package monster.artificial.bannerlah;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

<<<<<<< HEAD
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
=======
>>>>>>> 7b399b90c8c91ea98f8ca7eb9e4e64348b1bff53
import org.json.JSONException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import monster.artificial.bannerlah.model.MapItem;

<<<<<<< HEAD
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    // Declare a variable for the cluster manager.
    private ClusterManager<MapItem> mClusterManager;
    private GoogleMap mMap;
=======
public class MapActivity extends AppCompatActivity{// implements OnMapReadyCallback {
    // Declare a variable for the cluster manager.
//    private ClusterManager<MapItem> mClusterManager;
//
//    private GoogleMap mMap;
>>>>>>> 7b399b90c8c91ea98f8ca7eb9e4e64348b1bff53

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
<<<<<<< HEAD
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
=======
//        setUpMap();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        setUpMap();
//    }
//
//    @Override
//    public void onMapReady(GoogleMap map) {
//        if (mMap != null) {
//            return;
//        }
//        mMap = map;
//        startDemo();
//    }
//
////    private void setUpClusterer() {
////        // Position the map.
////        getMapAsync(this).moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));
////
////        // Initialize the manager with the context and the map.
////        // (Activity extends context, so we can pass 'this' in the constructor.)
////        mClusterManager = new ClusterManager<MapItem>(this, getMap());
////
////        // Point the map's listeners at the listeners implemented by the cluster
////        // manager.
////        getMap().setOnCameraIdleListener(mClusterManager);
////        getMap().setOnMarkerClickListener(mClusterManager);
////
////        // Add cluster items (markers) to the cluster manager.
////        addItems();
////    }
//
////    private void addItems() {
////
////        // Set some lat/lng coordinates to start with.
////        double lat = 51.5145160;
////        double lng = -0.1270060;
////
////        // Add ten cluster items in close proximity, for purposes of this example.
////        for (int i = 0; i < 10; i++) {
////            double offset = i / 60d;
////            lat = lat + offset;
////            lng = lng + offset;
////            MapItem offsetItem = new MapItem(lat, lng);
////            mClusterManager.addItem(offsetItem);
////        }
////    }
//
//    protected void startDemo() {
//        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));
//
//        mClusterManager = new ClusterManager<MapItem>(this, getMap());
//        getMap().setOnCameraIdleListener(mClusterManager);
//
//        try {
//            readItems();
//        } catch (JSONException e) {
//            Toast.makeText(this, "Problem reading list of markers.", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private void readItems() throws JSONException {
//        InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
//        List<MapItem> items = new MapItemReader().read(inputStream);
//        mClusterManager.addItems(items);
//    }
//
//    private void setUpMap() {
//        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
//    }
//
//    protected GoogleMap getMap() {
//        return mMap;
//    }
}
>>>>>>> 7b399b90c8c91ea98f8ca7eb9e4e64348b1bff53
