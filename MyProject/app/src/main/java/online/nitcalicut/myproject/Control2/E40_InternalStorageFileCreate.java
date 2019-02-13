package online.nitcalicut.myproject.Control2;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import online.nitcalicut.myproject.R;

public class E40_InternalStorageFileCreate extends AppCompatActivity {
    EditText edt_write;
    TextView txt_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e40__internal_storage_file_create);
        edt_write = (EditText) findViewById(R.id.E40_edt);
        txt_read = (TextView) findViewById(R.id.E40_txt);
        askForPermission("android.permission.WRITE_EXTERNAL_STORAGE", 1);
    }

    public void E40_btn_write(View view) {
        String string = edt_write.getText().toString();
        try {

            FileOutputStream fos = openFileOutput("sample.txt", MODE_PRIVATE);
            OutputStreamWriter opw = new OutputStreamWriter(fos);
            opw.write(string);
            opw.close();
            Toast.makeText(this, "File Saved", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e("Error", "IOException");
        }
    }

    public void E40_btn_read(View view) {
        try {
            FileInputStream fis = openFileInput("sample.txt");
            InputStreamReader ips = new InputStreamReader(fis);
            char[] array = new char[100];
            int buffersize;
            String content = "";
            while ((buffersize = ips.read(array)) > 0) {
                content += String.copyValueOf(array, 0, buffersize);
            }
            txt_read.setText(content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(E40_InternalStorageFileCreate.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(E40_InternalStorageFileCreate.this, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(E40_InternalStorageFileCreate.this, new String[]{permission}, requestCode);
            } else {
                ActivityCompat.requestPermissions(E40_InternalStorageFileCreate.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }
}


