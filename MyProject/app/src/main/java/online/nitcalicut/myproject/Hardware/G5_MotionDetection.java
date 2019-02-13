package online.nitcalicut.myproject.Hardware;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import online.nitcalicut.myproject.R;

public class G5_MotionDetection extends AppCompatActivity implements SensorEventListener {
    SensorManager sm;
    LinearLayout ll;
    BluetoothAdapter ba = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g5__motion_detection);

        ba = BluetoothAdapter.getDefaultAdapter();
        ll = (LinearLayout) findViewById(R.id.G5_ll);
        //get sensor service
        sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        //Tell which sensor you are going to use
        //And declare delay of sensor
        //Register all to your sensor object to use
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }
    //This method is called when your mobile moves any direction

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            //get x, y, z values
            float value[] = event.values;
            float x = value[0];
            float y = value[1];
            float z = value[2];
            //use the following formula
            //use gravity according to your place if you are on moon than use moon gravity
            float asr = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH *
                    SensorManager.GRAVITY_EARTH);
            //If mobile move any direction then the following condition will become true
            if (asr >= 2) {
                //any thing
                Random random = new Random();
                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
                ll.setBackgroundColor(color);

                if (ba == null) {
                    Toast.makeText(this, "No Blue tooth found", Toast.LENGTH_LONG).show();
                } else {
                    if (!ba.isEnabled()) {
                        ba.enable();
                    } else {
                        ba.disable();
                    }
                }
            }
        }
    }
}
