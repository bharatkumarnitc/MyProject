package online.nitcalicut.myproject.Control2;

import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import online.nitcalicut.myproject.R;

public class E41_ExternalStorageFileCreate extends AppCompatActivity {
    EditText txtData;
    TextView lblData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e41__external_storage_file_create);

        txtData = (EditText) findViewById(R.id.E41_txtData);
        lblData = (TextView) findViewById(R.id.E41_lblData);

        askForPermission("android.permission.WRITE_EXTERNAL_STORAGE", 1);
        askForPermission("android.permission.READ_EXTERNAL_STORAGE", 2);
    }

    public static boolean isExternalStorageReadOnly() {
        String str = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(str)) {
            return true;
        }
        return false;

    }

    public static boolean isExternalStorage() {
        String str = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(str)) {
            return true;
        }
        return false;

    }

    public void E41_btnSave(View view) {
        String string = txtData.getText().toString();
        if (isExternalStorage()) {

            File dir = new File(Environment.getExternalStorageDirectory()+"/AndroidShriram1");
            dir.mkdirs();
            String pathoffolder = dir.getAbsolutePath();
            try {
                FileOutputStream fos = new FileOutputStream(pathoffolder + "/sample.txt");
                fos.write(string.getBytes());
                fos.close();
                Toast.makeText(this, "File Saved", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public void E41_btn_read(View view) {
        if (isExternalStorage() || isExternalStorageReadOnly()) {
            try {
                FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory()+"/AndroidShriram1" + "/sample.txt");
                BufferedReader buff=new BufferedReader(new InputStreamReader(fis));
                String line,sample="";
                while((line=buff.readLine())!=null){
                    sample+=line;
                }
                lblData.setText(sample);

            } catch (IOException e) {
                e.getMessage();
            }

        }

    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(E41_ExternalStorageFileCreate.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(E41_ExternalStorageFileCreate.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(E41_ExternalStorageFileCreate.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(E41_ExternalStorageFileCreate.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }
}

