package online.nitcalicut.myproject.Controls;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ZoomControls;

import online.nitcalicut.myproject.R;

import static android.os.Build.VERSION_CODES.O;

public class B29_ZoomControl extends AppCompatActivity {

    ZoomControls zoomControls;
    ImageView imageView;
    float x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b29__zoom_control);

        imageView=(ImageView)findViewById(R.id.imgid);
        zoomControls=(ZoomControls)findViewById(R.id.zoomcontrol);


        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                x=imageView.getScaleX();
                y=imageView.getScaleY();
                x=x+1;
                y=y+1;
                imageView.setScaleX(x);
                imageView.setScaleY(y);


            }
        });

        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                x=imageView.getScaleX();
                y=imageView.getScaleY();

                if(x>1 && y>1)
                {
                    x=x-1;
                    y=y-1;


                }

                else
                {

                    x=x-1;
                    y=y-1;

                }

                imageView.setScaleX(x);
                imageView.setScaleY(y);

            }
        });

    }
}
