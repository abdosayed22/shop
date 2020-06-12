package com.example.shop;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPassword extends AppCompatActivity {
    Button forget;


    EditText e1, e2 , e3;
    ShopDataBase shopDataBase;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        forget = findViewById(R.id.logbtn);
        e1 = findViewById(R.id.email);
        e2 = findViewById(R.id.pass);
        e3 = findViewById(R.id.pin);





        shopDataBase = new ShopDataBase(getApplicationContext());





        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                String pin = e3.getText().toString();

                Cursor cursor = shopDataBase.findmypin(email);

                if (cursor.getCount() > 0) {

                    if (cursor.getString(2).equals(pin)) {

                        shopDataBase.updatepassword(email, password);
                        Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(getApplicationContext(), "Thats Not you !! Wrong Pin", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });


    }
}
