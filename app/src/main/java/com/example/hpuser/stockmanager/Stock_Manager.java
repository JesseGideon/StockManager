package com.example.hpuser.stockmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Stock_Manager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock__manager);


    }

    public void add_item(View view) {
        Intent myINt = new Intent(this,Add_Items.class);
        startActivity(myINt);
    }

    public void aboutApp(View view) {
        AlertDialog.Builder about = new AlertDialog.Builder(Stock_Manager.this);
        about.setIcon(R.drawable.image000);
        about.setTitle("Stock Manager");
        about.setMessage("  Stock Manager Helps Wholesallers/Retailers,Business Man & woman \n" +
                "keep stock(Records) of Each of there items in a well formated pattern and also get" +
                "" +
                " images of items sold to customers or Consumers.");
        about.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dial = about.create();
        dial.show();
    }

    public void view_item(View view) {
        startActivity(new Intent(Stock_Manager.this,ViewItem.class));
    }

    public void delte_Item(View view) {
        startActivity(new Intent(Stock_Manager.this,Delete_Activity.class));
    }
}
