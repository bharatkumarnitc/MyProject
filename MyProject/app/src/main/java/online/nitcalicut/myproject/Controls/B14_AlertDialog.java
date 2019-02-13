package online.nitcalicut.myproject.Controls;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B14_AlertDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b14__alert_dialog);
    }

    public void B14_fun_Show(View view) {

        ImageView img=new ImageView(this);
        img.setImageResource(R.drawable.math);
        AlertDialog.Builder ab=new AlertDialog.Builder(this);
        ab.setMessage("This is Message");
        ab.setView(img);
        ab.setTitle("This is Title");


        ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(B14_AlertDialog.this, "OK",Toast.LENGTH_LONG).show();
            }
        });


        ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(B14_AlertDialog.this, "Cancel",Toast.LENGTH_LONG).show();
            }
        });

        ab.create().show();

    }
}
