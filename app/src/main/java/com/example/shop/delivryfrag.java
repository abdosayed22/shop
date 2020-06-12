package com.example.shop;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class delivryfrag extends Fragment {

    int custid ;
    int orderid ;
    orderadapter cityAdabterr;

    ArrayList<Order> cityList;
    Cursor cu;
    Cursor cue;
    Cursor cuea;
    public delivryfrag() {
        // Required empty public constructor
    }

    public delivryfrag(int id , int custid) {
        orderid = id ;
       this. custid = custid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final ShopDataBase shopDataBase  = new ShopDataBase(getContext()) ;
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_delivryfrag, container, false);
        cityList = new ArrayList<>();
        final FragmentActivity c = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rr);


        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);



        cu = shopDataBase.getorderbyorderid(orderid);



        if(cu.getCount() >0) {


            for (cu.moveToFirst(); !cu.isAfterLast(); cu.moveToNext()) {
                Order object = new Order();
                object.setOrderid(orderid);


                object.setDate(cu.getString(0));
                object.setLat(cu.getString(1));
                object.setLongt(cu.getString(2));
                cue = shopDataBase.getorderinfo(orderid);
                object.setProdid(cue.getInt(2));
                cuea = shopDataBase.getprodwhen(object.getProdid());
                object.s.setName(cuea.getString(0));
                object.s.setCat(cuea.getString(1));
                object.s.setPrice(cuea.getString(2));
                object.s.setQuantity(cuea.getString(3));
                byte[] imasge = cuea.getBlob(4);
                Bitmap bmpp = BitmapFactory.decodeByteArray(imasge, 0, imasge.length);
                object.s.setImage(bmpp);
                cityList.add(object);


            }

        }

        int z =999;
        cityAdabterr = new orderadapter(cityList, c ,
                this  ,custid,z );
        recyclerView.setAdapter(cityAdabterr);






        return view;
    }

}
