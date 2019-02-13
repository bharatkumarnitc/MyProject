package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B18_Spinner extends AppCompatActivity {
    Spinner spinner;
    String arrCity[] = {"City1", "City2", "City3", "City4", "City5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b18__spinner);
        spinner = (Spinner) findViewById(R.id.b18_Spinner);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrCity);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=((TextView)view).getText().toString();
                Toast.makeText(B18_Spinner.this,s,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(B18_Spinner.this,"Nothing",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
