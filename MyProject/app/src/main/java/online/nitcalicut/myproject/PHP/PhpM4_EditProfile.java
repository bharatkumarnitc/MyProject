package online.nitcalicut.myproject.PHP;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import online.nitcalicut.myproject.R;

public class PhpM4_EditProfile extends AppCompatActivity {
    EditText txtName, txtEmail;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php_m4__edit_profile);

        txtName = (EditText) findViewById(R.id.PhpM4_txtName);
        txtEmail = (EditText) findViewById(R.id.PhpM4_txtEmail);

        //creating request queue
        requestQueue = Volley.newRequestQueue(PhpM4_EditProfile.this);
        FillValues();
    }

    private void FillValues() {
        String RegId = "2";

        getDetailsRequest getdetailrequest = new getDetailsRequest(RegId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response);

                try {
                    if (new JSONObject(response).getString("Status").equals("Success")) {
                        loadIntoListView(response);
                    } else
                        Toast.makeText(PhpM4_EditProfile.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        requestQueue.add(getdetailrequest);
    }

    public class getDetailsRequest extends StringRequest {

        private static final String REGISTER_URL = "http://ladduwalaz.com/ShriRam/getRegistrationByIdNew.php";
        private Map<String, String> parameters;

        public getDetailsRequest(String RegId, Response.Listener<String> listener) {
            super(Method.POST, REGISTER_URL, listener, null);
            parameters = new HashMap<>();
            parameters.put("regid", RegId);
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return parameters;
        }
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        txtName.setText(obj.getString("RegName"));
        txtEmail.setText(obj.getString("RegEmail"));
    }


    public void PhpM4_fun_Submit(View view) {
        String RegId = "2";
        String PName = txtName.getText().toString();
        String Email = txtEmail.getText().toString();

        ProfileUpdateRequest updateRequest = new ProfileUpdateRequest(RegId, PName, Email, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response);

                try {
                    if (new JSONObject(response).getString("status").equals("register")) {
                        Toast.makeText(PhpM4_EditProfile.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                        //finish();
                    } else
                        Toast.makeText(PhpM4_EditProfile.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        requestQueue.add(updateRequest);
    }

    public class ProfileUpdateRequest extends StringRequest {

        private static final String REGISTER_URL = "http://ladduwalaz.com/ShriRam/editprofile.php";
        private Map<String, String> parameters;

        public ProfileUpdateRequest(String regId, String name, String email, Response.Listener<String> listener) {
            super(Method.POST, REGISTER_URL, listener, null);
            parameters = new HashMap<>();
            parameters.put("name", name);
            parameters.put("email", email);
            parameters.put("regid", regId);

        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return parameters;
        }
    }

}
