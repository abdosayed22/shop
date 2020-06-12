package com.example.shop;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button login = findViewById(R.id.login );
        Button register = findViewById(R.id.btnUserSignup );
        Button Adlogin = findViewById(R.id.adlogin );
        Button search = findViewById(R.id.search );
        final ShopDataBase shopDataBase  = new ShopDataBase(getApplicationContext()) ;




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(MainActivity.this , login.class) ;
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , Register.class) ;
                startActivity(intent);
            }
        });
        Adlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(MainActivity.this , AdminProfile.class) ;
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(MainActivity.this , Search.class) ;
                startActivity(intent);
            }
        });


    }
}
