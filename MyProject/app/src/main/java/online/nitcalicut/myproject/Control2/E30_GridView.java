package online.nitcalicut.myproject.Control2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class E30_GridView extends AppCompatActivity {
    String[] city = {"aaa", "bbb", "ccc", "ddd", "eee"};
    GridView spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e30__grid_view);

        spn = (GridView) findViewById(R.id.E30_Grd);

        ArrayAdapter<String> adpt = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, city);
        spn.setAdapter(adpt);

        spn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(E30_GridView.this, ((TextView) view).getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
