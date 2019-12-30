package com.example.hpuser.stockmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.net.wifi.aware.Characteristics;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Manifest;


public class Add_Items extends AppCompatActivity {
    EditText myDate,Newdate;
    EditText itemId, itemName, itemPrice, itemQrt;

    ImageView itemImgFromCam;
    final int REQUEST_IMAGE_CAPTURE = 100;
    final int REQUEST_GALLERY_CAPTURE = 200;
    Button addItems;
    SqliteHelper handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__items);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        itemName = (EditText) findViewById(R.id.ItemName);
        itemId = (EditText) findViewById(R.id.ItemId);
        itemPrice = (EditText) findViewById(R.id.ItemPrice);
        itemQrt = (EditText) findViewById(R.id.ItemQrt);
        Newdate = (EditText) findViewById(R.id.ItemDate);
        handler = new SqliteHelper(this,null,null,1);

        addItems= (Button) findViewById(R.id.AddItem);
        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getItemName=itemName.getText().toString();
                String getId  = itemId.getText().toString();
                 String getPrice  = itemPrice.getText().toString();
                 String getQrt  = itemQrt.getText().toString();
                 String getDate  = Newdate.getText().toString();
                 Bitmap getImg=itemImgFromCam.getDrawingCache();
                if(getItemName.isEmpty() && getId.isEmpty() && getPrice.isEmpty() && getQrt.isEmpty() && getDate.isEmpty()) {

                    itemName.setError("Required");
                    itemId.setError("Required");
                    itemPrice.setError("Required");
                    itemQrt.setError("Required");
                    Newdate.setError("Required");
                    Toast.makeText(getApplicationContext(),"Please Choose an Image",Toast.LENGTH_SHORT);
                }else if(getItemName.isEmpty()){
                    itemName.setError("Required");
                }else if(getId.isEmpty()){
                    itemId.setError("Required");
                }else if(getPrice.isEmpty()){
                    itemPrice.setError("Required");
                }else if(getQrt.isEmpty()){
                    itemQrt.setError("Required");
                }else if( getDate.isEmpty()){
                    Newdate.setError("Required");
                }  else{
                    handler.AddItem(getItemName,getId,getDate,getPrice,getQrt,imageToByte(itemImgFromCam));
                    Toast.makeText(getApplicationContext(), getItemName + " Has been Added Succesfuly", Toast.LENGTH_SHORT).show();
                    itemName.setText("");
                    itemId.setText("");
                    itemPrice.setText("");
                    itemQrt.setText("");
                    Newdate.setText("");
                    itemImgFromCam.setImageResource(R.drawable.ic_camera_alt_black_24dp);
                }
            }
        });
    }

    //setting image to byte
    public static byte[]  imageToByte(ImageView image){
        Bitmap MyBitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        MyBitmap.compress(Bitmap.CompressFormat.JPEG, 100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }
    @Override
    protected void onStart() {
        super.onStart();
        myDate = (EditText) findViewById(R.id.ItemDate);
        myDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog myDatePick = new DateDialog(v);
                    FragmentTransaction fy = getSupportFragmentManager().beginTransaction();
                    myDatePick.show(fy, "DataPicker");
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void clickImg(View view) {
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(Add_Items.this);
        myBuilder.setIcon(R.drawable.ic_camera_alt_black_24dp);
        myBuilder.setTitle("IMAGE OPTIONS");
        CharSequence[] items = new CharSequence[]{"Camera", "Gallery", "Remove Image"};
        myBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
           switch (which) {
                    case 0:

                        itemImgFromCam = (ImageView) findViewById(R.id.ItemImage);
                        //if it has camera
                        if (!hascamera()) {
                            itemImgFromCam.setEnabled(false);
                        } else {

                            //lunching camera in the phone
                            Intent myIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(myIntent, REQUEST_IMAGE_CAPTURE);
                        }
                        break;
                    case 1:
                        ActivityCompat.requestPermissions(Add_Items.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_GALLERY_CAPTURE);
                        Intent galleryIntent = new Intent();
                        galleryIntent.setType("image/*");
                        galleryIntent.setAction(galleryIntent.ACTION_PICK);
                        startActivityForResult(galleryIntent.createChooser(galleryIntent, "Choose Picture"), REQUEST_GALLERY_CAPTURE);
                        break;
                    case 2:
                       itemImgFromCam.setImageResource(R.mipmap.ic_cam);
                       Toast.makeText(getApplication(), "Item Image Removed", Toast.LENGTH_SHORT).show();
                        break;
                   default:
                        break;
                }
            }
        });
        AlertDialog myd = myBuilder.create();
        myd.show();
    }

    //Method to check if the phone has camera in the phone
    public boolean hascamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //storing the image into imageView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==REQUEST_IMAGE_CAPTURE){

                if (requestCode == RESULT_OK) ;
                //getting the photo
                Bundle extra = data.getExtras();
                Bitmap photo = (Bitmap) extra.get("data");
                itemImgFromCam.setImageBitmap(photo);

            }else if(requestCode==REQUEST_GALLERY_CAPTURE){
                if (requestCode == RESULT_OK) ;
                //getting image from gallery
                Uri myuri = data.getData();
                try {
                    InputStream stream = getContentResolver().openInputStream(myuri);
                    Bitmap selectedImg = BitmapFactory.decodeStream(stream);
                    itemImgFromCam.setImageBitmap(selectedImg);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

                }

}