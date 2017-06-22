package bigoudy.bigoudypro;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
    public void fillValues(int position, View convertView) {
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
        Button buttonDecline = (Button)convertView.findViewById(R.id.buttonDecline);
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
                Toast.makeText(context, String.valueOf("J'accepte le rdv"+currentRdv.getIdCustomer()+currentRdv.getIdMeeting()), Toast.LENGTH_SHORT).show();
            }
        });


        buttonDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String action = "refuseReservation";
                int idMeeting = Integer.valueOf(currentRdv.getIdMeeting());
                String cancellationReason = "Rendez vous refusé";
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
        });


        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "J'envoie un message", Toast.LENGTH_SHORT).show();
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
}
