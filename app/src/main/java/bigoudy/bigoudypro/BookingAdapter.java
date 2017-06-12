package bigoudy.bigoudypro;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by apprenti on 01/06/17.
 */

public class BookingAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Meeting> meetingArrayList;

    public BookingAdapter (Context context, ArrayList<Meeting> meetingArrayList){
        this.context = context;
        this.meetingArrayList = meetingArrayList;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).
                    inflate(R.layout.booking_item, parent, false);
        }

        Meeting currentRdv = (Meeting) getItem(position);

        CircleImageView imageViewAvatar = (CircleImageView)convertView.findViewById(R.id.imageViewAvatar);
        TextView textViewName = (TextView)convertView.findViewById(R.id.textViewUser);
        TextView textViewDate = (TextView)convertView.findViewById(R.id.textViewDate);
        TextView textViewCoupe = (TextView)convertView.findViewById(R.id.textViewCoupe);
        TextView textViewPrice = (TextView)convertView.findViewById(R.id.textViewPrice);

        String urlAvatar = currentRdv.getLinkAvatarCustomer();
        String urlFull = "https://www.bigoudychat.ovh/"+urlAvatar;

        Picasso
                .with(this.context)
                .load(Uri.parse(urlFull))
                .into(imageViewAvatar);

        textViewName.setText(currentRdv.getFirstnameCustomer());
        textViewDate.setText(currentRdv.getDateMeeting());
        textViewCoupe.setText(currentRdv.getPerformances().get(position).getLibPerformance());
        textViewPrice.setText(currentRdv.getAmmountWithTimeIncreaseHT());



        return convertView;
    }
}
