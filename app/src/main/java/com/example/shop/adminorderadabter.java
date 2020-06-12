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

public class adminorderadabter extends RecyclerView.Adapter<adabter.MyViewHolder> {
    private Context context;
    private ArrayList<doneorder> requestList;
    private adabter.MyViewHolder holder;
    private int position;
    Fragment fragment ;
    int id ;
    int a ;

    public adminorderadabter(ArrayList<doneorder> citylist, Context context , Fragment f  ) {
        this.requestList = citylist;
        this.context = context;
        this.fragment = f ;

    }
    @NonNull
    @Override
    public adabter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw, parent, false);
        final adabter.MyViewHolder viewHolder=new adabter.MyViewHolder(view);
        return new adabter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adabter.MyViewHolder holder, final int position) {
        final doneorder request = requestList.get(position);
        holder.description_edt.setText(String.valueOf(request.getOrderid()));



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {


                doneorder ob = requestList.get(position);

                    fragment.getFragmentManager().beginTransaction().replace(R.id.fragconn, new delivryfrag(request.getOrderid() , request.getCustid())).addToBackStack(null).commit();







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
