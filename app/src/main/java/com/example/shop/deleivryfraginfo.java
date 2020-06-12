package com.example.shop;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class deleivryfraginfo extends Fragment {


    TextView t1 ,t2 ,t3 ,t4  , t5;
    String date , name ,cat , price ;
    String lat , longt ;
    int id ;
int custid , orderid;
FloatingActionButton setloc ;
Button money ;



    public deleivryfraginfo() {
        // Required empty public constructor
    }

    public deleivryfraginfo(Order order  , int custid) {

        this.date = order.getDate() ;
        this.orderid = order.getOrderid();
        this.name = order.s.getName() ;
        this.cat = order.s.getCat() ;
        this.price = order.s.getPrice();
        this.lat = order.getLat();
        this.longt=order.getLongt();
        this.custid=custid;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_deleivryfraginfo, container, false);


        t1 =(TextView)view.findViewById(R.id.tdate) ;
        t2 =(TextView)view.findViewById(R.id.tname) ;
        t3 =(TextView)view.findViewById(R.id.tcat) ;
        t4 =(TextView)view.findViewById(R.id.tprice) ;
        t5 =(TextView)view.findViewById(R.id.tid) ;
        setloc = (FloatingActionButton)view.findViewById(R.id.locbtn);
        money = (Button) view.findViewById(R.id.donebtn);

        final Fragment fragment = this ;
        setloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getActivity() ,MapsActivity.class );
                intent.putExtra("lat" , lat) ;
                intent.putExtra("longt" , longt) ;
                startActivity(intent);


             //   fragment.getFragmentManager().beginTransaction().replace(R.id.fragconn, new BlankFragment2(lat , longt)).addToBackStack(null).commit();


            }
        });


        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Done order and Get Money !!");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ShopDataBase shopDataBase = new ShopDataBase(getContext()) ;
                                shopDataBase.deleteorderfromrequests(orderid);
                                shopDataBase.deleteorderfromaallorders(orderid);
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        //mMap = googleMap;

        // Add a marker in Sydney and move the camera
      //  LatLng sydney = new LatLng(Double.parseDouble(LAT),Double.parseDouble(LONGT));
      //  mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Customer"));
       // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,20));
       // mMap.setBuildingsEnabled(true);
      //  mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);


        t1.setText(date);
        t2.setText(name);
        t3.setText(cat);
        t4.setText(price);
        t5.setText(String.valueOf(custid));







        return  view ;
    }

}
