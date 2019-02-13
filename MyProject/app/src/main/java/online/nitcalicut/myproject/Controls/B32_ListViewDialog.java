package online.nitcalicut.myproject.Controls;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import online.nitcalicut.myproject.R;

public class B32_ListViewDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b32__list_view_dialog);
    }

    public void B32_fun_Show(View view) {
        ListView lst=new ListView(this);

        String arrCity[] = {"City1", "City2", "City3", "City4", "City5"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrCity);
        lst.setAdapter(arrayAdapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s=((TextView)view).getText().toString();
                Toast.makeText(B32_ListViewDialog.this, s,Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog.Builder ab=new AlertDialog.Builder(this);

        ab.setView(lst);
        ab.setTitle("This is Title");


        ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(B32_ListViewDialog.this, "OK",Toast.LENGTH_LONG).show();
            }
        });


        ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(B32_ListViewDialog.this, "Cancel",Toast.LENGTH_LONG).show();
            }
        });

        ab.create().show();
    }
}
