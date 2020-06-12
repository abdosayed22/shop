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
public class adminallproductfrag extends Fragment {

    ArrayList<classallprod> cityList;
    Cursor cu;

    adabter cityAdabterr;

int a ;
    public adminallproductfrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final ShopDataBase shopDataBase  = new ShopDataBase(getContext()) ;
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_adminallproductfrag, container, false);
        cityList = new ArrayList<>();
        final FragmentActivity c = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rr);


       LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);



          cu = shopDataBase.getallpro();







      for (cu.moveToFirst(); !cu.isAfterLast(); cu.moveToNext()) {
             classallprod object = new classallprod();
             object.setId(cu.getInt(0));
             object.setName(cu.getString(1));
             object.setCat(cu.getString(2));
             object.setPrice(cu.getString(3));
             object.setQuantity(cu.getString(4));
            byte[] imasge = cu.getBlob(5);
           Bitmap bmpp = BitmapFactory.decodeByteArray(imasge, 0, imasge.length);
            object.setImage(bmpp);
             cityList.add(object);
        }


        int a = 1 ;

        cityAdabterr = new adabter(cityList, c ,
                this ,a , 2 ,"" ,"" );
        recyclerView.setAdapter(cityAdabterr);






        return view;
    }

}
