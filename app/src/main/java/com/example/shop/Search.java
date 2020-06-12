package com.example.shop;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Search extends AppCompatActivity {
    public  static EditText searchtext;
    Button search_btn,btn_open_camera;

    adabter cityAdabterr;
    ArrayList<classallprod> cityList;
    Cursor cursor1;
    int voicecode=1;
    Button voice_search;
    ArrayAdapter<String>adapter3;




    ShopDataBase ecommerce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        cityList = new ArrayList<>();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rr);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);



        btn_open_camera=(Button)findViewById(R.id.camera_search_id);
        searchtext=(EditText)findViewById(R.id.search_text_id);
        search_btn=(Button)findViewById(R.id.search_btn_activity_search_btn_id);
        voice_search=(Button)findViewById(R.id.search_voice_id);


        ecommerce=new ShopDataBase(this);
        btn_open_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),scancamera.class);
                startActivity(i);
            }
        });


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext=(EditText)findViewById(R.id.search_text_id);

                String prod_name=searchtext.getText().toString();
                Cursor cu=ecommerce.search_product(prod_name);

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


                int a = 3 ;

               cityAdabterr = new adabter(cityList, getApplicationContext() ,
                        a ,homefrag.id, homefrag.latitude ,homefrag.longitude  );
                recyclerView.setAdapter(cityAdabterr);



            }
        });

        voice_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent,voicecode);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==voicecode&&resultCode==RESULT_OK)
        {
            ArrayList<String>result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            searchtext.setText(result.get(0));

            //cursor1=ecommerce.search_product(result.get(0));
            
        }
    }









}
