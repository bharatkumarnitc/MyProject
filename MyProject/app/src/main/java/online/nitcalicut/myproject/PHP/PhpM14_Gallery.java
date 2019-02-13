package online.nitcalicut.myproject.PHP;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//import app.shriramdone.SQLServer.Mod9_Gallery;
//import app.shriramdone.clsConnection;
import online.nitcalicut.myproject.R;

public class PhpM14_Gallery extends AppCompatActivity {
    ListView lst;
    //clsConnection c1 = new clsConnection(this);
    //String arTitle[];
    String arPhotoUrl[], arGalleryId[];
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php_m14__gallery);

        lst = (ListView) findViewById(R.id.PHPM13_lst);

        requestQueue = Volley.newRequestQueue(PhpM14_Gallery.this);
        Intent i = getIntent();
        if (i != null) {
            String AlbumId = i.getStringExtra("AlbumId");
            if (AlbumId != "" && AlbumId != null) {
                FillValues(AlbumId);
            }
        }


    }

    private void FillValues(String AlbumId) {


        getDetailsRequest getdetailrequest = new getDetailsRequest(AlbumId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response);

                try {
                    if (new JSONObject(response).getString("Status").equals("Success")) {
                        loadIntoListView(response);
                    } else
                        Toast.makeText(PhpM14_Gallery.this, "Something Has Happened. Please Try Again!", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        requestQueue.add(getdetailrequest);
    }

    public class getDetailsRequest extends StringRequest {

        private static final String REGISTER_URL = "http://naharacademy.com/ShriRam/getGallery.php";
        private Map<String, String> parameters;

        public getDetailsRequest(String RegId, Response.Listener<String> listener) {
            super(Method.POST, REGISTER_URL, listener, null);
            parameters = new HashMap<>();
            parameters.put("AlbumId", RegId);
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return parameters;
        }
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        //arTitle = new String[jsonArray.length()];
        arPhotoUrl = new String[jsonArray.length()];
        arGalleryId = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            //arTitle[i] = obj.getString("Title");
            arPhotoUrl[i] = obj.getString("PhotoRul");
            arGalleryId[i] = obj.getString("GalleryId");

        }
        CustAdpt adpt = new CustAdpt(this, arPhotoUrl, arGalleryId);
        lst.setAdapter(adpt);
    }

    public class CustAdpt extends ArrayAdapter<String> {
        //String mName[];
        String mPhotoUrl[];
        String mGalleryId[];
        Context mcontext;

        public CustAdpt(Context context, String Lphotourl[], String LgalleryId[]) {
            super(context, R.layout.activity_php_m13__album_row, R.id.PHPM13_txt, Lphotourl);
            mcontext = context;
            //mName = Lname;
            mPhotoUrl = Lphotourl;
            mGalleryId = LgalleryId;

        }

        //@Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View row = convertView;
//            viewHolder vholder = null;
//
//            if (row == null) {
//                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(mcontext.LAYOUT_INFLATER_SERVICE);
//                row = inflater.inflate(R.layout.activity_sq18_list_multi_row, parent, false);
//                vholder = new viewHolder(row);
//                row.setTag(vholder);
//            } else {
//                vholder = (viewHolder) row.getTag();
//            }

//            vholder.txtname.setText(mName[position]);
//            vholder.txtname.setTag(mAlbumId[position]);
//            vholder.txtname.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String AlbumId = ((TextView) view).getTag().toString();
//                    Intent i = new Intent(PhpM14_Gallery.this, Mod9_Gallery.class);
//                    i.putExtra("AlbumId", AlbumId);
//                    startActivity(i);
//                }
//            });


//            Glide.with(PhpM14_Gallery.this)
//                    .load(mPhotoUrl[position])
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(vholder.img);
//
//
//            return row;
//        }
//
//        public class viewHolder {
//            //TextView txtname;
//            ImageView img;
//
//            public viewHolder(View v) {
//                //txtname = (TextView) v.findViewById(R.id.PHPM13_txt);
//                img = (ImageView) v.findViewById(R.id.PHPM13_img);
//            }
//        }
//    }
    }
}
