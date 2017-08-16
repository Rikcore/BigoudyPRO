package bigoudy.bigoudypro;

import android.content.Context;
import android.content.Intent;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.services.calendar.CalendarScopes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*Copyright 2014 Raquib-ul-Alam WeekView Library

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.*/

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AgendaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendaFragment extends Fragment implements WeekView.EventClickListener, MonthLoader.MonthChangeListener,
        WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;
    private FloatingActionButton floatingActionButtonLogout;
    private HashMap<Integer, Meeting> hashMapMeeting;
    MeetingDetailFragment meetingDetailFragment;
    String idConnectUser;

    FragmentManager fragmentManager;

    List<WeekViewEvent> events;
    ArrayList<String> googleList;
    ArrayList<WeekViewEvent> googleWeekViewEventList;

    BookingModel bookingModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    GoogleAccountCredential mCredential;
    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;
    private static final String[] SCOPES = { CalendarScopes.CALENDAR_READONLY };
    private List<String> list;

    public AgendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgendaFragment newInstance(String param1, String param2) {
        AgendaFragment fragment = new AgendaFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);
        setHasOptionsMenu(true);



        googleList = new ArrayList<String>();
        googleWeekViewEventList = new ArrayList<WeekViewEvent>();
        idConnectUser = getArguments().getString("idConnectUser");

        googleList = getArguments().getStringArrayList("googleList");
        for (int i = 0; i < googleList.size(); i++){
            WeekViewEvent googleWeekViewEvent = getGoogleCalendarEvent(googleList.get(i));
            googleWeekViewEventList.add(googleWeekViewEvent);
        }

        floatingActionButtonLogout = (FloatingActionButton)view.findViewById(R.id.floatingActionButtonLogout);
        floatingActionButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("bigouderId", null).commit();
                startActivity(new Intent(getActivity(), LoginActivity.class));*/
                startActivity(new Intent(getActivity(), SendMessagesActivity.class));
            }
        });



        bookingModel = (BookingModel) getArguments().getSerializable("bookingModel");


        // Inflate the layout for this fragment

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) view.findViewById(R.id.weekView);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        // Set long press listener for empty view
        mWeekView.setEmptyViewLongPressListener(this);

        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false);

        mWeekView.goToHour(8);

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        setupDateTimeInterpreter(id == R.id.action_week_view);



        switch (id){
            case R.id.action_today:
                mWeekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.FRANCE);
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" dd", Locale.FRANCE);

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                if (hour == 24) hour = 0;
                if (hour == 0) hour = 0;
                return hour + ":00";

                /** return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM"); --> lines 140-141-142 changed by Omar */
            }
        });
    }

    protected String getEventTitle(Calendar time) {
        return String.format(Locale.FRANCE, "Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {


        meetingDetailFragment = new MeetingDetailFragment();
        fragmentManager = getFragmentManager();
        int testId = (int) event.getId();
        Meeting selectedMeeting = hashMapMeeting.get(testId);

        //Si c'est un rdv Bigoudy, ouvre le détail
        if(selectedMeeting != null) {
            Bundle bundleDetails = new Bundle();
            bundleDetails.putSerializable("meetingDetails", selectedMeeting);
            meetingDetailFragment.setArguments(bundleDetails);
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contentLayout, meetingDetailFragment, meetingDetailFragment.getTag())
                    .commit();

        //Sinon ouvre Google Calendar sur le mois en cours
        }else{
            Calendar cal = new GregorianCalendar();
            cal.setTime(new Date());
            cal.add(Calendar.MONTH, 0);
            long time = cal.getTime().getTime();
            Uri.Builder builder =
                    CalendarContract.CONTENT_URI.buildUpon();
            builder.appendPath("time");
            builder.appendPath(Long.toString(time));
            Intent intent =
                    new Intent(Intent.ACTION_VIEW, builder.build());
            startActivity(intent);
        }
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {


    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        events = new ArrayList<WeekViewEvent>();

        //AJOUT DES RDV GOOGLE A LA LISTE
        for (int j = 0; j < googleWeekViewEventList.size(); j++){
            WeekViewEvent eventGoogle = googleWeekViewEventList.get(j);
            // Calendar.MONTH va de 0 à 11, tandis que newMonth varie de 1 à 12 >> +1
            if(eventGoogle.getStartTime().get(Calendar.MONTH) + 1 == newMonth && eventGoogle.getStartTime().get(Calendar.YEAR) == newYear) {
                eventGoogle.setColor(getResources().getColor(R.color.bigoudydarkgrey));
                events.add(eventGoogle);
            }

        }

        hashMapMeeting = new HashMap<>();


        for (int i = 0; i < bookingModel.getMeetings().size(); i++) {
            String date = bookingModel.getMeetings().get(i).getDateMeeting();
            String heure = bookingModel.getMeetings().get(i).getBeginTimeAvailable();
            int duration = Integer.valueOf((String) bookingModel.getMeetings().get(i).getDurationMeeting());
            WeekViewEvent event = bookingModel.getMeetings().get(i).getEvent(date, heure, newMonth, newYear, bookingModel, i, duration);
            hashMapMeeting.put(Integer.valueOf(bookingModel.getMeetings().get(i).getIdMeeting()), bookingModel.getMeetings().get(i));
            if (event != null) {
                event.setColor(getResources().getColor(R.color.bigoudystronggold));
                events.add(event);
            }
        }




        return events;

    }
    //TRANSFORME LES STRINGS REÇUS DE L'API GOOGLE EN WEEKVIEWEVENT
    public WeekViewEvent getGoogleCalendarEvent(String date){

        WeekViewEvent googleWeekViewEvent;
        int hourGoogleStart = 0;
        int minuteGoogleStart = 0;
        int dayGoogleStart = 0;
        int monthGoogleStart = 0;
        int yearGoogleStart = 0;
        int hourGoogleEnd = 0;
        int minuteGoogleEnd = 0;
        int dayGoogleEnd = 0;
        int monthGoogleEnd = 0;
        int yearGoogleEnd = 0;

        int duration = 0;

        String[] totalSplit = date.split("¤");
        String summary = totalSplit[0];
        String startString = totalSplit[1];
        String endString = totalSplit[2];





        if(startString.contains("T") && endString.contains("T")) {
            String[] startStringSplit = startString.split("T");
            String startDateGoogle = startStringSplit[0];
            String startHourGoogle = startStringSplit[1];

            String[] startDateGoogleSplit = startDateGoogle.split("-");
            yearGoogleStart = Integer.valueOf(startDateGoogleSplit[0]);
            monthGoogleStart = Integer.valueOf(startDateGoogleSplit[1]);
            dayGoogleStart = Integer.valueOf(startDateGoogleSplit[2]);
            String[] startHourGoogleSplit = startHourGoogle.split(":");
            hourGoogleStart = Integer.valueOf(startHourGoogleSplit[0]);
            minuteGoogleStart = Integer.valueOf(startHourGoogleSplit[1]);


            String[] endStringSplit = endString.split("T");
            String endDateGoogle = endStringSplit[0];
            String endHourGoogle = endStringSplit[1];

            String[] endDateGoogleSplit = endDateGoogle.split("-");
            yearGoogleEnd = Integer.valueOf(endDateGoogleSplit[0]);
            monthGoogleEnd = Integer.valueOf(endDateGoogleSplit[1]);
            dayGoogleEnd = Integer.valueOf(endDateGoogleSplit[2]);
            String[] endHourGoogleSplit = endHourGoogle.split(":");
            hourGoogleEnd = Integer.valueOf(endHourGoogleSplit[0]);
            minuteGoogleEnd = Integer.valueOf(endHourGoogleSplit[1]);





        }else{
            String[] startSplitString = startString.split("-");
            yearGoogleStart = Integer.valueOf(startSplitString[0]);
            monthGoogleStart = Integer.valueOf(startSplitString[1]);
            dayGoogleStart = Integer.valueOf(startSplitString[2]);
            hourGoogleStart = 00;
            minuteGoogleStart = 00;

            String[] endSplitString = endString.split("-");
            yearGoogleEnd = Integer.valueOf(endSplitString[0]);
            monthGoogleEnd = Integer.valueOf(endSplitString[1]);
            dayGoogleEnd = Integer.valueOf(endSplitString[2]);
            hourGoogleEnd = 0;
            minuteGoogleEnd = 0;


        }

        try {

            //Dates to compare
            String CurrentDate =  yearGoogleStart+"-"+monthGoogleStart+"-"+dayGoogleStart+" "+hourGoogleStart+":"+minuteGoogleStart;
            String FinalDate =  yearGoogleEnd+"-"+monthGoogleEnd+"-"+dayGoogleEnd+" "+hourGoogleEnd+":"+minuteGoogleEnd;

            Date date1;
            Date date2;

            SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            //Setting dates
            date1 = dates.parse(CurrentDate);
            date2 = dates.parse(FinalDate);

            //Comparing dates
            long difference = Math.abs(date1.getTime() - date2.getTime());
            long differenceDates = difference / (60 * 1000);

            duration = (int) differenceDates;

            pushGoogleRdvToBigoudy(duration, yearGoogleStart, monthGoogleStart, dayGoogleStart, hourGoogleStart, minuteGoogleStart);

        } catch (Exception exception) {
            Log.e("DIDN'T WORK", "exception " + exception);
        }



        Calendar starTime = Calendar.getInstance();

        starTime.set(Calendar.HOUR_OF_DAY, hourGoogleStart);
            starTime.set(Calendar.MINUTE, minuteGoogleStart);
            starTime.set(Calendar.DAY_OF_MONTH, dayGoogleStart);
            starTime.set(Calendar.MONTH, monthGoogleStart - 1);
            starTime.set(Calendar.YEAR, yearGoogleStart);
            Calendar endtime = (Calendar) starTime.clone();
            endtime.add(Calendar.MINUTE, duration);
            googleWeekViewEvent = new WeekViewEvent(0, summary, starTime, endtime);


            return googleWeekViewEvent;


    }


    //RECUPERE LES DONNÉES ET LES TRANSFORME POUR LES FOURNIR AU BON FORMAT À BIGOUDY POUR GRISER LE CALENDRIER
    public void pushGoogleRdvToBigoudy(int duration, int year, int month, int day, int hour, int minute){
        String action = "addBigouderExceptionTextualTimeAvailable";
        Integer id = Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("bigouderId", null));
        //Boolean available = false;
        Integer available = 0;
        String hourBigoudy;
        String minuteBigoudy;
        int durationBigoudy;

        if (duration == 1440){
            durationBigoudy = 780;
        } else {
            durationBigoudy = duration;
        }
        if (hour == 0){
            hourBigoudy = "08";
        }
        else if(hour < 10){
            hourBigoudy = "0"+hour;
        }else{
            hourBigoudy = String.valueOf(hour);
        }
        if(minute == 0){
            minuteBigoudy = "00";
        }else{
            minuteBigoudy = String.valueOf(minute);
        }
        String beginTime = hourBigoudy+":"+minuteBigoudy+":00";

        String monthBigoudy;
        String dayBigoudy;

        if(month < 10){
            monthBigoudy = "0"+month;
        }else{
            monthBigoudy = String.valueOf(month);
        }

        if(day < 10){
            dayBigoudy = "0"+day;
        }else{
            dayBigoudy = String.valueOf(day);
        }

        String dateMeeting = year+"-"+monthBigoudy+"-"+dayBigoudy;

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date(year - 1900, month - 1, day);
        String frenchDayOfTheWeek = sdf.format(d);

        String engDayOfTheWeek;

        switch (frenchDayOfTheWeek){
            case "lundi":
                engDayOfTheWeek = "monday";
                break;
            case "mardi":
                engDayOfTheWeek = "tuesday";
                break;
            case "mercredi":
                engDayOfTheWeek = "wednesday";
                break;
            case "jeudi":
                engDayOfTheWeek = "thursday";
                break;
            case "vendredi":
                engDayOfTheWeek = "friday";
                break;
            case "samedi":
                engDayOfTheWeek = "saturday";
                break;
            case "dimanche":
                engDayOfTheWeek = "sunday";
                break;
            default:
                engDayOfTheWeek = null;
        }


        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Resources.RESOURCES)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);

        final Call<ExceptionTime> exceptionTimeCall = serviceApi.setException(action, id, beginTime, durationBigoudy, engDayOfTheWeek , dateMeeting, available );

        exceptionTimeCall.enqueue(new Callback<ExceptionTime>() {
            @Override
            public void onResponse(Call<ExceptionTime> call, Response<ExceptionTime> response) {
                ExceptionTime exceptionTime = response.body();
            }

            @Override
            public void onFailure(Call<ExceptionTime> call, Throwable t) {
            }
        });


    }


}

