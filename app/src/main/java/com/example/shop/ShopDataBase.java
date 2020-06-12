package com.example.shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ShopDataBase  extends SQLiteOpenHelper {
    public  static final String dbname="sfdfsdfsf";
    SQLiteDatabase myfirst;

    public ShopDataBase(@Nullable Context context) {
        super(context, dbname, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("create table forget(id integer primary key ,email TEXT not null , code TEXT not null );");
        db.execSQL("create table user(Custid integer primary key autoincrement ,CustName TEXT not null , UserName TEXT not null , Password TEXT not null ,  Gender TEXT not null , Birthdate TEXT not null ,Job TEXT not null );");
        db.execSQL("create table categories(catid integer primary key autoincrement ,catname TEXT not null );");
       db.execSQL("create table orders(ordid integer primary key autoincrement ,Date TEXT not null , lat TEXT not null ,lng TEXT not null , Custid  integer, FOREIGN  KEY (Custid) REFERENCES  user (Custid));");
        db.execSQL("create table products(prodid integer primary key autoincrement ,proname TEXT not null , price TEXT not null  ,quantity TEXT not null,catid integer , FOREIGN  KEY (catid) REFERENCES  categories (catid));");
       db.execSQL("create table orderdet( quant TEXT not null , ordid,prodid,FOREIGN  KEY (ordid) REFERENCES  orders (ordid), FOREIGN  KEY (prodid) REFERENCES  products (id) );");
        db.execSQL("create table pp( id integer primary key autoincrement , proname TEXT not null  , procat TEXT not null , proprice TEXT not null  ,  proquant TEXT not null  , image blob not null    );");
        db.execSQL("create table doneorders( custid integer  , orderid integer    );");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists pp");
        db.execSQL("drop table if exists forget");
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists doneorders");
       db.execSQL("drop table if exists categories");
        db.execSQL("drop table if exists orders");
        db.execSQL("drop table if exists products");
       db.execSQL("drop table if exists orderdet");


        onCreate(db);
    }
    //this function to insert data
    public long insertdone(int custid,int orderid )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("custid",custid);
        content.put("orderid",orderid);

        long res=db.insert("doneorders",null,content);
        if(res==-1)
        {
            return res;
        }
        else
            return res;
    }
    public long insertdata(String CustName,String UserName ,String Password ,String Gender , String Birthdate ,String Job )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("CustName",CustName);
        content.put("UserName",UserName);
        content.put("Password",Password);
        content.put("Gender",Gender);
        content.put("Birthdate",Birthdate);
        content.put("Job",Job);

        long res=db.insert("user",null,content);
        if(res==-1)
        {
            return res;
        }
        else
            return res;
    }


    public boolean instaddpro(String CustName,String UserName ,String Password ,String Gender , byte [] Birthdate  )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("proname",CustName);
        content.put("procat",UserName);
        content.put("proprice",Password);
        content.put("proquant",Gender);
        content.put("image",Birthdate);


        long res=db.insert("pp",null,content);
        if(res==-1)
        {
            return false;
        }
        else
            return true;
    }
    public long insertorder(String Date,String lat ,String lng ,int Custid  )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("Date",Date);
        content.put("lat",lat);
        content.put("lng",lng);
        content.put("Custid",Custid);




        long res=db.insert("orders",null,content);
        if(res==-1)
        {
            return res;
        }
        else
            return res;
    }


    public boolean insertorderdet(String quan,int orderid ,int prodid   )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("quant",quan);
        content.put("ordid",orderid);
        content.put("prodid",prodid);



        long res=db.insert("orderdet",null,content);
        if(res==-1)
        {
            return false;
        }
        else
            return true;
    }

    public Cursor getemployeename (String name )
    {

        myfirst = getReadableDatabase();
        String [] arg  ={name} ;
        Cursor cursor = myfirst.rawQuery("select * from user where UserName like ?" , arg) ;
        cursor.moveToFirst() ;
        myfirst.close();

        return cursor;


    }




    public void updateprod (int oldid , String newquan)
    {


        String old = String.valueOf(oldid);
        myfirst = getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("proquant",newquan);
        myfirst.update("pp" ,content, "id like ?" , new String[] {old}) ;

        myfirst.close();



    }



    public Cursor getallpro ()
    {


        myfirst = getReadableDatabase();

        String [] rowdetails={"id","proname","procat","proprice" ,"proquant" , "image"};
        Cursor cursor=myfirst.query("pp",rowdetails,null,null,null,null,null);
        cursor.moveToFirst() ;
        myfirst.close();
        return cursor;


    }


    public Cursor getprodwhen (int i)
    {


        myfirst = getReadableDatabase();

        String [] rowdetails={"proname","procat","proprice" ,"proquant" , "image"};
        Cursor cursor=myfirst.query("pp",rowdetails,"id =" + i,null,null,null,null);
        cursor.moveToFirst() ;
        myfirst.close();
        return cursor;


    }
    public Cursor getuserinfo (String i )
    {


        myfirst = getReadableDatabase();

        String [] rowdetails={"CustName","UserName","Password" ,"Gender" , "Job"};
        Cursor cursor=myfirst.query("user",rowdetails,"UserName =" + i,null,null,null,null);
        cursor.moveToFirst() ;
        myfirst.close();
        return cursor;


    }


    public Cursor test (  )
    {


        myfirst = getReadableDatabase();

        String [] rowdetails={"CustName","UserName","Password" ,"Gender" , "Job"};
        Cursor cursor=myfirst.query("user",rowdetails,null,null,null,null,null);
        cursor.moveToFirst() ;
        myfirst.close();
        return cursor;


    }

    public Cursor getorderinfo (int i )
    {


        myfirst = getReadableDatabase();

        Cursor cursor = myfirst.rawQuery("select * from orderdet where ordid =" + i, null) ;
        cursor.moveToFirst() ;
        myfirst.close();
        return cursor;




    }

    public Cursor getmyorder(int custid) {


        myfirst = getReadableDatabase();
        String [] rowdetails={"ordid","Date","lat","lng" };
        Cursor cursor=myfirst.query("orders",rowdetails,"Custid =" + custid,null,null,null,null);
        cursor.moveToFirst() ;
        myfirst.close();
        return cursor;

    }


    public Cursor getdoneorder() {


        myfirst = getReadableDatabase();
        String [] rowdetails={"custid","orderid"};
        Cursor cursor=myfirst.query("doneorders",rowdetails,null,null,null,null,null);
        cursor.moveToFirst() ;
        myfirst.close();
        return cursor;

    }

    public Cursor getorderbyorderid(int orderid) {
        myfirst = getReadableDatabase();
        String [] rowdetails={"Date","lat","lng" };
        Cursor cursor=myfirst.query("orders",rowdetails,"ordid =" + orderid,null,null,null,null);
        cursor.moveToFirst() ;
        myfirst.close();
        return cursor;
    }


    public void deleteorderfromrequests (int id)
    {
        myfirst = getWritableDatabase();
        myfirst.delete("doneorders" , "orderid ='"+ id +"'" , null);
        myfirst.close();
    }


    public void deleteorderfromaallorders (int id)
    {
        myfirst = getWritableDatabase();
        myfirst.delete("orders" , "ordid ='"+ id +"'" , null);
        myfirst.close();
    }
    public void deleteorderfromorderdet (int id)
    {
        myfirst = getWritableDatabase();
        myfirst.delete("orderdet" , "ordid ='"+ id +"'" , null);
        myfirst.close();
    }

    public void deleteprod (int id)
    {
        myfirst = getWritableDatabase();
        myfirst.delete("pp" , "id ='"+ id +"'" , null);
        myfirst.close();
    }

    public long insertpin(String uname, String ucode) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("email",uname);
        content.put("code",ucode);


        long res=db.insert("forget",null,content);
        if(res==-1)
        {
            return res;
        }
        else
            return res;
    }

    public Cursor findmypin(String emails) {
        myfirst = getReadableDatabase();
        String [] arg  ={emails} ;
        Cursor cursor = myfirst.rawQuery("select * from forget where email like ?" , arg) ;
        cursor.moveToFirst() ;
        myfirst.close();

        return cursor;

    }


    public void updatepassword(String email, String password) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();

        content.put("Password",password);


       db.update("user",content,"UserName like ?" ,new String[] {email});



    }

    public  Cursor search_product(String name)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] arg={name};
        Cursor cursor=db.rawQuery("select * from pp where proname like '%"+name+"%';",null);
        int x=cursor.getCount();
        cursor.moveToFirst();
        return cursor;
    }
}
