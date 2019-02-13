package online.nitcalicut.myproject.Hardware;

import android.bluetooth.BluetoothAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class G2_BlueTooth extends AppCompatActivity {
    BluetoothAdapter ba=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g2__blue_tooth);

        ba=BluetoothAdapter.getDefaultAdapter();
    }

    public void G2_fun_Show(View v)
    {
        Button btn;
        if (ba == null) {
            Toast.makeText(this, "No Bluetooth found", Toast.LENGTH_LONG).show();
        } else {
            if (ba.isEnabled()) {
                //ba.disable();
                ((Button)v).setText("Turn On");
            } else {
                //ba.enable();
                btn=(Button)v;
                btn.setText("Turn Off");
            }
        }
    }
}

