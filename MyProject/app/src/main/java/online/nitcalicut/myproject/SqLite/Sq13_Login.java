package online.nitcalicut.myproject.SqLite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class Sq13_Login extends AppCompatActivity {
    EditText etemail, etpass;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq13__login);

        etemail = (EditText) findViewById(R.id.sq13_email);
        etpass = (EditText) findViewById(R.id.sq13_pass);

        db = openOrCreateDatabase("Registerform", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_Register (RegId INTEGER PRIMARY KEY AUTOINCREMENT, Name varhcar(50),Email varchar(50),Password varchar(50),Gender varhcar(50),Mobile varhcar(50))");
    }

    public void sq13_submit(View view) {

        String email,pass,Query;
         email = etemail.getText().toString();
         pass = etpass.getText().toString();

        Query = "select * from tbl_Register WHERE Email='" + email + "' AND Password='" + pass +"'";
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst())
        {
            SharedPreferences pref=getSharedPreferences("MyProject",MODE_PRIVATE);
            SharedPreferences.Editor editor=pref.edit();

            editor.putString("RegId",cursor.getString(cursor.getColumnIndex("RegId")));
            editor.putString("Name",cursor.getString(cursor.getColumnIndex("Name")));
            editor.putBoolean("IsLogin",true);
            editor.commit();

            startActivity(new Intent(this,Sq14_Home.class));

        }
        else {

            Toast.makeText(this, "invalid", Toast.LENGTH_LONG).show();
        }

    }
}



