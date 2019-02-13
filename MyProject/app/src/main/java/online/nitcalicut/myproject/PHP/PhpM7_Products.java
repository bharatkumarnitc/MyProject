package online.nitcalicut.myproject.PHP;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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

public class PhpM7_Products extends AppCompatActivity {
    ListView lst;
    //clsConnection c1 = new clsConnection(this);
    String arProductName[], arProductPrice[], arProductRating[],arproductId[];
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php_m7__products);
        lst = (ListView) findViewById(R.id.PhpM7_lst);
        getJSON("http://ladduwalaz.com/ShriRam/getProducts.php");

        requestQueue = Volley.newRequestQueue(PhpM7_Products.this);
    }






    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        arProductName = new String[jsonArray.length()];
        arProductPrice = new String[jsonArray.length()];
        arProductRating = new String[jsonArray.length()];
        arproductId=new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            arProductName[i] = obj.getString("ProductName");
            arProductPrice[i] = obj.getString("Price");
            arProductRating[i] = obj.getString("Rating");
            arproductId[i] = obj.getString("ProductId");
        }
        CustAdpt adpt = new CustAdpt(this, arProductName, arProductRating, arProductPrice,arproductId);
        lst.setAdapter(adpt);
    }

    public class CustAdpt extends ArrayAdapter<String> {
        String sproductName[];
        String sproductPrice[];
        String sproductRating[];
        String sproductId[];
        Context tcontext;
        public CustAdpt(Context context, String Lproductname[], String LproductPrice[], String LproductRating[],String LproductId[]) {
            super(context, R.layout.activity_php_m7_products_row, R.id.phpM7_row_Productname, Lproductname);
            tcontext = context;
            sproductName = Lproductname;
            sproductPrice = LproductPrice;
            sproductRating = LproductRating;
            sproductId = LproductId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            viewHolder vholder = null;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(tcontext.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.activity_php_m7_products_row, parent, false);
                vholder = new viewHolder(row);
                row.setTag(vholder);
            } else {
                vholder = (viewHolder) row.getTag();
            }
            vholder.txtProductName.setText(sproductName[position]);
            vholder.txtProductPrice.setText(sproductPrice[position]);
            vholder.txtProductrating.setText(sproductRating[position]);
            vholder.txtProductId.setText(sproductId[position]);
            vholder.AddToCart.setTag(sproductId[position]);
            vholder.AddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sql, Pid="";
                    Pid=((TextView)view).getTag().toString();
                    AddToCart(Pid, "2", "5");

                }
            });

            return row;
        }

        public class viewHolder {
            TextView txtProductName;
            TextView txtProductPrice;
            TextView txtProductrating;
            TextView txtProductId;
            Button   AddToCart;

            public viewHolder(View v) {
                AddToCart=(Button) v.findViewById(R.id.phpM7_row_btn_AddToCart);
                txtProductName = (TextView) v.findViewById(R.id.phpM7_row_Productname);
                txtProductPrice = (TextView) v.findViewById(R.id.phpM7_row_ProductPrice);
                txtProductrating = (TextView) v.findViewById(R.id.phpM7_row_ProductRating);
                txtProductId = (TextView) v.findViewById(R.id.phpM7_row_ProductId);
            }
        }
    }


    public void AddToCart(String ProductId, String RegId, String Price) {

        AddToCartRequest cartRequest = new AddToCartRequest(RegId, ProductId, Price, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response);

                try {
                    if (new JSONObject(response).getString("Status").equals("Success")) {
                        Toast.makeText(PhpM7_Products.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(PhpM7_Products.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        requestQueue.add(cartRequest);
    }

    public class AddToCartRequest extends StringRequest {

        private static final String REGISTER_URL = "http://naharacademy.com/ShriRam/AddToCart.php";
        private Map<String, String> parameters;

        public AddToCartRequest(String RegId, String ProductId, String Price, Response.Listener<String> listener) {
            super(Method.POST, REGISTER_URL, listener, null);
            parameters = new HashMap<>();
            parameters.put("RegId", RegId);
            parameters.put("ProductId", ProductId);
            parameters.put("Price", Price);

        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return parameters;
        }
    }

}
