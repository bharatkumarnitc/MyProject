package online.nitcalicut.myproject.PHP;

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


public class Php1_InsertData extends AppCompatActivity {
    EditText txt_Name,txt_Email,txt_pass;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php1__insert_data);

        txt_Name = (EditText) findViewById(R.id.Php1_txtName);
        txt_Email=(EditText)findViewById(R.id.Php1_txtEmail);
        txt_pass=(EditText)findViewById(R.id.Php1_txtPassword);

        //creating request queue
        requestQueue = Volley.newRequestQueue(Php1_InsertData.this);
    }

    public void Php1_fun_Submit(View view) {
        String PName = txt_Name.getText().toString();
        String Pemail=txt_Email.getText().toString();
        String Pass=txt_pass.getText().toString();

        InsertRequest registerRequest = new InsertRequest(PName,new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                //Log.i("Response", response);

                try
                {
                    if (new JSONObject(response).getString("Status").equals("Success"))
                    {
                        Toast.makeText(Php1_InsertData.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();

                    }
                    else
                        Toast.makeText(Php1_InsertData.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
                }

                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
        requestQueue.add(registerRequest);
    }

    public class InsertRequest extends StringRequest
    {

        private static final String REGISTER_URL = "http://ladduwalaz.com/ShriRam/CategoryAdd.php";
        private Map<String, String> parameters;

        public InsertRequest(String name, Response.Listener<String> listener)
        {
            super(Method.POST, REGISTER_URL, listener, null);
            parameters = new HashMap<>();
            parameters.put("CategoryName", name);

        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return parameters;
        }
    }
}
