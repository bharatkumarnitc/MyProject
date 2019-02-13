package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B12_DatePicker extends AppCompatActivity {
    DatePicker dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b12__date_picker);

        dp=(DatePicker)findViewById(R.id.B12_dp);
    }

    public void B12_fun_Show(View view) {
        String s="";

        s=String.valueOf(dp.getDayOfMonth())+"-"+String.valueOf(dp.getMonth())+"-"+String.valueOf(dp.getYear());
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
