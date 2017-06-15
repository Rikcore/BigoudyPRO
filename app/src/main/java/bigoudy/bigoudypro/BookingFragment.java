package bigoudy.bigoudypro;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.id;
import static bigoudy.bigoudypro.R.id.listViewBooking;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btn_tout, btn_resa, btn_accept;



    private BookingModel bookingModel;
    private BookingAdapter bookingAdapter;
    private ListView listViewBooking;
    private OnFragmentInteractionListener mListener;

    MeetingDetailFragment meetingDetailFragment;
    FragmentManager fragmentManager;
    android.support.v7.app.ActionBar actionBar;

    public BookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingFragment newInstance(String param1, String param2) {
        BookingFragment fragment = new BookingFragment();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_booking, container, false);

        filterMeeting("");

        btn_tout = (Button)view.findViewById(R.id.btn_tout);
        btn_tout.setBackground(getResources().getDrawable(R.drawable.btn_selected));
        btn_tout.setTextColor(getResources().getColor(R.color.white));
        btn_tout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                filterMeeting("");
                btn_tout.setBackground(getResources().getDrawable(R.drawable.btn_selected));
                btn_resa.setBackground(getResources().getDrawable(R.drawable.btn_gris));
                btn_accept.setBackground(getResources().getDrawable(R.drawable.btn_gris));
                btn_tout.setTextColor(getResources().getColor(R.color.white));
                btn_resa.setTextColor(getResources().getColor(R.color.black));
                btn_accept.setTextColor(getResources().getColor(R.color.black));

            }
         });

        btn_resa = (Button)view.findViewById(R.id.btn_resa);
        btn_resa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterMeeting("demand");
                btn_tout.setBackground(getResources().getDrawable(R.drawable.btn_gris));
                btn_resa.setBackground(getResources().getDrawable(R.drawable.btn_selected));
                btn_accept.setBackground(getResources().getDrawable(R.drawable.btn_gris));
                btn_tout.setTextColor(getResources().getColor(R.color.black));
                btn_resa.setTextColor(getResources().getColor(R.color.white));
                btn_accept.setTextColor(getResources().getColor(R.color.black));

            }
        });

        btn_accept = (Button)view.findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterMeeting("incoming");
                btn_tout.setBackground(getResources().getDrawable(R.drawable.btn_gris));
                btn_resa.setBackground(getResources().getDrawable(R.drawable.btn_gris));
                btn_accept.setBackground(getResources().getDrawable(R.drawable.btn_selected));
                btn_tout.setTextColor(getResources().getColor(R.color.black));
                btn_resa.setTextColor(getResources().getColor(R.color.black));
                btn_accept.setTextColor(getResources().getColor(R.color.white));
            }
        });


        fragmentManager = getFragmentManager();
        meetingDetailFragment = new MeetingDetailFragment();

        listViewBooking = (ListView)view.findViewById(R.id.listViewBooking);


        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.hide();

       /* String action = "getIncomingMeetingByBigouderId";
        String idConnectUserString = getArguments().getString("idConnectUser");
        Integer id = new Integer(idConnectUserString).intValue();
        String filter = "";

        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.bigoudychat.ovh/app/resources/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);

        final Call<BookingModel> bookingModelCall = serviceApi.getBookingModel(action, id, filter);

        bookingModelCall.enqueue(new Callback<BookingModel>() {
            @Override
            public void onResponse(Call<BookingModel> call, Response<BookingModel> response) {
                bookingModel = response.body();
                final ArrayList<Meeting> meetingArrayList = (ArrayList<Meeting>)bookingModel.getMeetings();


                final BookingAdapter bookingAdapter = new BookingAdapter(getActivity(), meetingArrayList);

                listViewBooking.setAdapter(bookingAdapter);


            }



            @Override
            public void onFailure(Call<BookingModel> call, Throwable t) {
                String tata = "tata";

            }
        });*/

        listViewBooking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Meeting selectedMeeting = bookingModel.getMeetings().get(position);
                Bundle bundleDetails = new Bundle();
                bundleDetails.putSerializable("meetingDetails", selectedMeeting);
                meetingDetailFragment.setArguments(bundleDetails);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.contentLayout, meetingDetailFragment, meetingDetailFragment.getTag())
                        .commit();

            }
        });



        // Inflate the layout for this fragment
        return view;


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

    public void filterMeeting(String filter){

        String action = "getIncomingMeetingByBigouderId";
        String idConnectUserString = getArguments().getString("idConnectUser");
        Integer id = new Integer(idConnectUserString).intValue();

        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.bigoudychat.ovh/app/resources/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);

        final Call<BookingModel> bookingModelCall = serviceApi.getBookingModel(action, id, filter);

        bookingModelCall.enqueue(new Callback<BookingModel>() {
            @Override
            public void onResponse(Call<BookingModel> call, Response<BookingModel> response) {
                bookingModel = response.body();
                final ArrayList<Meeting> meetingArrayList = (ArrayList<Meeting>)bookingModel.getMeetings();


                bookingAdapter = new BookingAdapter(getActivity(), meetingArrayList);

                listViewBooking.setAdapter(bookingAdapter);


            }



            @Override
            public void onFailure(Call<BookingModel> call, Throwable t) {
                String tata = "tata";

            }
        });


    }




}
