package online.nitcalicut.myproject.Advance;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import online.nitcalicut.myproject.R;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;


public class H4_GPSLocation extends AppCompatActivity {
    MapView mapView;
    private GoogleMap mMap;
    private H4_GPSTracker mGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h4__gpslocation);

        mapView = (MapView) findViewById(R.id.map);

        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                // Add a marker in Sydney and move the camera
                //LatLng Jaipur = new LatLng(mGPS.getLatitude(), mGPS.getLongitude());
                // mMap.addMarker(new MarkerOptions().position(Jaipur).title("My Current Location"));
                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Jaipur,15));
                mapView.onResume();


            }

        });
        mGPS = new H4_GPSTracker(this);


        TextView text = (TextView) findViewById(R.id.texts);
        if (mGPS.canGetLocation) {
            mGPS.getLocation();
            text.setText("Lat" + mGPS.getLatitude() + "\n" + "Lon" + mGPS.getLongitude());
        } else {
            text.setText("Unabletofind");
            System.out.println("Unable");
        }

    }

}
