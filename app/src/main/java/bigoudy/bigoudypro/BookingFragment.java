package bigoudy.bigoudypro;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




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
    private SwipeLayout swipeLayout;
    Button btn_tout, btn_demand, btn_incoming;



    private BookingModel bookingModel;
    private SwipeBookingAdapter swipeBookingAdapter;
    private ListView listViewBooking;
    private OnFragmentInteractionListener mListener;

    MeetingDetailFragment meetingDetailFragment;
    FragmentManager fragmentManager;
    String idConnectUser;

    Button buttonAccept;
    Button buttonDecline;
    Button buttonMessage;

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
                             final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_booking, container, false);
        idConnectUser = getArguments().getString("idConnectUser");

        btn_tout = (Button)view.findViewById(R.id.btn_tout);
        btn_demand = (Button)view.findViewById(R.id.btn_resa);
        btn_incoming = (Button)view.findViewById(R.id.btn_accept);

        filterMeeting("demand");

        btn_tout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                filterMeeting("");
                DesignButtonSelected(btn_tout);
                DesignButtonUnselected(btn_demand);
                DesignButtonUnselected(btn_incoming);

            }
         });

        btn_demand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterMeeting("demand");
                DesignButtonSelected(btn_demand);
                DesignButtonUnselected(btn_incoming);
                DesignButtonUnselected(btn_tout);
            }
        });

        btn_incoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterMeeting("incoming");
                DesignButtonSelected(btn_incoming);
                DesignButtonUnselected(btn_demand);
                DesignButtonUnselected(btn_tout);
            }
        });


        fragmentManager = getFragmentManager();
        meetingDetailFragment = new MeetingDetailFragment();

        listViewBooking = (ListView)view.findViewById(R.id.listViewBooking);
        

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
        final String idConnectUserString = getArguments().getString("idConnectUser");
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

                swipeBookingAdapter = new SwipeBookingAdapter(getActivity(), meetingArrayList, idConnectUser);

                listViewBooking.setAdapter(swipeBookingAdapter);
            }

            @Override
            public void onFailure(Call<BookingModel> call, Throwable t) {
            }
        });


    }

    public void DesignButtonSelected(Button button){
        button.setBackground(getResources().getDrawable(R.drawable.btn_selected));
        button.setTextColor(getResources().getColor(R.color.bigoudydarkgrey));

    }

    public void DesignButtonUnselected(Button button){
        button.setBackground(getResources().getDrawable(R.drawable.btn_unselected));
        button.setTextColor(getResources().getColor(R.color.bigoudystronggrey));
    }



}
