package com.example.shop;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class profilefrag extends Fragment {

    private static final String PREFS_NAME = "preferences";
    String name , user ,  gender ,  birth ,  job;
    public profilefrag(  ) {
        // Required empty public constructor
    }
    public profilefrag(String name ,String user , String gender , String birth , String job  ) {
        this.name = name ;
        this.user = user ;
        this.gender = gender;
        this.birth = birth;
        this.job = job ;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_profilefrag, container, false);

        final Button btn = (Button) view.findViewById(R.id.btn);
        final TextView cusname = (TextView) view.findViewById(R.id.cust_name_profile);
        final TextView cususer = (TextView) view.findViewById(R.id.cust_email_profile);
       final TextView cusgender = (TextView) view.findViewById(R.id.cust_gender_profile);
       final TextView cusbirth = (TextView) view.findViewById(R.id.cust_birthdate_profile);
       final TextView custjob = (TextView) view.findViewById(R.id.cust_job_profile);



          cusname.setText(name);
          cususer.setText(user);
          cusgender.setText(gender);
         cusbirth .setText(birth);
          custjob .setText(job);



          btn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {



                  SharedPreferences settings = getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                  settings.edit().clear().commit();

                  Intent intent = new Intent(getContext() ,MainActivity.class ) ;
                  startActivity(intent);
              }
          });

        return  view ;
    }

}
