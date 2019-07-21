package com.example.hp.nearbyplaces;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class secondactivity extends AppCompatActivity/* implements LocationListener */{

    ArrayList<Places> arrayList = new ArrayList<>();
    double curlat,curlong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);
//
//        Button btn=findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent=new Intent(getBaseContext(),MapsActivity.class);
//                startActivity(intent);
//            }
//        });


        arrayList.add(new Places(R.drawable.cafe, "cafe"));
        arrayList.add(new Places(R.drawable.gym, "gym"));
        arrayList.add(new Places(R.drawable.restaurants, "restaurant"));
        arrayList.add(new Places(R.drawable.bakery, "bakery"));
        arrayList.add(new Places(R.drawable.atm, "atm"));
        arrayList.add(new Places(R.drawable.hospital, "hospital"));
        arrayList.add(new Places(R.mipmap.ic_maps, "hello"));
        arrayList.add(new Places(R.mipmap.ic_maps, "hello"));
        arrayList.add(new Places(R.mipmap.ic_maps, "hello"));
        arrayList.add(new Places(R.mipmap.ic_maps, "hello"));
        arrayList.add(new Places(R.mipmap.ic_maps, "hello"));
        arrayList.add(new Places(R.mipmap.ic_maps, "hello"));

/*        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                5000,
                25,
                this);

        */
        RecyclerView recyclerView=findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new GridLayoutManager(secondactivity.this,2));
        MyAdapter myAdapter=new MyAdapter(arrayList,this);
        recyclerView.setAdapter(myAdapter);

//
//        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.
//                getInstance();
//
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//
//        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//
//                if (firebaseUser == null) {
//                    Intent loginIntent = AuthUI.getInstance()
//                            .createSignInIntentBuilder()
//                            .setIsSmartLockEnabled(false)
//                            .setAvailableProviders(Arrays.asList(
//                                    new AuthUI.IdpConfig.GoogleBuilder().build(),
////                                    new AuthUI.IdpConfig.FacebookBuilder().build(),
////                                    new AuthUI.IdpConfig.TwitterBuilder().build(),
////                                    new AuthUI.IdpConfig.GitHubBuilder().build(),
//                                    new AuthUI.IdpConfig.EmailBuilder().build(),
//                                    new AuthUI.IdpConfig.PhoneBuilder().build())
//                            )
//                            .build();
//                    startActivity(loginIntent);
//                } else {
//
//
//
//                }
//
//            }
//        });


    }

    @Override
    protected void onStart() {
        super.onStart();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.signout:
                FirebaseAuth.getInstance().signOut();
                break;
        }
        return true;
    }

    /*
    @Override
    public void onLocationChanged(Location location) {
         curlat=location.getLatitude();
         curlong=location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
    */
}
