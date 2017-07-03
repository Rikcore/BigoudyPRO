package bigoudy.bigoudypro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by apprenti on 03/07/17.
 */

public class PerfAdapter extends BaseAdapter {

    private Context context;
    private Meeting meeting;

    public PerfAdapter (Context context, Meeting meeting){
        this.context = context;
        this.meeting = meeting;
    }
    @Override
    public int getCount() {
        return meeting.getPerformances().size();
    }

    @Override
    public Object getItem(int position) {
        return meeting.getPerformances().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.performance_item, parent, false);
        }
        Performance currentPerf = meeting.getPerformances().get(position);

        TextView textViewNumPers = (TextView)convertView.findViewById(R.id.textViewNumPers);
        Fonts.setFontButler(context,textViewNumPers);
        TextView textViewType = (TextView)convertView.findViewById(R.id.textViewType);
        Fonts.setFontButler(context,textViewType);
        TextView textViewLib = (TextView)convertView.findViewById(R.id.textViewLib);
        Fonts.setFontButler(context,textViewLib);

        textViewNumPers.setText(currentPerf.getNumPers());
        textViewType.setText(currentPerf.getLibPerformanceType());
        textViewLib.setText(currentPerf.getLibPerformance());

        return convertView;
    }
}
