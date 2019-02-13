package online.nitcalicut.online_voting.Admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
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

public class update extends AppCompatActivity {

    EditText name,  pass, mobile;
    String Name, Email, Pass, Mobile,LoginEmail;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        SharedPreferences pref = getSharedPreferences("Login", MODE_PRIVATE);
        LoginEmail = pref.getString("RegId", "");
        Boolean login = pref.getBoolean("IsLogin", false);
        requestQueue = Volley.newRequestQueue(update.this);
        input();
        show();

        Name=name.getText().toString();
        Pass=pass.getText().toString();
        Mobile=mobile.getText().toString();


    }

    public void input() {
        name = (EditText) findViewById(R.id.etname);
        pass = (EditText) findViewById(R.id.etpass);
        mobile = (EditText) findViewById(R.id.etmobile);

    }

    public void show()
    {


        RegisterRequest registerRequest=new RegisterRequest(LoginEmail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Response", response);

                try
                {
                    if (new JSONObject(response).getString("status").equals("register"))
                    {
                        name.setText(new JSONObject(response).getString("Name"));
                        pass.setText(new JSONObject(response).getString("Password"));
                        mobile.setText(new JSONObject(response).getString("Mobile"));

                        //Toast.makeText(profile.this, "Account Successfully Created", Toast.LENGTH_LONG).show();
                    }

                    else
                        Toast.makeText(update.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });

        requestQueue.add(registerRequest);



    }

    public void Update(View view) {

        Name=name.getText().toString();
        Pass=pass.getText().toString();
        Mobile=mobile.getText().toString();
        Email=LoginEmail;

        UpdateRegister updateRegister=new UpdateRegister(Name,Email,Pass,Mobile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Response", response);

                try
                {
                    if (new JSONObject(response).getString("status").equals("register"))
                    {
                        Toast.makeText(update.this, "Account Updated Successfully ", Toast.LENGTH_LONG).show();
                    }

                    else if(new JSONObject(response).getString("status").equals("duplicate"))
                    {

                        Toast.makeText(update.this, "Email already Registererrer", Toast.LENGTH_LONG).show();


                    }
                    else
                        Toast.makeText(update.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });

        requestQueue.add(updateRegister);
    }

    public void Profile(View view) {


        startActivity(new Intent(update.this,profile.class));
    }


    public class RegisterRequest extends StringRequest
{

    private static final String REGISTER_URL = "https://pneumonic-chase.000webhostapp.com/Online%20Voting/profile.php";

    private Map<String, String> parameters;

    public RegisterRequest(String email,Response.Listener<String>listener) {
        super(Method.POST, REGISTER_URL,listener,null);

        parameters = new HashMap<>();
        parameters.put("Email",email);
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        return parameters;
    }
}



    public class UpdateRegister extends StringRequest
    {

        private static final String REGISTER_URL = "https://pneumonic-chase.000webhostapp.com/Online%20Voting/update.php";

        private Map<String, String> parameters;

        public UpdateRegister(String name, String email,String password,String mobile, Response.Listener<String> listener) {
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
