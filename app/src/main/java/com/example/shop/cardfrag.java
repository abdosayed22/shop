package com.example.shop;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class cardfrag extends Fragment {

    int custid ;
    orderadapter cityAdabterr;

    ArrayList<Order> cityList;
    Cursor cu;
    Cursor cue;
    Cursor cuea;
    public cardfrag() {
        // Required empty public constructor
    }

    public cardfrag(int id) {
        custid = id ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_cardfrag, container, false);
        cityList = new ArrayList<>();
        final FragmentActivity c = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rr);


        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);

        ShopDataBase shopDataBase  = new ShopDataBase(getContext()) ;

        cu = shopDataBase.getmyorder(custid);


        Toast.makeText(getContext(), "number is " + cu.getCount(), Toast.LENGTH_SHORT).show();


    for (cu.moveToFirst(); !cu.isAfterLast(); cu.moveToNext()) {
        Order object = new Order();
        object.setOrderid(cu.getInt(0));
        object.setDate(cu.getString(1));
        object.setLat(cu.getString(2));
        object.setLongt(cu.getString(3));
        cue = shopDataBase.getorderinfo(object.getOrderid());
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


int z = 0;
        cityAdabterr = new orderadapter(cityList, c ,
             this  ,custid ,z );
       recyclerView.setAdapter(cityAdabterr);






        return view;
    }

}
