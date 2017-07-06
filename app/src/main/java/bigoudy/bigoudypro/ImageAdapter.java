package bigoudy.bigoudypro;

/**
 * Created by sara on 29/06/2017.
 */

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<DiagnosticPhoto> diagnosticPhotoArrayList;




    public ImageAdapter(Context c, ArrayList<DiagnosticPhoto> diagnosticPhotoArrayList) {
        mContext = c;
        this.diagnosticPhotoArrayList = diagnosticPhotoArrayList;
    }

    public int getCount() {
        return diagnosticPhotoArrayList.size();
    }

    @Override
    public String getItem(int position) {
        return diagnosticPhotoArrayList.get(position).getLinkImageHD();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(480, 480));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(25, 25, 25, 25);
        } else {
            imageView = (ImageView) convertView;
        }
        String url = Resources.URLOVH + getItem(position);
        Picasso.with(mContext)
                .load(Uri.parse(url))
                .fit()
                .centerCrop().into(imageView);
        return imageView;
    }
}