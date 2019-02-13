package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B20_GridView extends AppCompatActivity {
    GridView gridView;
    String arrCity[] = {"City1", "City2", "City3", "City4", "City5", "City1", "City2", "City3", "City4", "City5", "City1", "City2", "City3", "City4", "City5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b20__grid_view);
        gridView = (GridView) findViewById(R.id.b20_gridView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrCity);
        gridView.setAdapter(arrayAdapter);

       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String s=((TextView)view).getText().toString();
               Toast.makeText(B20_GridView.this,s,Toast.LENGTH_SHORT).show();
           }
       });
    }
}
