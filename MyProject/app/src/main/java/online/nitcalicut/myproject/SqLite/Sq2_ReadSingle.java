package online.nitcalicut.myproject.SqLite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import online.nitcalicut.myproject.R;

public class Sq2_ReadSingle extends AppCompatActivity {
    SQLiteDatabase db;
    ListView lst;
    String Name[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq2__read_single);

        lst = (ListView) findViewById(R.id.Sq2_lst);

        db = openOrCreateDatabase("db_Bharat", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_Student (SName varchar(200))");

        fillDetails();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Name);
        lst.setAdapter(adapter);
    }

    private void fillDetails() {
        Cursor cursor;     //record hold
        String qry;
        qry = "select * from tbl_Student";
        cursor = db.rawQuery(qry, null);    //
        int length;
        cursor.moveToLast();
        length = cursor.getCount();
        Name = new String[length];
        cursor.moveToFirst();

        for (int i = 0; i < length; i++) {
            Name[i] = cursor.getString(cursor.getColumnIndex("SName"));
            cursor.moveToNext();
        }


    }
}
