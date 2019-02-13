package online.nitcalicut.online_voting.Admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import online.nitcalicut.online_voting.R;

public class AdminLogin extends AppCompatActivity {

    EditText adminemail, adminpass;
    TextView textView;
    String Email, Password;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        requestQueue = Volley.newRequestQueue(AdminLogin.this);
        input();


        textView = (TextView) findViewById(R.id.adminreigster);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AdminLogin.this, adminregister.class));

            }
        });


    }

    public void input() {

        adminemail = (EditText) findViewById(R.id.adminloginemail);
        adminpass = (EditText) findViewById(R.id.adminloignpass);

    }

    public void adminlogin(View view) {

        Email = adminemail.getText().toString();
        Password = adminpass.getText().toString();

        if (Email.isEmpty() || Password.isEmpty()) {

            Toast.makeText(this, "Please Enter the details", Toast.LENGTH_LONG).show();
        } else {

            RegisterRequest registerRequest = new RegisterRequest(Email, Password, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("Response", response);

                    try {
                        if (new JSONObject(response).getString("status").equals("register")) {


                            SharedPreferences pref = getSharedPreferences("Login", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();

                            editor.putString("RegId", Email);
                            editor.putBoolean("IsLogin", true);
                            editor.commit();
                            startActivity(new Intent(AdminLogin.this, profile.class));
                            //Toast.makeText(AdminLogin.this, "Account Successfully Created", Toast.LENGTH_LONG).show();
                        } else if (new JSONObject(response).getString("status").equals("false")) {

                            Toast.makeText(AdminLogin.this, "You are not register", Toast.LENGTH_LONG).show();


                        } else
                            Toast.makeText(AdminLogin.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            requestQueue.add(registerRequest);

        }
    }

    public class RegisterRequest extends StringRequest {

        private static final String REGISTER_URL = "https://pneumonic-chase.000webhostapp.com/Online%20Voting/Login.php";

        private Map<String, String> parameters;

        public RegisterRequest(String email, String password, Response.Listener<String> listener) {
            super(Request.Method.POST, REGISTER_URL, listener, null);
            parameters = new HashMap<>();
            parameters.put("Email", email);
            parameters.put("Password", password);

        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return parameters;
        }
    }
}


