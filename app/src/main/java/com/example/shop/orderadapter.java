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

public class orderadapter extends  RecyclerView.Adapter<adabter.MyViewHolder> {


    private Context context;
    private ArrayList<Order> requestList;
    private adabter.MyViewHolder holder;
    private int position;
    int custid ;
    Fragment fragment;
    int id;
    int a;
    int z ;

    public orderadapter(ArrayList<Order> citylist, Context context, Fragment f , int id  ,int z) {
        this.requestList = citylist;
        this.context = context;
        this.fragment = f;
        this.custid = id;
        this.z= z;

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
        final Order request = requestList.get(position);
        holder.description_edt.setText(request.s.getName());
        holder.imageView.setImageBitmap(request.s.getImage());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Order ob = requestList.get(position);

                if(z == 999)
                {

                    fragment.getFragmentManager().beginTransaction().replace(R.id.fragconn, new deleivryfraginfo(ob ,custid)).addToBackStack(null).commit();


                }
                else {


                    fragment.getFragmentManager().beginTransaction().replace(R.id.fragcon, new ordercardinfo(ob, custid)).addToBackStack(null).commit();

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
