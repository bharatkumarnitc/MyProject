package online.nitcalicut.online_voting.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class adminregister extends AppCompatActivity {
    EditText adminname, adminemail, adminpass, adminmobile;
    RequestQueue requestQueue;
    String name, email, pass, mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminregister);
        input();
        requestQueue = Volley.newRequestQueue(adminregister.this);

    }

    public void input()
    {
        adminname = (EditText) findViewById(R.id.adname);
        adminemail = (EditText) findViewById(R.id.ademail);
        adminpass = (EditText) findViewById(R.id.adpass);
        adminmobile = (EditText) findViewById(R.id.admobile);

    }

    public void btnadmin(View view) {

      name=adminname.getText().toString();
      email=adminemail.getText().toString();
      pass=adminpass.getText().toString();
      mobile=adminmobile.getText().toString();

      if(name.isEmpty() || email.isEmpty() || pass.isEmpty() || mobile.isEmpty())
      {

          Toast.makeText(this,"Please Enter the Details",Toast.LENGTH_SHORT).show();

      }

      else
      {

        RegisterRequest registerRequest=new RegisterRequest(name,email,pass,mobile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Response", response);

                try
                {
                    if (new JSONObject(response).getString("status").equals("register"))
                    {
                        Toast.makeText(adminregister.this, "Account Successfully Created", Toast.LENGTH_LONG).show();
                    }

                    else if(new JSONObject(response).getString("status").equals("duplicate"))
                    {

                        Toast.makeText(adminregister.this, "Email already Registererrer", Toast.LENGTH_LONG).show();


                    }
                    else
                        Toast.makeText(adminregister.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });

          requestQueue.add(registerRequest);
            }

        }



    public class RegisterRequest extends StringRequest {

        private static final String REGISTER_URL = "https://pneumonic-chase.000webhostapp.com/Online%20Voting/register.php";

        private Map<String, String> parameters;

        public RegisterRequest(String name, String email,String password,String mobile, Response.Listener<String> listener) {
            super(Request.Method.POST, REGISTER_URL, listener, null);
            parameters = new HashMap<>();
            parameters.put("Name",name);
            parameters.put("Email",email);
            parameters.put("Password", password);
            parameters.put("Mobile", mobile);

        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError
        {
            return parameters;
        }
    }



}

