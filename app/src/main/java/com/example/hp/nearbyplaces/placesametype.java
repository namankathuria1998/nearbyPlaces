package com.example.hp.nearbyplaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class placesametype extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placesametype);

        Intent intent=getIntent();
        String json=intent.getStringExtra("key");
//        ArrayList<EachPlace>results=intent.getParcelableExtra("key");
//        ArrayList<EachPlace>results=intent.getParcelableArrayListExtra("key");
       Gson gson=new Gson();
        OverallResult overallResult=gson.fromJson(json,OverallResult.class);
        ArrayList<EachPlace>results=overallResult.getResults();


        for(EachPlace epobject:results) Log.e("TAG", "onCreate: " + epobject.getName());
        Log.e("TAG", "onCreate: " + "hello hi bye");
        RecyclerView recyclerView=findViewById(R.id.restrecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eachrestadapter adapter=new eachrestadapter(results,this);
        recyclerView.setAdapter(adapter);

//        OkHttpClient client=new OkHttpClient();


//        for(EachPlace ep:results)
//        {
//            Request request=new Request.Builder()
//                      .url("https://maps.googleapis.com/maps/api/place/details/json?placeid=" + ep.getPlace_id() + "&fields=name,rating,formatted_phone_number&key=YOUR_API_KEY")
//                      .build();
//        }
//


    }
}
