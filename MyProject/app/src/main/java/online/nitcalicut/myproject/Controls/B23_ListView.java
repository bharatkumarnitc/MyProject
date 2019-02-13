package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B23_ListView extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b23__list_view);

        listView=(ListView)findViewById(R.id.B23_lst);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               String s=((TextView)view).getText().toString();

                Toast.makeText(B23_ListView.this,s,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
