package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B9_NumberPicker extends AppCompatActivity {
    NumberPicker np;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b9__number_picker);

        np=(NumberPicker)findViewById(R.id.B9_np);
        np.setMinValue(5);
        np.setMaxValue(20);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Toast.makeText(B9_NumberPicker.this, String.valueOf(newVal), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
