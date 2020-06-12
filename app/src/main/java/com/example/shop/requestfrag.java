package com.example.shop;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import static android.content.Context.LOCATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class requestfrag extends Fragment   implements LocationListener {
    String currentTime ;
    String name , cat , price, quan ;
    Bitmap image ;
    TextView tname ,tcat , tprice , tquan ;
    int idprod ;
    Button button ;
    Location loc  = null;
    String latitude , longitude ;
    String l, l2 ;
    int ide ;

    public requestfrag() {
        // Required empty public constructor
    }




    public requestfrag( classallprod request , int ide ,String latitude ,String longitude) {

        this.name = request.getName() ;
        this.cat = request.getCat() ;
        this.price = request.getPrice() ;
        this.quan = request.getQuantity() ;
        this.image = request.getImage() ;
        this.idprod = request.getId();
        this.ide = ide ;
        this.latitude = latitude ;
        this.longitude =longitude ;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        LocationManager locationManager = (LocationManager) getContext()
                .getSystemService(LOCATION_SERVICE);

        // getting GPS status
        boolean checkGPS = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        boolean  checkNetwork = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!checkGPS && !checkNetwork) {
            Toast.makeText(getContext(), "No Service Provider Available", Toast.LENGTH_SHORT).show();
        } else {

            // First get location from Network Provider
            if (checkNetwork) {
                Toast.makeText(getContext(), "Network", Toast.LENGTH_SHORT).show();

                try {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            6000,
                            6000, this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        loc = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    }

                    if (loc != null) {

                        latitude=String.valueOf(loc.getLatitude()) ;
                        longitude =String.valueOf(loc.getLongitude()) ;
                        Toast.makeText(getContext(), latitude + longitude , Toast.LENGTH_SHORT).show();
                    }
                } catch (SecurityException e) {

                }
            }
        }
        // if GPS Enabled get lat/long using GPS Services
        if (checkGPS) {
            Toast.makeText(getContext(), "GPS", Toast.LENGTH_SHORT).show();
            if (loc == null) {
                try {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            6000,
                            6000, this);
                    Log.d("GPS Enabled", "GPS Enabled");
                    if (locationManager != null) {
                        loc = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null) {
                            latitude=String.valueOf(loc.getLatitude()) ;
                            longitude =String.valueOf(loc.getLongitude()) ;

                            Toast.makeText(getContext(), latitude + longitude , Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (SecurityException e) {

                }
            }
        }
        // Inflate the layout for this fragment

        final ProgressDialog dialog = ProgressDialog.show(getContext(), "","Loading..Wait.." , true);
        dialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //your code here
                dialog.dismiss();
            }
        }, 10000);

        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_requestfrag, container, false);



        final ShopDataBase shopDataBase = new ShopDataBase(getContext()) ;
        ImageView imageView ;

        tname = (TextView) view.findViewById(R.id.t1) ;
        tcat = (TextView) view.findViewById(R.id.t2);
        tprice = (TextView) view.findViewById(R.id.t3);
        tquan = (TextView) view.findViewById(R.id.t4);
        button = (Button) view.findViewById(R.id.button);



        imageView = (ImageView)view.findViewById(R.id.i1) ;




        tname.setText(name);
        tcat.setText(cat);
        tprice.setText(price);
        tquan.setText(quan);
        imageView.setImageBitmap(image);

         currentTime = Calendar.getInstance().getTime().toString();

         final int newquan = Integer.valueOf(quan) - 1 ;

         final String NEWQUAN = String.valueOf(newquan) ;



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Add to your Card !!");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                long x =shopDataBase.insertorder(currentTime,latitude ,longitude ,ide ) ;
                                int orderid = (int) x ;
                                shopDataBase.insertorderdet("1" ,orderid , idprod ) ;
                                shopDataBase.updateprod(idprod , NEWQUAN);
                                Toast.makeText(getContext() , "Success order show your card" ,Toast.LENGTH_SHORT).show();
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



        return  view ;

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

    @Override
    public void onResume() {
        super.onResume();

    }
}
