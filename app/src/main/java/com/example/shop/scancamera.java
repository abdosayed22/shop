package com.example.shop;


import android.os.Bundle;

import com.google.zxing.Result;

import androidx.appcompat.app.AppCompatActivity;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class scancamera extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView1=new ZXingScannerView(this);
        setContentView(scannerView1);
    }

    @Override
    public void handleResult(Result result) {
        Search.searchtext.setText(result.getText().toString());
        onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        scannerView1.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView1.setResultHandler(this);
        scannerView1.startCamera();
    }


}