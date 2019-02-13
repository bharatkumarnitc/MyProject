package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B16_Seekbar extends AppCompatActivity {
    SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b16__seekbar);

        sb = (SeekBar) findViewById(R.id.B16_sb);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(getApplicationContext(), String.valueOf(progress), Toast.LENGTH_SHORT).show();
                if (progress >= 60)
                    Toast.makeText(getApplicationContext(), "Ruk Ja", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(),"Start",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(),"Stop",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void B16_fun_Show(View view) {
    }
}
