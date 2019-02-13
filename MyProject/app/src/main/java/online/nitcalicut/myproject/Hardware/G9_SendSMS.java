package online.nitcalicut.myproject.Hardware;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class G9_SendSMS extends AppCompatActivity {
    EditText txtMobileNo,txtMessage;
    Button btnSendSMS;
    static final Integer SMS = 0x8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g9__send_sms);

        askForPermission(Manifest.permission.SEND_SMS,SMS);
        txtMobileNo=(EditText)findViewById(R.id.G9_txt_PhoneNo);
        txtMessage=(EditText)findViewById(R.id.G9_txt_Message);
        btnSendSMS=(Button)findViewById(R.id.G9_button1);

        //Performing action on button click
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no=txtMobileNo.getText().toString();
                String msg=txtMessage.getText().toString();

                //Getting intent and PendingIntent instance
                Intent intent=new Intent(getApplicationContext(),G9_SendSMS.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                //Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(no, null, msg, pi,null);

                Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(G9_SendSMS.this, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(G9_SendSMS.this, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(G9_SendSMS.this, new String[]{permission}, requestCode);
            } else {
                ActivityCompat.requestPermissions(G9_SendSMS.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }
}

