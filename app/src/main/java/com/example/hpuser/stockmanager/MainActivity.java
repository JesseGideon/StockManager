package com.example.hpuser.stockmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button mybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread myt= new Thread() {
            public void run() {
                try {
                    sleep(1 * 2000);
                    finish();
                    Intent myInt = new Intent(MainActivity.this, Stock_Manager.class);
                    startActivity(myInt);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        myt.start();
    }



}
