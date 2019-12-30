package com.example.hpuser.stockmanager;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewItem extends AppCompatActivity {
    ListView myListView;
    SqliteHelper handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        handler= new SqliteHelper(this,null,null,1);
        myListView= (ListView) findViewById(R.id.ListViewItems);

        ArrayList<Model> doList = handler.viewDatabase();
        RecordListAdapter myAdt = new RecordListAdapter(ViewItem.this,R.layout.row,doList);
        myListView.setAdapter(myAdt);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}