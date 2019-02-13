package online.nitcalicut.myproject.Hardware;

import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import online.nitcalicut.myproject.R;

public class G3_Wifi extends AppCompatActivity {
    WifiManager wm;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g3__wifi);

        b1 = (Button) findViewById(R.id.G3_btn);
    }

    public void G3_fun_Show(View v) {
        //Button b1 = (Button) v;
        //get Wifi service
        wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        //Check Wifi is on or off
        if (wm.isWifiEnabled()) {
            //enable or disable Wifi
            //for enable pass true value
            //for disable pass false value
            wm.setWifiEnabled(false);
            b1.setText("Turn On");
        } else {
            wm.setWifiEnabled(true);
            b1.setText("Turn Off");
        }
    }

}

