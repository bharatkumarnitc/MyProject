package online.nitcalicut.myproject.SqLite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class Sq12_Register extends AppCompatActivity {

    EditText etname, etemail, etpass, etmobile;
    RadioGroup rggender;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq12__register);
        etname = (EditText) findViewById(R.id.sq12_name);
        etemail = (EditText) findViewById(R.id.sq12_email);
        rggender = (RadioGroup) findViewById(R.id.sq12_gender);
        etpass = (EditText) findViewById(R.id.sq12_pass);
        etmobile = (EditText) findViewById(R.id.sq12_mobile);

        db = openOrCreateDatabase("Registerform", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_Register (RegId INTEGER PRIMARY KEY AUTOINCREMENT, Name varhcar(50),Email varchar(50),Password varchar(50),Gender varhcar(50),Mobile varhcar(50))");
    }

    public void sq12_submit(View view) {

        String name, email, pass, gender, moble, Query;
        name = etname.getText().toString();
        email = etemail.getText().toString();
        pass = etpass.getText().toString();
        gender = rggender.toString();
        moble = etmobile.getText().toString();

        Query = "select * from demo WHERE Email='" + email + "'";
        Cursor cursor = db.rawQuery(Query, null);

        if (cursor.moveToFirst())
            Toast.makeText(this, "already", Toast.LENGTH_LONG).show();
        else {
            Query = "insert into tbl_Register(Name,Email,Password,Gender,Mobile) VALUES('" + name + "','" + email + "','" + pass + "','" + gender + "','" + moble + "')";
            db.execSQL(Query);
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
            clear();
        }

    }

    private void clear() {
      etname.setText("");
      etemail.setText("");
      etpass.setText("");
      etmobile.setText("");
    }

}
