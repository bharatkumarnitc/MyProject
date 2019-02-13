package online.nitcalicut.myproject.PHP;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import online.nitcalicut.myproject.R;

public class Php9_SpinnerId extends AppCompatActivity {
    Spinner ddlTrainings;
   // List<SpinnerObject> lstTraining = new ArrayList<SpinnerObject>();

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_php9__spinner_id);
//
//        ddlTrainings = (Spinner) findViewById(R.id.sql10_spn);
//        getJSON("http://naharacademy.com/ShriRam/getCategoryList.php");
//
//    }

//    private void getJSON(final String urlWebService) {
//
//        class GetJSON extends AsyncTask<Void, Void, String> {
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected String doInBackground(Void... voids) {
//                try {
//                    URL url = new URL(urlWebService);
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    StringBuilder sb = new StringBuilder();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    String json;
//                    while ((json = bufferedReader.readLine()) != null) {
//                        sb.append(json + "\n");
//                    }
//                    return sb.toString().trim();
//                } catch (Exception e) {
//                    return null;
//                }
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
//                try {
//                    loadIntoListView(s);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//        }
//        GetJSON getJSON = new GetJSON();
//        getJSON.execute();
//    }

  /*  private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        //String[] heroes = new String[jsonArray.length()];

        lstTraining.clear();
        lstTraining.add(new SpinnerObject(Integer.parseInt("0"), "---Select Category---"));

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            lstTraining.add(new SpinnerObject(Integer.parseInt(obj.getString("CategoryId")),
                    obj.getString("CategoryName")));


        }

        ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(Php9_SpinnerId.this,
                R.layout.spinnerlayout, lstTraining);
        ddlTrainings.setAdapter(dataAdapter);

        ddlTrainings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int s = ((SpinnerObject) ddlTrainings.getSelectedItem()).getId();
                // SelectedTrainingId = String.valueOf(s);
                // Toast.makeText(getApplicationContext(), n, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
*/

   }
}