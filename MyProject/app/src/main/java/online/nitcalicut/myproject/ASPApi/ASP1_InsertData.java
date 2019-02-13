package online.nitcalicut.myproject.ASPApi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class ASP1_InsertData extends AppCompatActivity {
    EditText txtCategoryName;
    String CategoryName;
    String ResponseText;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asp1__insert_data);

        txtCategoryName = (EditText) findViewById(R.id.ASP1_txtCategoryName);
    }

    public void ASP1_funInsert(View v) {
        CategoryName = txtCategoryName.getText().toString();

        if (!CategoryName.isEmpty() && !CategoryName.toString().equals(""))
        {
            (new AsyncCallWS()).execute();
        }
    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            ResponseText = ASP_WebAPI.ApiInsertData(CategoryName, "InsertCategory");
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }


        }

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(ASP1_InsertData.this);
            pDialog.setMessage("Saving CAtegory");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }
}