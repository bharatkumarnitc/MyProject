package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B19_ListView extends AppCompatActivity {
    ListView listView;
    String arrCity[] = {"City1", "City2", "City3", "City4", "City5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b19__list_view);
        listView = (ListView) findViewById(R.id.b19_ListView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrCity);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s=((TextView)view).getText().toString();
                Toast.makeText(B19_ListView.this,s,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
