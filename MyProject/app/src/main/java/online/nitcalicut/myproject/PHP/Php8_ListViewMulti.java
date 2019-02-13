package online.nitcalicut.myproject.PHP;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;


//import app.shriramdone.SQLServer.Sql8_ListViewMulti;
//import app.shriramdone.clsConnection;
import online.nitcalicut.myproject.R;

public class Php8_ListViewMulti extends AppCompatActivity {
//    ListView lst;
//    clsConnection c1 = new clsConnection(this);
//    String arName[], arMobile[], arEmail[], arPassword[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php8__list_view_multi);

//        lst = (ListView) findViewById(R.id.Php8_lst);
//        getJSON("http://naharacademy.com/ShriRam/getRegistrations.php");
    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
//        arName = new String[jsonArray.length()];
//        arEmail = new String[jsonArray.length()];
//        arMobile = new String[jsonArray.length()];
//        arPassword = new String[jsonArray.length()];
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject obj = jsonArray.getJSONObject(i);
//            arName[i] = obj.getString("RegName");
//            arEmail[i] = obj.getString("RegEmail");
//            arMobile[i] = obj.getString("RegMobile");
//            arPassword[i] = obj.getString("RegPassword");
//        }
//        CustAdpt adpt = new CustAdpt(this, arName, arMobile, arEmail, arPassword);
//        lst.setAdapter(adpt);
    }

//    public class CustAdpt extends ArrayAdapter<String> {
//        String name[];
//        String mob[];
//        String email[];
//        String pass[];
//        Context tcontext;
//
//        public CustAdpt(Context context, String Lname[], String Lmob[], String Lemail[], String Lpass[]) {
//            super(context, R.layout.activity_sq18_list_multi_row, R.id.sq18_row_email, Lemail);
//            tcontext = context;
//            name = Lname;
//            mob = Lmob;
//            email = Lemail;
//            pass = Lpass;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View row = convertView;
//            viewHolder vholder = null;
//
//            if (row == null) {
//                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(tcontext.LAYOUT_INFLATER_SERVICE);
//                row = inflater.inflate(R.layout.activity_sq18_list_multi_row, parent, false);
//                vholder = new viewHolder(row);
//                row.setTag(vholder);
//            } else {
//                vholder = (viewHolder) row.getTag();
//            }
//            vholder.txtname.setText(name[position]);
//            vholder.txtmobile.setText(mob[position]);
//            vholder.txtemail.setText(email[position]);
//            vholder.txtpass.setText(pass[position]);
//
//
//            return row;
//        }
//
//        public class viewHolder {
//            TextView txtname;
//            TextView txtmobile;
//            TextView txtemail;
//            TextView txtpass;
//
//            public viewHolder(View v) {
//                txtname = (TextView) v.findViewById(R.id.sq18_row_email);
//                txtmobile = (TextView) v.findViewById(R.id.sq18_row_mobile);
//                txtemail = (TextView) v.findViewById(R.id.sq18_row_email);
//                txtpass = (TextView) v.findViewById(R.id.sq18_row_pass);
//            }
//        }
//    }
//
//
}
