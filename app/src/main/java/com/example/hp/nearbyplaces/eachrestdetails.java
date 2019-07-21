package com.example.hp.nearbyplaces;

import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class eachrestdetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eachrestdetails);

        Intent intent=getIntent();
        String json=intent.getStringExtra("key");
        final Gson gson=new Gson();
        final EachPlace curobj =  gson.fromJson(json,EachPlace.class);

//        LocationManager locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);


        ArrayList<photoobject>photos=curobj.getPhotos();
        Log.e("TAG", "onCreate: "+ photos
        );

        TextView name=findViewById(R.id.name);
        TextView restname=findViewById(R.id.restname);
        if(photos!=null)
        {
            photoobject myobject = photos.get(0);
            String photoref = myobject.getPhoto_reference();
            double maxwidth = myobject.getWidth();

            ImageView placephoto = findViewById(R.id.placephoto);
            String photostring = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photoref + "&key=AIzaSyApcu3m9F1zYUGh7j6DJMfOk6dy5hxa2YE";
            Picasso.get().load(Uri.parse(photostring)).into(placephoto);

            float myrating=curobj.getRating();
            RatingBar ratingBar=findViewById(R.id.ratingbar);
            ratingBar.setRating(myrating);

            name.setText("Overall Rating:- " + myrating);
            restname.setText(curobj.getName());
        }

         final ImageView inmap=findViewById(R.id.inmap);
         inmap.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                Intent mapintent=new Intent(eachrestdetails.this,MapsActivity.class);
                String mapjson=gson.toJson(curobj);
                mapintent.putExtra("key",mapjson);
                eachrestdetails.this.startActivity(mapintent);
             }
         });

//
//         final newouterobject newobject=new newouterobject();

        final ImageView dialphone=findViewById(R.id.dial);
//        newouterobject actualobject;

//


//
//        dialphone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                 startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("  ")));
//                Intent intnt=new Intent();
//                intnt.setAction(Intent.ACTION_VIEW);
//                intnt.setData(Uri.parse("https://www.youtube.com/watch?v=fN7RlgnTZYQ"));
//                startActivity(intnt);
//            }
//        });

         dialphone.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 OkHttpClient client=new OkHttpClient();
                 Request request=new Request.Builder()
                         .url("https://maps.googleapis.com/maps/api/place/details/json?placeid=" + curobj.getPlace_id() + "&key=AIzaSyApcu3m9F1zYUGh7j6DJMfOk6dy5hxa2YE")
                         .build();
                 client.newCall(request).enqueue(new Callback() {
                     @Override
                     public void onFailure(Call call, IOException e) {
                         Log.e("TAG", "onResponse: "+e.getLocalizedMessage());
                     }

                     @Override
                     public void onResponse(Call call, Response response) throws IOException {
                         String json=response.body().string();
                         final newouterobject newobject=gson.fromJson(json,newouterobject.class);

                         Log.e("TAG", "onResponse: "+ response.body());
                             {
                                 String phoneno = newobject.getResult().getFormatted_phone_number();
                                 Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneno, null));
                                 startActivity(intent);
                             }

                     }
                 });


             }
         });

//
//        OkHttpClient client=new OkHttpClient();
//         Request request=new Request.Builder()
//                 .url("https://maps.googleapis.com/maps/api/place/details/json?placeid=" + curobj.getPlace_id() + "&key=AIzaSyApcu3m9F1zYUGh7j6DJMfOk6dy5hxa2YE")
//                 .build();
//         client.newCall(request).enqueue(new Callback() {
//             @Override
//             public void onFailure(Call call, IOException e) {
//
//             }
//
//             @Override
//             public void onResponse(Call call, Response response) throws IOException {
//                 String json=response.body().string();
//                 final newouterobject newobject=gson.fromJson(json,newouterobject.class);
//
//
//                 dialphone.setOnClickListener(new View.OnClickListener() {
//                     @Override
//                     public void onClick(View view) {
//                         if(newobject.getResult() != null)
//                         {
//                             String phoneno = newobject.getResult().getFormatted_phone_number();
//                             Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneno, null));
//                             startActivity(intent);
//                         }
//                     }
//                 });

//             }
//         });


    }
}
