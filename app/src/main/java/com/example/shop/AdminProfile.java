package com.example.shop;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class AdminProfile extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedfrag = null;
            switch (item.getItemId()) {
                case R.id.adminnavhome:
                    selectedfrag = new adminallproductfrag();
                    break;
                case R.id.adminnavrequests:
                    selectedfrag = new adminrequestsfrag();
                    break;
                case R.id.adminnavadd:
                    selectedfrag = new adminaddfrag();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragconn, selectedfrag).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationn);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragconn , new adminallproductfrag()).commit() ;




    }

}
