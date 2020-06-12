package com.example.shop;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ordercardinfo extends Fragment {

    Button buy , delete , admin ;
    TextView t1 ,t2 ,t3 ,t4 ;
    String date , name ,cat , price ;
    int ide ;
    int custid ;
    String quantity ;
    int productid;

    public ordercardinfo() {
        // Required empty public constructor
    }

    public ordercardinfo(Order order , int id) {

        this.date = order.getDate() ;
        this.quantity =order.s.getQuantity();
        this.name = order.s.getName() ;
        this.cat = order.s.getCat() ;
        this.price = order.s.getPrice();
        this.ide = order.getOrderid();
        this.custid = id;
        this.productid = order.prodid ;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_ordercardinfo, container, false);

        buy=(Button)view.findViewById(R.id.btn) ;
        admin=(Button)view.findViewById(R.id.btn3) ;
        delete=(Button)view.findViewById(R.id.btn2) ;
        t1 =(TextView)view.findViewById(R.id.tdate) ;
        t2 =(TextView)view.findViewById(R.id.tname) ;
        t3 =(TextView)view.findViewById(R.id.tcat) ;
        t4 =(TextView)view.findViewById(R.id.tprice) ;

        final int newquan = Integer.valueOf(quantity) +1 ;

        final String NEWQUAN = String.valueOf(newquan) ;

        t1.setText(date);
        t2.setText(name);
        t3.setText(cat);
        t4.setText(price);


        final ShopDataBase shopDataBase = new ShopDataBase(getContext());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Are you Sure to Delete This Product From your Shoping Cart ... !?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                shopDataBase.deleteorderfromaallorders(ide);
                                shopDataBase.deleteorderfromorderdet(ide);
                                shopDataBase.updateprod(productid , NEWQUAN);
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
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("you are welcome ... Done order !!");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                shopDataBase.insertdone(custid,ide) ;
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




   admin.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent i = new Intent(Intent.ACTION_SEND);
           i.setType("message/rfc822");
           i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"abdo.22.wait@gmail.com"});
           i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
           i.putExtra(Intent.EXTRA_TEXT   , "body of email");
           try {
               startActivity(Intent.createChooser(i, "Send mail..."));
           } catch (android.content.ActivityNotFoundException ex) {
               Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
           }
       }
   });





        return  view ;
    }

}
