package com.example.shop;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class adminrequestsfrag extends Fragment {

    ArrayList<doneorder> cityList;
    Cursor cu;
    adminorderadabter cityAdabterr;
    int a ;
    public adminrequestsfrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final ShopDataBase shopDataBase  = new ShopDataBase(getContext()) ;
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_adminrequestsfrag, container, false);
        cityList = new ArrayList<>();
        final FragmentActivity c = getActivity();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rr);


        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);



        cu = shopDataBase.getdoneorder();


        Toast.makeText(getContext() , "number is" + cu.getCount() , Toast.LENGTH_SHORT).show();



        for (cu.moveToFirst(); !cu.isAfterLast(); cu.moveToNext()) {
            doneorder object = new doneorder();
            object.custid = (cu.getInt(0));
             object.orderid = (cu.getInt(1));

            cityList.add(object);
        }



       cityAdabterr = new adminorderadabter(cityList, c ,
               this );
       recyclerView.setAdapter(cityAdabterr);






        return view;
    }

}
