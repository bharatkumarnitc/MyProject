package online.nitcalicut.myproject.Control2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class E34_CustomToast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e34__custom_toast);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_e34__custom_toast_row,
                (ViewGroup) findViewById(R.id.E34_toast_layout_root));

        ImageView image = (ImageView) layout.findViewById(R.id.E34_toastimage);
        image.setImageResource(R.drawable.icon1);
        TextView text = (TextView) layout.findViewById(R.id.E34_toasttext);
        text.setText("Hello! This is a custom toast!");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
