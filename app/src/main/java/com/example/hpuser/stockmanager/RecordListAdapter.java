package com.example.hpuser.stockmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

/**
 * Created by HP USER on 07/06/2018.
 */

public class RecordListAdapter extends ArrayAdapter<Model> {

    private Context context;
    private int layout;
    ArrayList<Model> RecordArrayList;
    SqliteHelper handler;
    private static class ViewHolder {
        ImageView img;

        TextView txtName, txtId, txtDate, txtPrice, txtQrt;
    }

    public RecordListAdapter(Context context, int layout, ArrayList<Model> RecordArrayList) {
        super(context, layout, RecordArrayList);
       this.RecordArrayList=RecordArrayList;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       /* String txtName = getItem(position).getItemName();
        int txtId = getItem(position).getItemId();
        String txtDate = getItem(position).getDate();
        String txtPrice = getItem(position).getItemPrice();
        String txtQrt = getItem(position).getItemPrice();
        byte[] img=getItem(position).getImage();

*/
        View result;
        ViewHolder holder;
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView=inflater.inflate(layout,parent,false);
            holder= new ViewHolder();
            holder.txtName=convertView.findViewById(R.id.setItemName);
            holder.txtId=convertView.findViewById(R.id.setItemId);
            holder.txtDate=convertView.findViewById(R.id.setDateSold);
            holder.txtPrice=convertView.findViewById(R.id.setItemPrice);
            holder.txtQrt=convertView.findViewById(R.id.setItemQrt);
            holder.img = convertView.findViewById(R.id.ImageIcon);

            result= convertView;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        Model mymodel= RecordArrayList.get(position);
        holder.txtName.setText(mymodel.getItemName());
        holder.txtId.setText(mymodel.getItemId());
        holder.txtDate.setText(mymodel.getDate());
        holder.txtPrice.setText(mymodel.getItemPrice());
        holder.txtQrt.setText(mymodel.getItemQrt());
        Bitmap map =BitmapFactory.decodeByteArray(mymodel.getImage(),0,mymodel.getImage().length);
        holder.img.setImageBitmap(map);

        return convertView;
    }
  /**  public void showDiagDelete(final String itmName){
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Warning");
        alert.setMessage("Sure You Want To Remove?");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler = new SqliteHelper(context,null,null,1);
                handler.cursorData(itmName);
                Toast.makeText(context,"Data Removed",Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
alert.show();

    }**/
}