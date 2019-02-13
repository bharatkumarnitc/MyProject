package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ZoomButton;

import online.nitcalicut.myproject.R;

public class B33_ZoomButton extends AppCompatActivity {
    ZoomButton zoomPlus, zoomMinus;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b33__zoom_button);

        zoomMinus=(ZoomButton)findViewById(R.id.B33_zoomMinus);
        zoomPlus=(ZoomButton)findViewById(R.id.B33_zoomPlus);
        img=(ImageView) findViewById(R.id.B33_img);

        zoomPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x,y;

                x=img.getScaleX();
                y=img.getScaleY();

                x=x+1;
                        y=y+1;

                        img.setScaleX(x);
                        img.setScaleY(y);

                        }
                        });


                        zoomMinus.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        float x,y;

        x=img.getScaleX();
        y=img.getScaleY();

        if(x>1) {
        x = x - 1;
        y = y - 1;
        }

        img.setScaleX(x);
        img.setScaleY(y);

        }
        });


        }
        }
