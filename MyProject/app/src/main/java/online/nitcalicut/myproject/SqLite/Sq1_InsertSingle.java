package online.nitcalicut.myproject.SqLite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Level;

import online.nitcalicut.myproject.R;

public class Sq1_InsertSingle extends AppCompatActivity {
    SQLiteDatabase db;
    EditText txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq1__insert_single);

        txtName = (EditText) findViewById(R.id.Sq1_txt_Name);

        db = openOrCreateDatabase("db_Bharat", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_Student (SName varchar(200))");
    }

    public void Sq1_fun_Submit(View view)
    {
        String Sql = "", SName = "";

        SName = txtName.getText().toString();
        Sql = "INSERT INTO tbl_Student(SName) VALUES ('" + SName + "')";
        db.execSQL(Sql);

        txtName.setText("");
        Toast.makeText(this,"done", Toast.LENGTH_LONG).show();

    }
}
