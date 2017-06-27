package bigoudy.bigoudypro;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.telecom.Call;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.squareup.picasso.Picasso;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by apprenti on 20/06/17.
 */

public class SwipeBookingAdapter extends BaseSwipeAdapter {

    private Context context;
    private ArrayList<Meeting> meetingArrayList;
    private BookingFragment bookingFragment;
    private String idBigouder;
    private String address;

    public final static String DECLINE_MOTIF = "Saisissez le motif du refus";

    private int value = 30;

    FragmentManager fragmentManager;


    public SwipeBookingAdapter (Context context, ArrayList<Meeting> meetingArrayList, String idBigouder, BookingFragment bookingFragment){
        this.context = context;
        this.meetingArrayList = meetingArrayList;
        this.idBigouder = idBigouder;
        this.bookingFragment = bookingFragment;
    }
    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.swipe_booking_item, null);
    }

    @Override
    public void fillValues(int position, final View convertView) {
        final Meeting currentRdv = (Meeting) getItem(position);
        address = currentRdv.getAddressMeeting()+" "+currentRdv.getZipcodeMeeting()+" "+currentRdv.getCityMeeting();


        CircleImageView imageViewAvatar = (CircleImageView)convertView.findViewById(R.id.imageViewAvatar);
        TextView textViewName = (TextView)convertView.findViewById(R.id.textViewUser);
        Fonts.setFontButler(context, textViewName);
        TextView textViewDate = (TextView)convertView.findViewById(R.id.textViewDate);
        Fonts.setFontMontSerrat(context, textViewDate);
        TextView textViewCoupe = (TextView)convertView.findViewById(R.id.textViewCoupe);
        Fonts.setFontMontSerrat(context, textViewCoupe);
        TextView textViewPrice = (TextView)convertView.findViewById(R.id.textViewPrice);
        Fonts.setFontMontSerrat(context, textViewPrice);

        String urlAvatar = currentRdv.getLinkAvatarCustomer();
        String urlFull = "https://www.bigoudychat.ovh/"+urlAvatar;

        Picasso
                .with(this.context)
                .load(Uri.parse(urlFull))
                .into(imageViewAvatar);

        textViewName.setText(currentRdv.getFirstnameCustomer());
        textViewDate.setText(currentRdv.getDateMeeting());
        textViewCoupe.setText(currentRdv.getPerformances().get(0).getLibPerformance());
        textViewPrice.setText(currentRdv.getAmmountWithTimeIncreaseHT());

        Button buttonAccept = (Button)convertView.findViewById(R.id.buttonAccept);
        final Button buttonDecline = (Button)convertView.findViewById(R.id.buttonDecline);
        Button buttonMessage = (Button)convertView.findViewById(R.id.buttonMessage);
        Button buttonGps = (Button)convertView.findViewById(R.id.buttonGPS);
        if(currentRdv.getIsADemandMeeting().equals("0")){
            buttonAccept.setVisibility(View.GONE);
            buttonDecline.setVisibility(View.GONE);
            buttonGps.setVisibility(View.VISIBLE);
        }else{
            buttonAccept.setVisibility(View.VISIBLE);
            buttonDecline.setVisibility(View.VISIBLE);
            buttonGps.setVisibility(View.GONE);
        }
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int step = 30;
                final int max = 240;
                final int min = 30;

                final AlertDialog.Builder alertAccept = new AlertDialog.Builder(context);
                alertAccept.setMessage("Merci de choisir la durée du rendez-vous");
                alertAccept.setTitle("Durée rendez-vous");
                final LinearLayout linearLayoutAccept = new LinearLayout(context);
                linearLayoutAccept.setOrientation(LinearLayout.VERTICAL);
                final TextView durationTextView = new TextView(context);
                final SeekBar seekBar = new SeekBar(context);
                final Button buttonFinalizeRdv = new Button(context);
                buttonFinalizeRdv.setText("Ok");
                seekBar.setMax( (max - min) / step );
                linearLayoutAccept.addView(durationTextView);
                durationTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                buttonFinalizeRdv.setGravity(Gravity.CENTER_HORIZONTAL);
                durationTextView.setTextSize(20);
                linearLayoutAccept.addView(seekBar);
                linearLayoutAccept.addView(buttonFinalizeRdv);
                alertAccept.setView(linearLayoutAccept);

                final AlertDialog ad = alertAccept.show();

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        value = min + (progress * step);
                        durationTextView.setText(String.valueOf(value));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                buttonFinalizeRdv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        StringBuilder sb = new StringBuilder();
                        String action = "acceptReservation";
                        int idMeeting = Integer.valueOf(currentRdv.getIdMeeting());
                        int durationMeeting = value;
                        int caseToLock = value/30;
                        LocalTime[] tab = new LocalTime[caseToLock];
                        tab[0] = LocalTime.parse(currentRdv.getBeginTimeAvailable());
                        for(int i = 1; i < tab.length; i++){
                            tab[i] = tab[i-1].plusMinutes(30);
                        }
                        String[] tabString = new String[caseToLock];
                        for (int j = 0; j < tabString.length; j++){
                            tabString[j] = "'"+tab[j].toString().substring(0, 8)+"'";
                        }
                        for (int k = 0; k < tabString.length; k++){
                            if(k < tabString.length - 1){
                                sb.append(tabString[k]);
                                sb.append(",");
                            }else{
                                sb.append(tabString[k]);
                            }
                        }

                        String dateMeeting = currentRdv.getDateMeeting();
                        String timesAvailable = sb.toString();
                        String moreInfoBigouder = "";
                        Integer id = Integer.valueOf(idBigouder);
                        String dateDiagnosticMeeting = "null";
                        String timeDiagnosticAvailable = "null";

                        final retrofit2.Call<AcceptReservation> acceptReservation = callRetrofit().acceptReservation(action, idMeeting, durationMeeting, dateMeeting, timesAvailable, moreInfoBigouder, id, dateDiagnosticMeeting, timeDiagnosticAvailable);
                        acceptReservation.enqueue(new Callback<AcceptReservation>() {
                            @Override
                            public void onResponse(retrofit2.Call<AcceptReservation> call, Response<AcceptReservation> response) {
                                Toast.makeText(context, String.valueOf("J'accepte le rdv"+currentRdv.getIdCustomer()+currentRdv.getIdMeeting()), Toast.LENGTH_SHORT).show();
                                bookingFragment.btn_demand.callOnClick();
                                ad.dismiss();


                            }

                            @Override
                            public void onFailure(retrofit2.Call<AcceptReservation> call, Throwable t) {

                            }
                        });

                    }
                });
            }
        });


        buttonDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDecline = new AlertDialog.Builder(context);
                alertDecline.setTitle("Refus rendez-vous");
                alertDecline.setMessage("Merci de préciser votre motif de refus");
                final LinearLayout linearLayoutDecline = new LinearLayout(context);
                linearLayoutDecline.setOrientation(LinearLayout.VERTICAL);
                final EditText editTextDeclineReason = new EditText(context);
                final Button buttonDeclineReason = new Button(context);
                buttonDeclineReason.setText("OK");
                final Button buttonCancelDecline = new Button(context);
                buttonCancelDecline.setText("Annuler");
                linearLayoutDecline.addView(editTextDeclineReason);
                linearLayoutDecline.addView(buttonDeclineReason);
                alertDecline.setView(linearLayoutDecline);
                final AlertDialog ad = alertDecline.show();

                buttonDeclineReason.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        declineMeeting(currentRdv, editTextDeclineReason.getText().toString());
                        ad.dismiss();
                    }
                });




            }
        });


        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String motif = "Saisissez votre message";
                Toast.makeText(context, "J'envoie un message", Toast.LENGTH_SHORT).show();

                final LinearLayout linearLayoutMessage = new LinearLayout(context);
                linearLayoutMessage.setOrientation(LinearLayout.VERTICAL);
                final EditText edittextMessage = new EditText(context);
                edittextMessage.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES|InputType.TYPE_TEXT_FLAG_MULTI_LINE);
               // edittext.setMinLines(2);
                edittextMessage.setMaxLines(4);
                edittextMessage.setVerticalScrollBarEnabled(true);
                edittextMessage.setScroller(new Scroller(context));
                edittextMessage.setMovementMethod(new ScrollingMovementMethod());
                edittextMessage.setGravity(Gravity.LEFT|Gravity.TOP);
                Button buttonSendMessage = new Button(context);
                buttonSendMessage.setText("ok");
                buttonSendMessage.setBackground(context.getResources().getDrawable(R.drawable.selector_btn_message));
                linearLayoutMessage.addView(edittextMessage);
                linearLayoutMessage.addView(buttonSendMessage);
                AlertDialog.Builder alertMessage = new AlertDialog.Builder(context);
                alertMessage.setMessage("Entrez votre Message");
                alertMessage.setTitle("Message");
                alertMessage.setView(linearLayoutMessage);

                final AlertDialog ad = alertMessage.show();
                buttonSendMessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendMessage(currentRdv, edittextMessage.getText().toString());
                        ad.dismiss();
                    }
                });

            }
        });

        buttonGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+address);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);
            }
        });

    }

    @Override
    public int getCount() {
        return this.meetingArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.meetingArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ServiceApi callRetrofit(){
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.bigoudychat.ovh/app/resources/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);
        return serviceApi;
    }

    public void sendMessage(Meeting meeting, String message) {
        String action = "addMessageAndDiscussion";
        Integer idSender = Integer.valueOf(idBigouder);
        Integer idReceiver = Integer.valueOf(meeting.getIdCustomer());
        final retrofit2.Call<Message> callMessage = callRetrofit().sendMessageRdv(action, idSender, idReceiver, message);
        callMessage.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(retrofit2.Call<Message> call, Response<Message> response) {
                Toast.makeText(context, "Message envoyé", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(retrofit2.Call<Message> call, Throwable t) {

            }
        });
    }
    public void declineMeeting(Meeting meeting, String declineReason){
        String action = "refuseReservation";
        int idMeeting = Integer.valueOf(meeting.getIdMeeting());
        String cancellationReason = declineReason;
        final retrofit2.Call<DeclineMeeting> declineMeeting = callRetrofit().declineReservation(action, idMeeting, cancellationReason);
        declineMeeting.enqueue(new Callback<DeclineMeeting>() {
            @Override
            public void onResponse(retrofit2.Call<DeclineMeeting> call, Response<DeclineMeeting> response) {
                Toast.makeText(context, "Rendez-vous refusé", Toast.LENGTH_SHORT).show();
                bookingFragment.btn_demand.callOnClick();
            }

            @Override
            public void onFailure(retrofit2.Call<DeclineMeeting> call, Throwable t) {

            }
        });

    }
}
