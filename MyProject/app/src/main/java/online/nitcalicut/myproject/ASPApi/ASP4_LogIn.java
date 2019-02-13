package online.nitcalicut.myproject.ASPApi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import online.nitcalicut.myproject.R;

public class ASP4_LogIn extends AppCompatActivity {
    EditText txtRegMobile, txtRegPassword;
    String strRegMobile, strRegPassword;
    String ResponseText;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asp4__log_in);

        txtRegMobile = (EditText) findViewById(R.id.ASP4_txt_MobileNo);
        txtRegPassword = (EditText) findViewById(R.id.ASP4_txt_Password);
    }

    public void ASP4_fun_Continue(View view) {
        strRegMobile = txtRegMobile.getText().toString();
        strRegPassword = txtRegPassword.getText().toString();

        if (!strRegMobile.isEmpty() && !strRegMobile.toString().equals("")) {
            (new AsyncCallWS()).execute();
        }
    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            ResponseText = ASP_WebAPI.ApiLogin(strRegMobile, strRegPassword, "Login");
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
        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(ASP4_LogIn.this);
            pDialog.setMessage("Registering ");
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

        JSONObject obj = jsonArray.getJSONObject(0);
        if(obj != null) {
            String RegId = obj.getString("RegId");
            String RegName = obj.getString("RegName");
            String RegEmail = obj.getString("RegEmail");
            String RegMobile = obj.getString("RegMobile");
            String RegPassword = obj.getString("RegPassword");
            String RegStatus = obj.getString("RegStatus");
        }
    }
}




