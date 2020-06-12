package com.example.shop;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class showalot extends Fragment {

    String name , cat , price, quan ;
    Bitmap image ;
    TextView tname ,tcat , tprice , tquan ;
    int id ;
    Button delete ;
    int prodid ;

    public showalot() {
        // Required empty public constructor
    }




    public showalot( classallprod request) {

        this.name = request.getName() ;
        this.prodid=request.getId();
        this.cat = request.getCat() ;
        this.price = request.getPrice() ;
        this.quan = request.getQuantity() ;
        this.image = request.getImage() ;
        this.id = request.getId();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_showalot, container, false);



        ImageView imageView ;

        tname = (TextView) view.findViewById(R.id.t1) ;
        tcat = (TextView) view.findViewById(R.id.t2);
        tprice = (TextView) view.findViewById(R.id.t3);
        tquan = (TextView) view.findViewById(R.id.t4);
        delete = (Button) view.findViewById(R.id.del);


        imageView = (ImageView)view.findViewById(R.id.i1) ;




        tname.setText(name);
        tcat.setText(cat);
        tprice.setText(price);
        tquan.setText(quan);

        imageView.setImageBitmap(image);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopDataBase shopDataBase = new ShopDataBase(getContext()) ;
                shopDataBase.deleteprod(prodid);
                Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }
        });




        return  view ;

    }

}
