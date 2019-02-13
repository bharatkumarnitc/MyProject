package online.nitcalicut.myproject.ASPApi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import online.nitcalicut.myproject.R;

public class ASP3_Register extends AppCompatActivity {
    EditText txtRegName, txtRegMobile, txtRegEmail, txtRegPassword, txtRegstatus;
    String strRegName, strRegMobile, strRegEmail, strRegPassword, strRegstatus="1";
    String ResponseText;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asp3__register);


        txtRegName = (EditText) findViewById(R.id.ASP3_txt_Name);
        txtRegMobile= (EditText) findViewById(R.id.ASP3_txt_Mobile);
        txtRegEmail= (EditText) findViewById(R.id.ASP3_txt_Email);
        txtRegPassword= (EditText) findViewById(R.id.ASP3_txt_Password);
    }

    public void ASP3_fun_Continue(View v) {
        strRegName = txtRegName.getText().toString();
        strRegEmail = txtRegEmail.getText().toString();
        strRegMobile = txtRegMobile.getText().toString();
        strRegPassword = txtRegPassword.getText().toString();

        if (!strRegName.isEmpty() && !strRegName.toString().equals("")) {
            (new AsyncCallWS()).execute();
        }
    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            ResponseText = ASP_WebAPI.ApiRegister(strRegName, strRegMobile, strRegEmail, strRegPassword, strRegstatus, "Register");
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
            pDialog = new ProgressDialog(ASP3_Register.this);
            pDialog.setMessage("Registering ");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }
}