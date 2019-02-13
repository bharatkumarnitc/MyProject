package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import online.nitcalicut.myproject.Layout.A2_Layout;
import online.nitcalicut.myproject.R;

public class B7_ToggleButton extends AppCompatActivity {
    ToggleButton tg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b7__toggle_button);

        tg=(ToggleButton)findViewById(R.id.B7_tgb);

        tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Toast.makeText(B7_ToggleButton.this, "yes", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(B7_ToggleButton.this, "no", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
