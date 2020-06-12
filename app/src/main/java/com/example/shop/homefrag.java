package com.example.shop;


import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.LOCATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class homefrag extends Fragment  implements LocationListener {


    public static  int id ;
    ArrayList<classallprod> cityList;
    Cursor cu;
    String currentTime ;
    String name , cat , price, quan ;
    Bitmap image ;
    TextView tname ,tcat , tprice , tquan ;
    int idprod ;
    Button button ;
    Location loc  = null;
    public  static  String latitude , longitude ;
    String l, l2 ;


    adabter cityAdabterr;

    int a ;
    public homefrag() {
        // Required empty public constructor
    }
    public homefrag(int id ) {
        this.id = id ;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        final ShopDataBase shopDataBase  = new ShopDataBase(getContext()) ;
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_homefrag, container, false);
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


        int a = 2 ;

        cityAdabterr = new adabter(cityList, c ,
                this ,a ,id, latitude ,longitude  );
        recyclerView.setAdapter(cityAdabterr);






        return view;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
