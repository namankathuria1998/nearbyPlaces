package com.example.hp.nearbyplaces;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class eachrestadapter extends RecyclerView.Adapter<eachrestadapter.viewholder> {

   private ArrayList<EachPlace>arrayList;
   private Context context;

    public eachrestadapter(ArrayList<EachPlace> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater lif=LayoutInflater.from(context);
        View myrestview=lif.inflate(R.layout.seclayoutforone,parent,false);
        return new viewholder(myrestview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
          EachPlace curobject=arrayList.get(position);
          holder.restname.setText(curobject.getName());


//           if()
          Picasso.get().load(curobject.getIcon()).into(holder.restimage);

          holder.vicinity.setText(curobject.getVicinity());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewholder extends RecyclerView.ViewHolder
    {
        ImageView restimage;
        TextView restname;
        TextView vicinity;

        public viewholder(View itemView) {
            super(itemView);
            restimage=itemView.findViewById(R.id.restimage);
            restname=itemView.findViewById(R.id.restname);
            vicinity=itemView.findViewById(R.id.vicinity);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  EachPlace curobj=arrayList.get(getAdapterPosition());
                    Gson gson=new Gson();
                    String json=gson.toJson(curobj);
                    Intent i=new Intent(context,eachrestdetails.class);
                    i.putExtra("key",json);
                    context.startActivity(i);
                }
            });
        }



    }
}
