package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Profile extends AppCompatActivity {

    static    User use  ;
    int id ;
    String Name;
    String User ;
    String Gender ;
    String Birthdate ;
    String Job ;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedfrag = null ;
            switch (item.getItemId()) {
                case R.id.navhome:
                    selectedfrag = new homefrag(id) ;
                    break;
                case R.id.navcard:
                    selectedfrag = new cardfrag(id) ;
                    break;
                case R.id.navprofile:
                    selectedfrag = new profilefrag(Name , User , Gender , Birthdate ,Job) ;
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragcon , selectedfrag).commit() ;
            return true ;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        id =  getIntent().getIntExtra("id" , 1);
       Name =  getIntent().getStringExtra("Name");
         User =getIntent().getStringExtra("User");
         Gender= getIntent().getStringExtra("Gender");
         Birthdate=  getIntent().getStringExtra("Birthdate");
         Job  = getIntent().getStringExtra("Job");


        getSupportFragmentManager().beginTransaction().replace(R.id.fragcon , new homefrag(id)).commit() ;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Profile.this , MainActivity.class) ;
        startActivity(intent);
    }
}
