package online.nitcalicut.myproject.Control3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import online.nitcalicut.myproject.R;

import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

public class F4_ViewStub extends AppCompatActivity {
    ViewStub simpleViewStub;
    Button showButton, hideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f4__view_stub);

        simpleViewStub = ((ViewStub) findViewById(R.id.F4_simpleViewStub)); // get the reference of ViewStub
        simpleViewStub.inflate(); // inflate the layout
        showButton = (Button) findViewById(R.id.F4_showButton); // get the reference of show button
        hideButton = (Button) findViewById(R.id.F4_hideButton); // get the reference of hide buttton

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleViewStub.setVisibility(View.VISIBLE);
            }
        });

        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleViewStub.setVisibility(View.GONE);
            }
        });
    }
}

