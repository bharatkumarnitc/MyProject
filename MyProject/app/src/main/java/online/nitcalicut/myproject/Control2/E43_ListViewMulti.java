package online.nitcalicut.myproject.Control2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import online.nitcalicut.myproject.R;

public class E43_ListViewMulti extends AppCompatActivity {
    String[] arcity={"Jaipur1","Jaipur2","Jaipur3","Jaipur4","Jaipur5","Jaipur6","Jaipur7"};
    String[] arcity1={"Jaipur1","Jaipur2","Jaipur3","Jaipur4","Jaipur5","Jaipur6","Jaipur7"};
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e43__list_view_multi);

        lst=(ListView)findViewById(R.id.E43_lst);
        CustomAdapter customAdapter=new CustomAdapter(this,arcity,arcity1);
        lst.setAdapter(customAdapter);
    }

    public class CustomAdapter extends ArrayAdapter<String> {
        String[] array1, array12;
        Context mcontext;

        public CustomAdapter(Context context, String[] array1, String[] array12) {
            super(context, R.layout.activity_e43__row, R.id.E43_txt1, array1);
            this.array1 = array1;
            this.array12 = array12;
            mcontext = context;

        }

        @Override
        public View getView(final int pos, View v, ViewGroup parent) {
            View row = v;
            ViewHolder vhold = null;
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(mcontext.LAYOUT_INFLATER_SERVICE);
            if (row == null) {
                row = inflater.inflate(R.layout.activity_e43__row, parent, false);
                vhold = new ViewHolder(row);
                row.setTag(vhold);
            } else {
                vhold = (ViewHolder) row.getTag();
            }

            return row;

        }
    }

    public class ViewHolder {
        TextView textView1, textView2;

        public ViewHolder(View view) {

            textView1 = (TextView) view.findViewById(R.id.E43_txt1);
            textView2 = (TextView) view.findViewById(R.id.E43_txt2);

        }
    }

}

