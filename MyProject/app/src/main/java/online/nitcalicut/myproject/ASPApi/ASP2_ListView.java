package online.nitcalicut.myproject.ASPApi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import online.nitcalicut.myproject.R;

public class ASP2_ListView extends AppCompatActivity {
    ListView lst;
    String ResponseText;
    String[] Categories;

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asp2__list_view);

        lst = (ListView) findViewById(R.id.ASP2_lst);
        (new AsyncCallWS()).execute();

    }


    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            ResponseText = ASP_WebAPI.ApigetCategory("GetCategory");
            try {
                loadIntoListView(ResponseText);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ASP2_ListView.this, android.R.layout.simple_list_item_1, Categories);
            lst.setAdapter(arrayAdapter);
        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(ASP2_ListView.this);
            pDialog.setMessage("Loading Category");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        Categories = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Categories[i] = obj.getString("CategoryName");
        }

    }
}