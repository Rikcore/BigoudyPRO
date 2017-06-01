package bigoudy.bigoudypro;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


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

    private OnFragmentInteractionListener mListener;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        String idConnectUser = getArguments().getString("idConnectUser");

        final ArrayList<FakeBooking> bookingArrayList = new ArrayList<>();

        bookingArrayList.add(new FakeBooking("http://www.humoristique.info/images-droles/animaux/percing+sur+un+singe+punk.jpg", "29/05/17 12h00", "Toto Caca", "Coupe","230€"));
        bookingArrayList.add(new FakeBooking("https://s-media-cache-ak0.pinimg.com/736x/1c/d0/b0/1cd0b044ed429acbe18f2ba4eae5b89d.jpg", "29/05/17 14h00", "Rikcore C.", "Coupe","20€"));
        bookingArrayList.add(new FakeBooking("https://img.20mn.fr/SDJvH_FxTvms1VBuVcBz2A/830x532_plateau-the-tonight-show-starring-jimmy-fallon-legendaire-coiffure-donald-trump-pris-sacre-coup", "29/05/17 16h00", "Donald T.", "Couleur","987€"));
        bookingArrayList.add(new FakeBooking("http://img1.ndsstatic.com/chien/un-bebe-singe-a-la-coiffure-avant-gardiste_181845_w620.jpg", "29/05/17 18h00", "Sarah T.", "Brushing","24€"));
        bookingArrayList.add(new FakeBooking("http://zupimages.net/viewer.php?id=17/22/e6th.jpg", "29/05/17 19h00", "Bruno M.", "Shampoing","30€"));

        final BookingAdapter bookingAdapter = new BookingAdapter(getActivity(), bookingArrayList);

        ListView listViewBooking = (ListView)view.findViewById(R.id.listViewBooking);

        listViewBooking.setAdapter(bookingAdapter);
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
}
