package com.example.shop;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class adabter extends  RecyclerView.Adapter<adabter.MyViewHolder> {
    private Context context;
    private ArrayList<classallprod> requestList;
    private MyViewHolder holder;
    private int position;
    Fragment fragment ;
    int id ;
    int a ;
    String LAT ,LONGT ;
    public adabter(ArrayList<classallprod> citylist, Context context , Fragment f , int a , int id ,String lat ,String longt ) {
        this.requestList = citylist;
        this.context = context;
        this.fragment = f ;
        this.a = a ;
        this.id = id ;
        this.LAT = lat ;
        this.LONGT = longt ;

    }
    public adabter(ArrayList<classallprod> citylist, Context context , int a , int id ,String lat ,String longt ) {
        this.requestList = citylist;
        this.context = context;
        this.a = a ;
        this.id = id ;
        this.LAT = lat ;
        this.LONGT = longt ;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw, parent, false);
        final MyViewHolder viewHolder=new MyViewHolder(view);
        return new MyViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final classallprod request = requestList.get(position);
        holder.description_edt.setText(request.getName());
        holder.imageView.setImageBitmap(request.getImage());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {


                 classallprod ob = requestList.get(position);
                if(a == 1) {
                    fragment.getFragmentManager().beginTransaction().replace(R.id.fragconn, new showalot(ob)).addToBackStack(null).commit();

                }
                else if (a==2)
                {
                            fragment.getFragmentManager().beginTransaction().replace(R.id.fragcon, new requestfrag(ob , id ,LAT , LONGT)).addToBackStack(null).commit();

                }







            }
        });



    }
    @Override
    public int getItemCount() {
        if (requestList == null) return 0;
        return requestList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView description_edt ;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            description_edt = (TextView) itemView.findViewById(R.id.des_txt);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}
