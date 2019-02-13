package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B13_TimePicker extends AppCompatActivity {
    TimePicker tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b13__time_picker);

        tp = (TimePicker) findViewById(R.id.B13_tp);

    }

    public void B13_fun_Show(View view) {
        String s="";

        s= String.valueOf(tp.getHour())+":"+String.valueOf(tp.getMinute());
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
