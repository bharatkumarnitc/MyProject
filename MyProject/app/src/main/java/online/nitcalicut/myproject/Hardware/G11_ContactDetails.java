package online.nitcalicut.myproject.Hardware;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import online.nitcalicut.myproject.R;

public class G11_ContactDetails extends AppCompatActivity {
    TextView lblNumber;
    Button btnCall;
    String number;
    String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g12__contact_details);

        lblNumber = (TextView) findViewById(R.id.lblNumber);
        btnCall = (Button) findViewById(R.id.btnCall);

        Intent i = getIntent();

        if (i != null) {
            cid = i.getStringExtra("id");

            // Read Contact number of specific contact with help of Content Resolver
            ContentResolver cr = getContentResolver();

            Cursor c = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                    new String[]{cid}, null);

            c.moveToFirst();
            number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            // Display Contact Number into Label
            lblNumber.setText(number);

        }
    }

    public void C33_fun_MakeCall(View v) {
        // Implicit Intent to make call
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        startActivity(i);
    }
}


