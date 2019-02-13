package online.nitcalicut.myproject.SqLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class Sq11_AdvanceSearch extends AppCompatActivity {

    EditText edname,edmobile;
    ListView lvshow;
    SQLiteDatabase db;
    String name[];
    String mobile[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq11__advance_search);
        edname=(EditText)findViewById(R.id.sq11_name);
        edmobile=(EditText)findViewById(R.id.sq11_mobile);
        lvshow=(ListView)findViewById(R.id.sq11_showlist);
        db=openOrCreateDatabase("demo",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS details(Name varchar(50),Mobile varchar(50))");

       //filllist("","");

    }



    public void sq11_btnsearch(View view) {

        String sname,snumber;

        sname=edname.getText().toString();
        snumber=edmobile.getText().toString();
        filllist(sname,snumber);

    }
    private void filllist(String tname,String tmobile) {

        Cursor cursor1;
        String query1="";

        if(tname.equals("")&& tmobile.equals(""))
            query1="select * from details";
        else if(!(tname.equals("")) && tmobile.equals(""))
        {
            query1 = "select * from details where Name LIKE '%" + tname + "%'";
        }

        else if(tname.equals("") && !(tmobile.equals("")))
        {
            query1 = "select * from details where Mobile LIKE '%" + tmobile + "%'";
        }
        else if(!(tname.equals("")) && !(tmobile.equals("")))
            query1 = "select * from details where Name LIKE '%" + tname + "%' AND Mobile LIKE '%"+tmobile + "%'";
        cursor1 = db.rawQuery(query1, null);
        int length1=0;
        cursor1.moveToLast();
        length1 = cursor1.getCount();
        if(length1==0)
        {

            Toast.makeText(this,"Data is not present",Toast.LENGTH_LONG).show();
        }
        name = new String[length1];
        mobile = new String[length1];
        cursor1.moveToFirst();

        for (int i = 0; i < length1; i++) {
            name[i] = cursor1.getString(cursor1.getColumnIndex("Name"));
            mobile[i] = cursor1.getString(cursor1.getColumnIndex("Mobile"));
            cursor1.moveToNext();

        }
        CustAdpt custm = new CustAdpt(this, name, mobile);
        lvshow.setAdapter(custm);

    }

    class CustAdpt extends ArrayAdapter<String> {
        String[] mName;
        String[] mCity;
        Context mContext;


        public CustAdpt(Context context, String[] tname, String[] tmobile) {
            super(context, R.layout.activity_sq11__advance_search_row, R.id.sq9_txtname, tname);


            mName = tname;
            mCity = tmobile;
            mContext = context;
        }


        @Override

        public View getView(int Position, View convertview, ViewGroup parent) {
            View row = convertview;
            ViewHolder vHolder = null;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(mContext.LAYOUT_INFLATER_SERVICE); //it's convert xml file into object
                row = inflater.inflate(R.layout.activity_sq11__advance_search_row, parent, false); //it's set a reference to row
                vHolder = new ViewHolder(row);  //It's call a ViewHolder function and pass complete row
                row.setTag(vHolder);
            } else {
                vHolder = (ViewHolder) row.getTag();
            }

            vHolder.tvname.setText(mName[Position]);
            vHolder.tvmobile.setText(mCity[Position]);


            return row;
        }

    }

    public class ViewHolder {
        TextView tvname, tvmobile;

        public ViewHolder(View v) {
            tvname = (TextView) v.findViewById(R.id.sq9_txtname);
            tvmobile = (TextView) v.findViewById(R.id.sq9_txtnumber);
        }
    }


}
