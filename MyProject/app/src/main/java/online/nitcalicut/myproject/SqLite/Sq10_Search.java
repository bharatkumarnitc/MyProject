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
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class Sq10_Search extends AppCompatActivity {


    SQLiteDatabase db;
    ListView list;
    String name[];
    String mobile[];
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq10__search);

        list = (ListView) findViewById(R.id.sq10_list);
        searchView = (SearchView) findViewById(R.id.sq10_search);
        db = openOrCreateDatabase("demo", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS details(Name varchar(50),Mobile varchar(50))");
        filllist("");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filllist(query);


                Toast.makeText(Sq10_Search.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(Sq10_Search.this, newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }


    private void filllist(String query) {

        Cursor cursor1;
        String query1;
        if (query.equals(""))
            query1 = "select * from details ";
        else
            query1 = "select * from details where Name LIKE '%" + query + "%'";

        cursor1 = db.rawQuery(query1, null);
        int length1;
        cursor1.moveToLast();
        length1 = cursor1.getCount();
        name = new String[length1];
        mobile = new String[length1];

        cursor1.moveToFirst();

        for (int i = 0; i < length1; i++) {
            name[i] = cursor1.getString(cursor1.getColumnIndex("Name"));
            mobile[i] = cursor1.getString(cursor1.getColumnIndex("Mobile"));
            cursor1.moveToNext();

        }
        CustAdpt custm = new CustAdpt(this, name, mobile);
        list.setAdapter(custm);

    }

    class CustAdpt extends ArrayAdapter<String> {
        String[] mName;
        String[] mCity;
        Context mContext;


        public CustAdpt(Context context, String[] tname, String[] tmobile) {
            super(context, R.layout.activity_sq9__read_multiple_row, R.id.Sq8_txt_Name, tname);


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
                row = inflater.inflate(R.layout.activity_sq9__read_multiple_row, parent, false); //it's set a reference to row
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
