package com.example.shop;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    EditText name, email ,gender ,date,password,job , code;
    Button signup ;
    String uname, uemail ,ugender,udate,upassword,ujob , ucode;
    ShopDataBase shopDataBase ;
    Boolean E = false ;
    Cursor cursor ;
     EditText edittext ;
    Calendar myCalendar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup = findViewById(R.id.signup) ;
        name = findViewById(R.id.name) ;
        email = findViewById(R.id.email) ;
        edittext= (EditText) findViewById(R.id.date);

        password = findViewById(R.id.password) ;
        job = findViewById(R.id.job) ;
        gender = findViewById(R.id.gender) ;
        code = findViewById(R.id.code) ;

        shopDataBase = new ShopDataBase(getApplicationContext()) ;
        cursor = shopDataBase.getemployeename(email.getText().toString());

          myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Register.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = shopDataBase.getemployeename(email.getText().toString());
                if(cursor.getCount() == 0) {
                    uname = name.getText().toString();
                    uemail = email.getText().toString();
                    upassword = password.getText().toString();
                    ugender = gender.getText().toString();
                    udate = edittext.getText().toString();
                    ujob = job.getText().toString();
                    ucode = code.getText().toString();

                    ClearData();
                    if (E == true) {
                        Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_LONG).show();
                        E = false;
                    } else {


                        shopDataBase = new ShopDataBase(getApplicationContext());
                        long res = shopDataBase.insertdata(uname, uemail, upassword, ugender, udate, ujob);
                        long rest = shopDataBase.insertpin(uname,ucode);


                        if (res == -1) {
                            Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Success Added" + res, Toast.LENGTH_LONG).show();
                        }
                    }

                }
                else
                {

                    email.setError("Email is Busy ! ..... Pleas Enter Another ");

                }

            }
        });








    }
    public void ClearData() {
        E=false ;

        if (name.getText().toString().equals(""))
        {
            name.setError("Pleas Put Real Name");
            E = true ;
        }

        if (email.getText().toString().equals(""))
        {
            email.setError("Pleas Put Real Email");
            E = true ;
        }

        if (password.getText().toString().equals(""))
        {
            password.setError("Pleas Put Real password");
            E = true ;
        }

        if (gender.getText().toString().equals(""))
        {
            gender.setError("Pleas Put Real gender");
            E = true ;
        }

        if (edittext.getText().toString().equals(""))
        {
            edittext.setError("Pleas Put Real date");
            E = true ;
        }
        if (job.getText().toString().equals(""))
        {
            job.setError("Pleas Put Real job");
            E = true ;
        }
        if (code.getText().toString().equals(""))
        {
            code.setError("Pleas Put Real job");
            E = true ;
        }

    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
}
