package online.nitcalicut.myproject.SqLite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class Sq8_MultipleInsert extends AppCompatActivity {
    SQLiteDatabase db;
    EditText txtName, txtMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq8__multiple_insert);

        txtName = (EditText) findViewById(R.id.Sq8_txt_Name);
        txtMobile = (EditText) findViewById(R.id.Sq8_txt_Mobile);
        db = openOrCreateDatabase("db_Bharat", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_StudentNewJi (RollNo INTEGER PRIMARY KEY AUTOINCREMENT, SName varchar(200), Mobile varchar(200))");
    }

    public void Sq8_fun_Submit(View view) {
        String Sql = "", SName = "", Mobile = "";

        SName = txtName.getText().toString();
        Mobile = txtMobile.getText().toString();

        Sql = "INSERT INTO tbl_StudentNewJi(SName,Mobile) VALUES ('" + SName + "','" + Mobile + "')";
        db.execSQL(Sql);

        txtName.setText("");
        Toast.makeText(this, "done", Toast.LENGTH_LONG).show();

    }


}
