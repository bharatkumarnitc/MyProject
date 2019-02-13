package com.example.nikhilverma.project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView tv_name;
    Button b;
   // TextView tv_number;
    DatabaseReference db;
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_name=(TextView)findViewById(R.id.name);
        sp=(Spinner)findViewById(R.id.spinner);
       // tv_number=(TextView)findViewById(R.id.phone);
        b=(Button) findViewById(R.id.btn1);
        db=FirebaseDatabase.getInstance().getReference().child("artist");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=tv_name.getText().toString();
                String geners=sp.getSelectedItem().toString();
                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(getApplicationContext(),"Please enter some name",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String id=db.push().getKey();
                    Artist art=new Artist(name,geners,id);
                    db.child(id).setValue(art);
                    Toast.makeText(getApplicationContext(),"Artist added",Toast.LENGTH_SHORT).show();
                }
              //  String number=tv_number.getText().toString();
               // Toast.makeText(getApplicationContext(),name+" "+number,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
