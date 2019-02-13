package online.nitcalicut.myproject.PHP;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
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

public class PhpM5_ChangePassword extends AppCompatActivity {
    EditText txtCurrentPass, txtNewPass, txtConfirmPass;
    private ProgressDialog pDialog;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php_m5__change_password);

        txtCurrentPass = (EditText) findViewById(R.id.PhpM5_txtCurrentPass);
        txtNewPass= (EditText) findViewById(R.id.PhpM5_txtNewPass);
        txtConfirmPass= (EditText) findViewById(R.id.PhpM5_txtConfirmPass);

        //creating request queue
        requestQueue = Volley.newRequestQueue(PhpM5_ChangePassword.this);
    }

    public void PhpM5_fun_Submit(View view) {
        String CurrentPass = txtCurrentPass.getText().toString();
        String NewPass = txtNewPass.getText().toString();
        String ConfirmPass = txtConfirmPass.getText().toString();



        SharedPreferences pref=getSharedPreferences("MyProject",MODE_PRIVATE);
        String Regid= pref.getString("RegId","");

        RegisterRequest registerRequest = new RegisterRequest(CurrentPass, NewPass, Regid,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response);

                try
                {
                    if (new JSONObject(response).getString("Status").equals("success"))
                    {
                        Toast.makeText(PhpM5_ChangePassword.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                        //finish();
                    }
                    else
                        Toast.makeText(PhpM5_ChangePassword.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
        requestQueue.add(registerRequest);
    }

    public class RegisterRequest extends StringRequest
    {

        private static final String REGISTER_URL = "http://ladduwalaz.com/ShriRam/changepassword.php";
        private Map<String, String> parameters;

        public RegisterRequest(String CurrentPass, String NewPass, String RegId, Response.Listener<String> listener)
        {
            super(Method.POST, REGISTER_URL, listener, null);
            parameters = new HashMap<>();
            parameters.put("regid",RegId);
            parameters.put("currentpassword", CurrentPass);
            parameters.put("newpassword", NewPass);




        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return parameters;
        }
    }

}
