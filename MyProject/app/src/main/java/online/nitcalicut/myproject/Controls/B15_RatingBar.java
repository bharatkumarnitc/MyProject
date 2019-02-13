package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B15_RatingBar extends AppCompatActivity {
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b15__rating_bar);

        rb=(RatingBar)findViewById(R.id.B15_rb);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(B15_RatingBar.this, String.valueOf(rating),Toast.LENGTH_LONG).show();
            }
        });
    }
}
