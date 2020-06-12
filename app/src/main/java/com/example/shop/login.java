package com.example.shop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    Button login ;
    TextView textView ;

    EditText e1 ,e2 ;
    ShopDataBase shopDataBase ;
    Cursor cursor ;
    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";

    private final String DefaultUnameValue = "";
    private String UnameValue;

    private final String DefaultPasswordValue = "";
    private String PasswordValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.logbtn) ;
        e1 = findViewById(R.id.email) ;
        e2 = findViewById(R.id.pass) ;
        textView = findViewById(R.id.forget) ;

        shopDataBase = new ShopDataBase(getApplicationContext()) ;



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(login.this, ForgetPassword.class);
                startActivity(intent);


            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cursor = shopDataBase.getemployeename(e1.getText().toString());

                e2.setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD);

                //content.put("CustName",CustName);
                //content.put("UserName",UserName);
                //content.put("Password",Password);
                //content.put("Gender",Gender);
               // content.put("Birthdate",Birthdate);
               // content.put("Job",Job);

                if (cursor.getCount() > 0) {

                    if (cursor.getString(3).equals(e2.getText().toString())) {


                        User user = new User();

                        user.id = cursor.getInt(0);
                        user.name = cursor.getString(1);
                        user.username = cursor.getString(2);
                        user.Password = cursor.getString(3);
                        user.gender = cursor.getString(4);
                        user.birthdate = cursor.getString(5);
                        user.job = cursor.getString(6);




                        Intent intent = new Intent(login.this, Profile.class);
                        intent.putExtra("id", user.id);
                        intent.putExtra("Name", user.name);
                        intent.putExtra("User", user.username);
                        intent.putExtra("Gender", user.gender);
                        intent.putExtra("Birthdate", user.birthdate);
                        intent.putExtra("Job", user.job);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG).show();
                    }

                }

                else
                {

                    Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG).show();
                }


            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();
        savePreferences();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();

        if (!e1.getText().toString().equals("") && !e2.getText().toString().equals(""))
        {
            cursor = shopDataBase.getemployeename(e1.getText().toString());

            e2.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);

            //content.put("CustName",CustName);
            //content.put("UserName",UserName);
            //content.put("Password",Password);
            //content.put("Gender",Gender);
            // content.put("Birthdate",Birthdate);
            // content.put("Job",Job);

            if (cursor.getCount() > 0) {

                if (cursor.getString(3).equals(e2.getText().toString())) {


                    User user = new User();

                    user.id = cursor.getInt(0);
                    user.name = cursor.getString(1);
                    user.username = cursor.getString(2);
                    user.Password = cursor.getString(3);
                    user.gender = cursor.getString(4);
                    user.birthdate = cursor.getString(5);
                    user.job = cursor.getString(6);




                    Intent intent = new Intent(login.this, Profile.class);
                    intent.putExtra("id", user.id);
                    intent.putExtra("Name", user.name);
                    intent.putExtra("User", user.username);
                    intent.putExtra("Gender", user.gender);
                    intent.putExtra("Birthdate", user.birthdate);
                    intent.putExtra("Job", user.job);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG).show();
                }

            }

            else
            {

                Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG).show();
            }

        }
    }

    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit
        UnameValue = e1.getText().toString();
        PasswordValue = e2.getText().toString();
        System.out.println("onPause save name: " + UnameValue);
        System.out.println("onPause save password: " + PasswordValue);
        editor.putString(PREF_UNAME, UnameValue);
        editor.putString(PREF_PASSWORD, PasswordValue);
        editor.commit();
    }

    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        UnameValue = settings.getString(PREF_UNAME, DefaultUnameValue);
        PasswordValue = settings.getString(PREF_PASSWORD, DefaultPasswordValue);
        e1.setText(UnameValue);
        e2.setText(PasswordValue);
        System.out.println("onResume load name: " + UnameValue);
        System.out.println("onResume load password: " + PasswordValue);
    }
}
