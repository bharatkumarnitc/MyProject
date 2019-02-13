package online.nitcalicut.myproject.Hardware;

import android.Manifest;
import android.content.pm.PackageManager;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class G13_CallLogWrite extends AppCompatActivity {
    static final Integer CONTACT = 0x9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g13__call_log_write);
        askForPermission(Manifest.permission.WRITE_CALL_LOG,CONTACT);
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(G13_CallLogWrite.this, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(G13_CallLogWrite.this, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(G13_CallLogWrite.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(G13_CallLogWrite.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    public  void G13_fun_CallLogMissedcall(View v) {
        //Add number into calllog
        long  callTimeInMiliSecond	= System.currentTimeMillis(); //time stamp

//CallLogUtility is the class where we have written our add/delete function
        G13_CallLogUtility utility = new G13_CallLogUtility();
        //number to add
        EditText e1=(EditText)findViewById(R.id.G13_txt_MobileNoCL);

        String numberStr = e1.getText().toString();
        utility.AddNumToCallLog(this.getContentResolver(),numberStr, CallLog.Calls.MISSED_TYPE, callTimeInMiliSecond);
    }

    public  void G13_fun_CallLogOutgoing(View v) {
        //Add number into calllog
        long  callTimeInMiliSecond	= System.currentTimeMillis(); //time stamp
        //CallLogUtility is the class where we have written our add/delete function
        G13_CallLogUtility utility = new G13_CallLogUtility();
        //number to add
        EditText e1=(EditText)findViewById(R.id.G13_txt_MobileNoCL);
        String numberStr = e1.getText().toString();
        utility.AddNumToCallLog(getBaseContext().getContentResolver(),numberStr, CallLog.Calls.OUTGOING_TYPE, callTimeInMiliSecond);
    }

    public  void G13_fun_CallLogInComming(View v) {
        //Add number into calllog
        long  callTimeInMiliSecond	= System.currentTimeMillis(); //time stamp
        //CallLogUtility is the class where we have written our add/delete function
        G13_CallLogUtility utility = new G13_CallLogUtility();
        //number to add
        EditText e1=(EditText)findViewById(R.id.G13_txt_MobileNoCL);
        String numberStr = e1.getText().toString();
        utility.AddNumToCallLog(getBaseContext().getContentResolver(),numberStr, CallLog.Calls.INCOMING_TYPE, callTimeInMiliSecond);
    }

}
