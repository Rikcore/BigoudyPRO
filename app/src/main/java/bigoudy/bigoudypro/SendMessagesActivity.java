package bigoudy.bigoudypro;

import java.util.ArrayList;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SendMessagesActivity extends AppCompatActivity {

    ListView listViewContact;
    Button buttonSend;
    TextView textViewAnnuler;
    ProgressDialog progressDialog;
    Handler progresshandler;

    ArrayList<Contact> contacts;
    ContactsAdapter contactAdapter;
    Contact contactToDelete;

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static final int PERMISSION_REQUEST_SEND_SMS = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_messages);

        listViewContact = (ListView) findViewById(R.id.listViewContact);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        textViewAnnuler = (TextView)findViewById(R.id.textViewAnnuler);

        contacts = new ArrayList<Contact>();

        showContacts();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending Messages.. Please wait!");

        progresshandler = new Handler() {
            public void handleMessage(Message msg) {
                progressDialog.dismiss();
                Toast.makeText(SendMessagesActivity.this, "Messages Sent",
                        Toast.LENGTH_LONG).show();
            }
        };

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_SEND_SMS);
                    //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
                } else {
                    // Android version is lesser than 6.0 or the permission is already granted.
                    sendSms();
                }
            }
        });




        listViewContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                contactToDelete = contacts.get(i);
                contacts.remove(contactToDelete);
                Toast.makeText(SendMessagesActivity.this, "Effacé", Toast.LENGTH_SHORT).show();
                contactAdapter.notifyDataSetChanged();
            }
        });

        textViewAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contactToDelete != null){
                    contacts.add(contactToDelete);
                    filterAlpha();
                    Toast.makeText(SendMessagesActivity.this, "Suppression annulée", Toast.LENGTH_SHORT).show();
                    contactAdapter.notifyDataSetChanged();
                    contactToDelete = null;
                } else {
                    Toast.makeText(SendMessagesActivity.this, "Rien à annuler", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            try {
                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

                while (phones.moveToNext()) {
                    String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Contact con = new Contact(name, phoneNumber);
                    if (con.getMobile().startsWith("06")||con.getMobile().startsWith("07")||con.getMobile().startsWith("+33")) {
                        contacts.add(con);
                    }
                }
                phones.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            filterAlpha();
            contactAdapter = new ContactsAdapter(this, contacts);
            listViewContact.setAdapter(contactAdapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == PERMISSION_REQUEST_SEND_SMS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendSms();
            } else {
                Toast.makeText(this, "Permission refusée", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class SendMessagesThread extends Thread {
        Handler handler;

        public SendMessagesThread(Handler handler) {
            this.handler = handler;
        }

        public void run() {
            SmsManager smsManager = SmsManager.getDefault();
            // Find out which contacts are selected
            for (int i = 0; i < contacts.size(); i++) {
                try {
                    smsManager.sendTextMessage(contacts.get(i).getMobile(), null, "Test envoi SMS groupé + "+contacts.get(i).getName(), null, null);
                } catch (Exception ex) {
                }

            }
            Message m = handler.obtainMessage();
            handler.sendMessage(m);
        } // run
    } // Thread

    public void sendSms(){
        SendMessagesThread thread = new SendMessagesThread(progresshandler);
        thread.start();
        progressDialog.show();
    }

    public void filterAlpha(){
        Collections.sort(contacts, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                Contact map1=(Contact) o1;
                Contact map2=(Contact) o2;
                String s1=(String)map1.getName();
                String s2=(String)map2.getName();
                return s1.compareTo(s2);
            }
        });
    }


}



