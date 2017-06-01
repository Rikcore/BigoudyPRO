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
    private ArrayList<FakeBooking> bookingArrayList;

    public BookingAdapter (Context context, ArrayList<FakeBooking> bookingArrayList){
        this.context = context;
        this.bookingArrayList = bookingArrayList;
    }
    @Override
    public int getCount() {
        return this.bookingArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.bookingArrayList.get(position);
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

        FakeBooking currentRdv = (FakeBooking)getItem(position);

        CircleImageView imageViewAvatar = (CircleImageView)convertView.findViewById(R.id.imageViewAvatar);
        TextView textViewName = (TextView)convertView.findViewById(R.id.textViewUser);
        TextView textViewDate = (TextView)convertView.findViewById(R.id.textViewDate);
        TextView textViewCoupe = (TextView)convertView.findViewById(R.id.textViewCoupe);
        TextView textViewPrice = (TextView)convertView.findViewById(R.id.textViewPrice);

        Picasso
                .with(this.context)
                .load(Uri.parse(currentRdv.getUriAvatar()))
                .into(imageViewAvatar);

        textViewName.setText(currentRdv.getFirstNameLastName());
        textViewDate.setText(currentRdv.getDateRdv());
        textViewCoupe.setText(currentRdv.getCoupe());
        textViewPrice.setText(currentRdv.getPriceAdresse());



        return convertView;
    }
}
