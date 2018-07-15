package monster.artificial.bannerlah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setUpMap();
    }

    private void setUpMap() {
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        startDemo();
    }

    protected void startDemo() {
        double v1 = 5.369279;
        double v2 = 100.431960;

        // at Jalan Perda Utama
        for (int i = 0 ; i < 15; i++)
        {
            LatLng Location = new LatLng(v1, v2);
            mMap.addMarker(new MarkerOptions().position(Location).title("Banners"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Location, 15));
            v1 += 0.000075;
            v2 += 0.000250;

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Intent i = new Intent(MapActivity.this, StatusActivity.class);
                    startActivity(i);
                    finish();
                    //Using position get Value from arraylist
                    return false;
                }
            });
        }

        v1 = 5.369074;
        v2 = 100.431397;

        // at Jalan Perda Utama
        for (int i = 0 ; i < 15; i++)
        {
            LatLng Location = new LatLng(v1, v2);
            mMap.addMarker(new MarkerOptions().position(Location).title("Banners"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Location, 15));
            v1 -= 0.000075;
            v2 -= 0.000250;
        }

        // Random points

        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }
}
