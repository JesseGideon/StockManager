package com.example.hpuser.stockmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class Delete_Activity extends AppCompatActivity {

    SqliteHelper handler;
    EditText deleteTxt;
    String getTxt;
    SQLiteDatabase SQLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_);
        handler = new SqliteHelper(this,null,null,1);
        deleteTxt= (EditText) findViewById(R.id.deleteTxt);
        getTxt = deleteTxt.getText().toString();

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


    public void deleteClick(View view) {

        if(!(deleteTxt.getText().toString().isEmpty())){
            final AlertDialog.Builder deletDig = new AlertDialog.Builder(this);
            deletDig.setTitle("DELETE RECORD");
            deletDig.setIcon(R.drawable.ic_delete);
            deletDig.setMessage("Sure You Want to Delete Record " + deleteTxt.getText().toString() + "?");
            deletDig.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SQLiteDatabase = handler.getReadableDatabase();
                    handler.delete(deleteTxt.getText().toString(),SQLiteDatabase);
                    deleteTxt.setText("");
                    Toast.makeText(getApplicationContext(),"Record Deleted ",Toast.LENGTH_SHORT).show();
                }
            });
            deletDig.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            deletDig.show();
        }
        else {

            deleteTxt.setError("Most not Be Empty");
        }

    }
}


