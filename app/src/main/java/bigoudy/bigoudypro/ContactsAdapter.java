package bigoudy.bigoudypro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rikcore on 14/08/17.
 */

public class ContactsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Contact> contacts;
    private LayoutInflater mInflater;

    public ContactsAdapter(Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
        mInflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        /*LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from contact.xml
            gridView = inflater.inflate(R.layout.contact_item, null);

            TextView textName = (TextView) gridView.findViewById(R.id.name);
            textName.setText( contacts.get(position).getName());

            TextView textMobile = (TextView) gridView.findViewById(R.id.mobile);
            textMobile.setText( contacts.get(position).getMobile());

        } else {
            gridView = (View) convertView;
        }

        return gridView;*/

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.contact_item, parent, false);
        }

        Contact currentContact = contacts.get(position);

        TextView textName = (TextView) convertView.findViewById(R.id.name);
        textName.setText(currentContact.getName());

        TextView textMobile = (TextView) convertView.findViewById(R.id.mobile);
        textMobile.setText(currentContact.getMobile());

        return convertView;
    }


    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

}
