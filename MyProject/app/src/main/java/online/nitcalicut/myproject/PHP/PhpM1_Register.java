package online.nitcalicut.myproject.PHP;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


import online.nitcalicut.myproject.R;

public class PhpM1_Register extends AppCompatActivity {
    EditText txtRegName, txtRegMobile, txtRegEmail, txtRegPassword, txtRegstatus;
    private ProgressDialog pDialog;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php_m1__register);

        txtRegName = (EditText) findViewById(R.id.PHPM1_txt_Name);
        txtRegMobile= (EditText) findViewById(R.id.PHPM1_txt_Mobile);
        txtRegEmail= (EditText) findViewById(R.id.PHPM1_txt_Email);
        txtRegPassword= (EditText) findViewById(R.id.PHPM1_txt_Password);

        //creating request queue
        requestQueue = Volley.newRequestQueue(PhpM1_Register.this);
    }

    public void PHPM1_fun_Continue(View view) {
        String PName = txtRegName.getText().toString();
        String Mobile = txtRegMobile.getText().toString();
        String Email = txtRegEmail.getText().toString();
        String Password = txtRegPassword.getText().toString();

        RegisterRequest registerRequest = new RegisterRequest(PName, Mobile, Email, Password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response);

                try
                {
                    if (new JSONObject(response).getString("status").equals("register"))
                    {
                        Toast.makeText(PhpM1_Register.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                        //finish();
                    }
                    else
                        Toast.makeText(PhpM1_Register.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
        requestQueue.add(registerRequest);
    }

    public class RegisterRequest extends StringRequest {

        private static final String REGISTER_URL = "http://ladduwalaz.com/ShriRam/register.php";
        private Map<String, String> parameters;

        public RegisterRequest(String name, String mobile, String email, String password, Response.Listener<String> listener) {
            super(Method.POST, REGISTER_URL, listener, null);
            parameters = new HashMap<>();
            parameters.put("name", name);
            parameters.put("password", password);
            parameters.put("email", email);
            parameters.put("mobile", mobile);

        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError
        {
            return parameters;
        }
    }

}
