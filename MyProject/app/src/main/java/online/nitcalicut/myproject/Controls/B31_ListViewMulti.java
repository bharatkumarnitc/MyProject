package online.nitcalicut.myproject.Controls;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B31_ListViewMulti extends AppCompatActivity {
    ListView listView;
    String arrCity[] = {"City1", "City2", "City3", "City4", "City5","City1", "City2", "City3", "City4", "City5","City1", "City2", "City3", "City4", "City5","City1", "City2", "City3", "City4", "City5"};
    String arrName[] = {"NameCity1", "NameCity2", "NameCity3", "NameCity4", "NameCity5","NameCity1", "NameCity2", "NameCity3", "NameCity4", "NameCity5","NameCity1", "NameCity2", "NameCity3", "NameCity4", "NameCity5","NameCity1", "NameCity2", "NameCity3", "NameCity4", "NameCity5"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b31_listviewmulti);

        listView = (ListView) findViewById(R.id.B31_lst);

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrCity);

        CustAdpt arrayAdapter = new CustAdpt(this, arrName, arrCity);
        listView.setAdapter(arrayAdapter);

    }

    public class CustAdpt extends ArrayAdapter<String> {
        String[] mName;
        String[] mCity;
        Context mContext;


        public CustAdpt(Context context, String[] tName, String[] tCity)

        {
            super(context, R.layout.activity_b31_listviewmulti_row, R.id.B31_lbl_Name, tName);

            mName = tName;
            mCity = tCity;
            mContext = context;

        }



        @Override
        public View getView(int postition, View convertView, ViewGroup parent) {
            View row=convertView;
            ViewHolder vHolder=null;

            if(row==null)
            {
                LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(mContext.LAYOUT_INFLATER_SERVICE); //it's convert xml file into object
                row=inflater.inflate(R.layout.activity_b31_listviewmulti_row,parent,false); //it's set a reference to row
                vHolder=new ViewHolder(row);  //It's call a ViewHolder function and pass complete row
                row.setTag(vHolder);
            }
            else
            {
                vHolder=(ViewHolder)row.getTag();
            }

            vHolder.lblName.setText(mName[postition]);
            vHolder.lblCity.setText(mCity[postition]);

            return row;
        }
    }



    public class ViewHolder {
        TextView lblName, lblCity;

        public ViewHolder(View v) {
            lblName= (TextView) v.findViewById(R.id.B31_lbl_Name);
            lblCity = (TextView) v.findViewById(R.id.B31_lbl_City);
        }
    }
}
