package online.nitcalicut.myproject.Control2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import online.nitcalicut.myproject.R;

public class E39_InternalStorageFileCreate extends AppCompatActivity {
    EditText txtFileName,txtData;
    Button btnSave,btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e39__internal_storage_file_create);

        txtFileName=(EditText)findViewById(R.id.E39_txtFileName);
        txtData=(EditText)findViewById(R.id.E39_txtData);
        btnSave=(Button)findViewById(R.id.E39_btnSave);
        btnRead=(Button)findViewById(R.id.E39_btnRead);

        //Performing Action on Read Button
        btnSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String filename=txtFileName.getText().toString();
                String data=txtData.getText().toString();

                FileOutputStream fos;
                try {
                    fos = openFileOutput(filename, Context.MODE_PRIVATE);
                    //default mode is PRIVATE, can be APPEND etc.
                    fos.write(data.getBytes());
                    fos.close();

                    Toast.makeText(getApplicationContext(),filename + " saved",
                            Toast.LENGTH_LONG).show();


                } catch (FileNotFoundException e) {e.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}

            }

        });

        //Performing Action on Read Button
        btnRead.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String filename=txtFileName.getText().toString();
                StringBuffer stringBuffer = new StringBuffer();

                try {
                    //Attaching BufferedReader to the FileInputStream by the help of InputStreamReader
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput(filename)));
                    String inputString;
                    //Reading data line by line and storing it into the stringbuffer
                    while ((inputString = inputReader.readLine()) != null) {
                        stringBuffer.append(inputString + "\n");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Displaying data on the toast
                Toast.makeText(getApplicationContext(),stringBuffer.toString(),
                        Toast.LENGTH_LONG).show();

            }

        });
    }

}
