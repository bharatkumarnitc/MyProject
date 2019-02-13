package online.nitcalicut.myproject.Hardware;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class G7_PhoneCall extends AppCompatActivity {
    static final Integer CALL = 0x2;
    EditText txtNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g7__phone_call);

        txtNo=(EditText)findViewById(R.id.G7_txt_No);
        askForPermission(Manifest.permission.CALL_PHONE,CALL);
    }

    public void G7_fun_Call(View v) {
        String PhoneNo;
        PhoneNo = "tel:"+txtNo.getText().toString();
        //Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(PhoneNo));
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(PhoneNo));
        startActivity(i);
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(G7_PhoneCall.this, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(G7_PhoneCall.this, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(G7_PhoneCall.this, new String[]{permission}, requestCode);
            } else {
                ActivityCompat.requestPermissions(G7_PhoneCall.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

}


