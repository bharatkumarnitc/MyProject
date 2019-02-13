package online.nitcalicut.myproject.PHP;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//import app.shriramdone.SQLServer.Mod8_Album;
//import app.shriramdone.SQLServer.Mod9_Gallery;
//import app.shriramdone.clsConnection;
import online.nitcalicut.myproject.R;

public class PhpM13_Album extends AppCompatActivity {
//    ListView lst;
//    clsConnection c1 = new clsConnection(this);
//    String arTitle[], arPhotoUrl[], arAlbumId[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php_m13__album);

//        lst = (ListView) findViewById(R.id.PHPM13_lst);
//        getJSON("http://naharacademy.com/ShriRam/getAlbum.php");
//    }
//
//    private void getJSON(final String urlWebService) {
//
//        class GetJSON extends AsyncTask<Void, Void, String> {
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected String doInBackground(Void... voids) {
//                try {
//                    URL url = new URL(urlWebService);
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    StringBuilder sb = new StringBuilder();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    String json;
//                    while ((json = bufferedReader.readLine()) != null) {
//                        sb.append(json + "\n");
//                    }
//                    return sb.toString().trim();
//                } catch (Exception e) {
//                    return null;
//                }
//            }

//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
//                try {
//                    loadIntoListView(s);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//        }
//        GetJSON getJSON = new GetJSON();
//        getJSON.execute();
//    }

//    private void loadIntoListView(String json) throws JSONException {
//        JSONArray jsonArray = new JSONArray(json);
//        arTitle= new String[jsonArray.length()];
//        arPhotoUrl= new String[jsonArray.length()];
//        arAlbumId= new String[jsonArray.length()];
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject obj = jsonArray.getJSONObject(i);
//            arTitle[i] = obj.getString("AlbumName");
//            arPhotoUrl[i] = obj.getString("PhotoRul");
//            arAlbumId[i] = obj.getString("AlbumId");
//
//        }
//        CustAdpt adpt = new CustAdpt(this, arTitle, arPhotoUrl, arTitle);
//        lst.setAdapter(adpt);
//    }

//    public class CustAdpt extends ArrayAdapter<String> {
//        String mName[];
//        String mPhotoUrl[];
//        String mAlbumId[];
//        Context mcontext;
//
//        public CustAdpt(Context context, String Lname[], String Lphotourl[], String LalbumId[]) {
//            super(context, R.layout.activity_php_m13__album_row, R.id.PHPM13_txt, Lname);
//            mcontext = context;
//            mName = Lname;
//            mPhotoUrl = Lphotourl;
//            mAlbumId = LalbumId;
//
//        }
//
//        @Override
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
//                    String AlbumId= ((TextView)view).getTag().toString();
//                    Intent i=new Intent(PhpM13_Album.this , Mod9_Gallery.class);
//                    i.putExtra("AlbumId",AlbumId);
//                    startActivity(i);
//                }
//            });
//
//
//            Glide.with(PhpM13_Album.this)
//                    .load(mPhotoUrl[position])
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(vholder.img);
//
//
//            return row;
//        }

//        public class viewHolder {
//            TextView txtname;
//            ImageView img;
//
//            public viewHolder(View v) {
//                txtname = (TextView) v.findViewById(R.id.PHPM13_txt);
//                img = (ImageView) v.findViewById(R.id.PHPM13_img);
//            }
//        }
        }
    }