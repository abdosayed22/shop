package com.example.shop;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class adminaddfrag extends Fragment {
    ImageView imageButton ;
    Button addpro ;
    byte[] sora ;
    InputStream inputStream = null;
    EditText ename , ecat , eprice , equant ;
    public adminaddfrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final ShopDataBase shopDataBase  = new ShopDataBase(getContext()) ;


        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_adminaddfrag, container, false);

        imageButton =   (ImageView) view.findViewById(R.id.imageid) ;
        addpro = (Button) view.findViewById(R.id.AddItem) ;
        ename = (EditText)view.findViewById(R.id.name) ;
        ecat = (EditText)view.findViewById(R.id.catname) ;
        eprice = (EditText)view.findViewById(R.id.price) ;
        equant = (EditText)view.findViewById(R.id.quantity) ;


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengallary ();

            }
        });

        addpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean b = shopDataBase.instaddpro(ename.getText().toString() , ecat.getText().toString()  , eprice.getText().toString()  , equant.getText().toString()  , sora) ;
                if(b == true)
                {
                    Toast.makeText(getContext() ,"yessssssssssssssss" , Toast.LENGTH_SHORT ).show();

                }
                else
                {Toast.makeText(getContext() ,"noooo" , Toast.LENGTH_SHORT ).show();}

            }
        });










        return view;
    }



    public  void opengallary ()
    {
        Toast.makeText(getContext() ,"swr" , Toast.LENGTH_SHORT ).show();
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT) ;
        intent.setType("image/*") ;
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(resultCode == RESULT_OK && requestCode == 100)
        {

            Toast.makeText(getContext() ,"gbtha" , Toast.LENGTH_SHORT ).show();

            Uri uri = data.getData() ;





            try {
                inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap decodestream = BitmapFactory.decodeStream(inputStream) ;
                sora =   getBitmapAsByteArray(decodestream) ;
                imageButton.setImageBitmap(decodestream);



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



        }
        else
        {Toast.makeText(getContext() ,"no" , Toast.LENGTH_SHORT ).show();}




    }


    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }
}
