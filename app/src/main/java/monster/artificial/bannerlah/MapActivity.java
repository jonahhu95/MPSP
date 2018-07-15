package monster.artificial.bannerlah;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import org.json.JSONException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import monster.artificial.bannerlah.model.MapItem;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    // Declare a variable for the cluster manager.
    private ClusterManager<MapItem> mClusterManager;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
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