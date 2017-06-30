package bigoudy.bigoudypro;

import android.app.ActionBar;
import android.app.FragmentBreadCrumbs;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetingDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetingDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingDetailFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String address;

    private OnFragmentInteractionListener mListener;

    android.support.v7.app.ActionBar actionBar;

    public MeetingDetailFragment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeetingDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeetingDetailFragment newInstance(String param1, String param2) {
        MeetingDetailFragment fragment = new MeetingDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meeting_detail, container, false);

        Meeting meetingDetails = (Meeting) getArguments().getSerializable("meetingDetails");
        address = meetingDetails.getAddressMeeting()+" "+meetingDetails.getZipcodeMeeting()+" "+meetingDetails.getCityMeeting();

        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.hide();

        FloatingActionButton floatingActionButtonGps = (FloatingActionButton)v.findViewById(R.id.floatingActionButtonGps);

        TextView textViewName = (TextView)v.findViewById(R.id.textViewName);
        Fonts.setFontButler(getActivity(), textViewName);
        TextView textViewLibPerf = (TextView)v.findViewById(R.id.textViewLibPerf);
        Fonts.setFontMontSerrat(getActivity(), textViewLibPerf);
        TextView textViewDate = (TextView)v.findViewById(R.id.textViewDate);
        Fonts.setFontMontSerrat(getActivity(), textViewDate);
        TextView textViewAdress = (TextView)v.findViewById(R.id.textViewAdress);
        Fonts.setFontMontSerrat(getActivity(), textViewAdress);
        TextView textViewRappel = (TextView)v.findViewById(R.id.textViewRappel);
        Fonts.setFontMontSerrat(getActivity(), textViewRappel);
        TextView textViewComments = (TextView)v.findViewById(R.id.textViewComments);
        Fonts.setFontMontSerrat(getActivity(), textViewComments);
        TextView textViewPrice = (TextView)v.findViewById(R.id.textViewPrice);
        Fonts.setFontMontSerrat(getActivity(), textViewPrice);
        CircleImageView imageViewAvatar = (CircleImageView)v.findViewById(R.id.imageViewAvatar);

        String urlFull = "https://www.bigoudychat.ovh/"+meetingDetails.getLinkAvatarCustomer();

        Picasso
                .with(getActivity().getApplicationContext())
                .load(Uri.parse(urlFull))
                .into(imageViewAvatar);


        textViewName.setText(meetingDetails.getFirstnameCustomer()+" "+meetingDetails.getLastnameCustomer());
        textViewLibPerf.setText(meetingDetails.getPerformances().get(0).getLibPerformance());

        String formatDate = getGoodDateFormat(meetingDetails.getDateMeeting());


        textViewDate.setText(formatDate+"\n"+meetingDetails.getBeginTimeAvailable().substring(0,5));
        textViewAdress.setText(meetingDetails.getAddressMeeting()+"\n"+meetingDetails.getZipcodeMeeting()+" "+meetingDetails.getCityMeeting());
        textViewRappel.setText("30 minutes avant");
        if (meetingDetails.getMoreInfoAskMeeting() != null) {
            textViewComments.setText(meetingDetails.getMoreInfoAskMeeting().toString());
        }   else{
            textViewComments.setText("Pas de commentaire");
        }
        textViewPrice.setText(meetingDetails.getAmmountWithTimeIncreaseHT()+"€");

        floatingActionButtonGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+address);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });


        // Inflate the layout for this fragment
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        actionBar.show();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public String getGoodDateFormat(String dateString){
        DateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
        fromFormat.setLenient(false);
        DateFormat toFormat = new SimpleDateFormat("EEEEEEEE dd MMM yyyy");
        toFormat.setLenient(false);
        Date date = null;
        try {
            date = fromFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toFormat.format(date);
    }


}
