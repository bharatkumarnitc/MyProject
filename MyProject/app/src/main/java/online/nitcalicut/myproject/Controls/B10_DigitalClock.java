package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DigitalClock;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B10_DigitalClock extends AppCompatActivity {
    DigitalClock clk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b10__digital_clock);

        clk=(DigitalClock)findViewById(R.id.B10_clk);

        Toast.makeText(this, clk.getText(), Toast.LENGTH_LONG).show();
    }
}
