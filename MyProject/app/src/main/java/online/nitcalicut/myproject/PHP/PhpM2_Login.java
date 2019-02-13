package online.nitcalicut.myproject.PHP;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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

public class PhpM2_Login extends AppCompatActivity {
    EditText txtRegMobile, txtRegPassword;
    private ProgressDialog pDialog;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php_m2__login);

        txtRegMobile = (EditText) findViewById(R.id.PHPM2_txt_MobileNo);
        txtRegPassword = (EditText) findViewById(R.id.PHPM2_txt_Password);

        //creating request queue
        requestQueue = Volley.newRequestQueue(PhpM2_Login.this);
    }

    public void PHPM2_fun_Continue(View view) {
        String Mobile = txtRegMobile.getText().toString();
        String Password = txtRegPassword.getText().toString();

        RegisterRequest registerRequest = new RegisterRequest(Mobile, Password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response);

                try {
                    if (new JSONObject(response).getBoolean("success")) {

                        SharedPreferences pref=getSharedPreferences("MyProject",MODE_PRIVATE);
                        SharedPreferences.Editor editor=pref.edit();

                        editor.putString("RegId",new JSONObject(response).getString("RegId"));
                        editor.putBoolean("IsLogin",true);
                        editor.commit();

                        startActivity(new Intent(PhpM2_Login.this, PhpM3_Home.class));
                        //Toast.makeText(PhpM2_Login.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(PhpM2_Login.this, "Password Invalid", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        requestQueue.add(registerRequest);
    }

    public class RegisterRequest extends StringRequest {

        private static final String REGISTER_URL = "http://ladduwalaz.com/ShriRam/login.php";
        private Map<String, String> parameters;

        public RegisterRequest(String mobile, String password, Response.Listener<String> listener) {
            super(Method.POST, REGISTER_URL, listener, null);
            parameters = new HashMap<>();
            parameters.put("password", password);
            parameters.put("mobile", mobile);





        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return parameters;
        }
    }

}
