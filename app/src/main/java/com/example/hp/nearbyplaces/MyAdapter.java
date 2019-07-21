package com.example.hp.nearbyplaces;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewholder> implements LocationListener {


    ArrayList<Places> arrayList = new ArrayList<>();
    double curlat, curlong;
    GoogleMap Map;
    Context context;


    OkHttpClient client = new OkHttpClient();

    public MyAdapter(ArrayList<Places> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.layoutforone, parent, false);


        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);

        return new viewholder(view);

     /*   if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return TODO;
        }
*/    }


    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        Places curobj=arrayList.get(position);

        holder.tv.setText(curobj.getText());
        holder.iv.setImageDrawable(context.getResources().getDrawable(curobj.getImage()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



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


    class viewholder extends RecyclerView.ViewHolder
    {
        ImageView iv;
        TextView tv;

        public viewholder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.image);
            tv=itemView.findViewById(R.id.text);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Places curobj=arrayList.get(getAdapterPosition());
                   String type=curobj.getText();

                   Request request=new Request.Builder()
                           .url("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + curlat + "," + curlong + /*28.6213 + "," + 77.0613 + */"&radius=1500&type=" + type + "&key=AIzaSyApcu3m9F1zYUGh7j6DJMfOk6dy5hxa2YE")
                           .build();

                   client.newCall(request).enqueue(new Callback() {
                       @Override
                       public void onFailure(Call call, IOException e) {

                       }

                       @Override
                       public void onResponse(Call call, Response response) throws IOException {

                           String json=response.body().string();
                           Gson gson=new Gson();
//                           OverallResult overallResult=gson.fromJson(json,OverallResult.class);
//                           ArrayList<EachPlace>results=overallResult.getResults();

                           Intent intent=new Intent(context,placesametype.class);
                           intent.putExtra("key",json);
                           context.startActivity(intent);

                       }
                   });

               }
           });
        }
    }
}
