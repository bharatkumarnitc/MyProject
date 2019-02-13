package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import online.nitcalicut.myproject.R;

public class B8_Switch extends AppCompatActivity {
    Switch tg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b8__switch);
        tg=(Switch)findViewById(R.id.B8_sw);

        tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Toast.makeText(B8_Switch.this, "yes", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(B8_Switch.this, "no", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
