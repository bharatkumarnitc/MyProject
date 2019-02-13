package online.nitcalicut.myproject.Control3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;


import online.nitcalicut.myproject.R;

public class F2_ImageSwitcher extends AppCompatActivity {
    String[] TEXTS = { "Background", "XP", "Sky" };
    int[] IMAGES = { R.drawable.a1, R.drawable.a2, R.drawable.a3};
    int mPosition = 0;
    TextSwitcher mTextSwitcher;
    ImageSwitcher mImageSwitcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f2__image_switcher);

        mTextSwitcher = (TextSwitcher) findViewById(R.id.F2_textSwitcher);
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(F2_ImageSwitcher.this);
                textView.setTextSize(18);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });

        mTextSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        mImageSwitcher = (ImageSwitcher) findViewById(R.id.F2_imageSwitcher);
        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(F2_ImageSwitcher.this);
                return imageView;
            }
        });

        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        //mImageSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mImageSwitcher.setInAnimation(in);
        mImageSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        F2_fun_onSwitch(null);
    }

    public void F2_fun_onSwitch(View view) {
        mTextSwitcher.setText(TEXTS[mPosition]);
        mImageSwitcher.setImageResource(IMAGES[mPosition]);
        mPosition = (mPosition + 1) % TEXTS.length;
    }
}
