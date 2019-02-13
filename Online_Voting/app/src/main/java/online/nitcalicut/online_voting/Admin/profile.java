package online.nitcalicut.online_voting.Admin;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class profile extends AppCompatActivity {

TextView tvname,tvemail,tvpass,tvmobile;
RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvname=(TextView)findViewById(R.id.tvname);
        tvemail=(TextView)findViewById(R.id.tvemail);
        tvpass=(TextView)findViewById(R.id.tvpassword);
        tvmobile=(TextView)findViewById(R.id.tvmobile);
        requestQueue=Volley.newRequestQueue(profile.this);

        SharedPreferences pref = getSharedPreferences("Login", MODE_PRIVATE);
        String Email=pref.getString("RegId","");
        Boolean login=pref.getBoolean("IsLogin",false);
        //textView.setText(Email);  //set string in textview we can use setText

RegisterRequest registerRequest=new RegisterRequest(Email, new Response.Listener<String>() {
    @Override
    public void onResponse(String response) {

        Log.i("Response", response);

        try
        {
            if (new JSONObject(response).getString("status").equals("register"))
            {

               tvname.setText(new JSONObject(response).getString("Name"));
               tvemail.setText(new JSONObject(response).getString("Email"));
                tvpass.setText(new JSONObject(response).getString("Password"));
                tvmobile.setText(new JSONObject(response).getString("Mobile"));

                //Toast.makeText(profile.this, "Account Successfully Created", Toast.LENGTH_LONG).show();
            }

            else
                Toast.makeText(profile.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
});

        requestQueue.add(registerRequest);




    }

    public void update(View view) {

        startActivity(new Intent(profile.this,update.class));
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

}
