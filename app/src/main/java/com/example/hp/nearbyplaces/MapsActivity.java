package com.example.hp.nearbyplaces;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback ,LocationListener {

    String type;
    EditText et;
    Button button;
    private GoogleMap mMap;
    ArrayList<Marker> markerArrayList;
    int i;
    EachPlace curobject;

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
//        et=findViewById(R.id.et);
//        button=findViewById(R.id.btn);

        Intent intent=getIntent();
        String json=intent.getStringExtra("key");
        Gson gson=new Gson();
        curobject=gson.fromJson(json,EachPlace.class);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        mMap=googleMap;

        com.example.hp.nearbyplaces.Location curlocation=curobject.getGeometry().getLocation();
        LatLng currest=new LatLng(curlocation.getLat(),curlocation.getLng());

        MarkerOptions markerOptions=new MarkerOptions()
                .title("The place u searched is here")
                .position(currest)
                .draggable(false);

        mMap.addMarker(markerOptions);


       googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currest, 20f));

//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(currest));

        /*
        mMap = googleMap;


        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);

//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                10000,
//                0,
//                this
//                );

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        */
        //
//        LatLng newdelhi=new LatLng(28.6139,77.2090);
//
//        MarkerOptions markerOptions=new MarkerOptions()
//                .position(newdelhi)
//                .draggable(true)
//                .title("hello new delhi");
//
//         googleMap.addMarker(markerOptions);
//
//         googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newdelhi,10f));
//

    }

    @Override
    public void onLocationChanged(final Location location) {

        LatLng mycurrlocation = new LatLng(location.getLatitude(), location.getLongitude());
//
//        MarkerOptions markerOptions = new MarkerOptions()
//                .position(mycurrlocation)
//                .draggable(true)
//                .title(" I am here");
//
//        mMap.addMarker(markerOptions);

        /*
        MarkerOptions markerOptions=new MarkerOptions()
                .title("I am here")
                .position(mycurrlocation)
                .draggable(true);
        mMap.addMarker(markerOptions);

        CircleOptions circleOptions = new CircleOptions()
                .center(mycurrlocation)
                .radius(1000);

        mMap.addCircle(circleOptions);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mycurrlocation, 17.2f));
        */
//        Button btn;
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                type=et.getText().toString();

//
//
//
//        final Request request=new Request.Builder()
//                .url("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + location.getLatitude() + "," + location.getLongitude() + "&radius=1000&type=" + type + "&key=AIzaSyApcu3m9F1zYUGh7j6DJMfOk6dy5hxa2YE")
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String json = response.body().string();
//                Gson gson = new Gson();
//
//                OverallResult overallResult = gson.fromJson(json, OverallResult.class);
//
//                ArrayList<EachPlace> results = overallResult.getResults();
//
//
//                for(EachPlace ep : results)
//                {
//                    LatLng myplace=new LatLng(ep.getGeometry().getLocation().getLat() , ep.getGeometry().getLocation().getLng());
//
//                    final MarkerOptions markerOptions1=new MarkerOptions()
//                            .position(myplace)
//                            .title(ep.getName())
//                            .draggable(false);
//
//
//                    MapsActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            markerArrayList.add(mMap.addMarker(markerOptions1));
//                        }
//                    });
//
//                }
//            }
//        });

//        }
//        });

    }
        @Override
        public void onStatusChanged (String s,int i, Bundle bundle){

        }

        @Override
        public void onProviderEnabled (String s){

        }

        @Override
        public void onProviderDisabled (String s){

        }



}

