package online.nitcalicut.myproject.Hardware;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class G10_ReadSMS extends AppCompatActivity {
    ListView lst;
    static final Integer SMS = 0x8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g10__read_sms);

        askForPermission(Manifest.permission.SEND_SMS,SMS);
        lst = (ListView) findViewById(R.id.G10_lst);
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(G10_ReadSMS.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(G10_ReadSMS.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(G10_ReadSMS.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(G10_ReadSMS.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }


    public void G10_fun_Inbox(View v) {
        // Create Inbox box URI
        Uri inboxURI = Uri.parse("content://sms/inbox");
        // List required columns
        String[] reqCols = new String[]{"_id", "address", "body"};
        // Get Content Resolver object, which will deal with Content Provider
        ContentResolver cr = getContentResolver();
        // Fetch Inbox SMS Message from Built-in Content Provider
        Cursor c = cr.query(inboxURI, reqCols, null, null, null);
        // Attached Cursor with adapter and display in listview
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_g10__read_sms_row, c,
                new String[]{"body", "address"}, new int[]{
                R.id.G10_lbl_Msg, R.id.G10_lbl_Number});
        lst.setAdapter(adapter);
    }

    public void G10_fun_Draft(View v) {
        // Create Inbox box URI
        Uri inboxURI = Uri.parse("content://sms/draft");
        // List required columns
        String[] reqCols = new String[]{"_id", "address", "body"};
        // Get Content Resolver object, which will deal with Content Provider
        ContentResolver cr = getContentResolver();
        // Fetch Inbox SMS Message from Built-in Content Provider
        Cursor c = cr.query(inboxURI, reqCols, null, null, null);
        // Attached Cursor with adapter and display in listview
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_g10__read_sms_row, c,
                new String[]{"body", "address"}, new int[]{
                R.id.G10_lbl_Msg, R.id.G10_lbl_Number});
        lst.setAdapter(adapter);
    }

    public void G10_fun_Sent(View v) {
        // Create Inbox box URI
        Uri inboxURI = Uri.parse("content://sms/sent");
        // List required columns
        String[] reqCols = new String[]{"_id", "address", "body"};
        // Get Content Resolver object, which will deal with Content Provider
        ContentResolver cr = getContentResolver();
        // Fetch Inbox SMS Message from Built-in Content Provider
        Cursor c = cr.query(inboxURI, reqCols, null, null, null);
        // Attached Cursor with adapter and display in listview
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_g10__read_sms_row, c,
                new String[]{"body", "address"}, new int[]{
                R.id.G10_lbl_Msg, R.id.G10_lbl_Number});
        lst.setAdapter(adapter);
    }
}


