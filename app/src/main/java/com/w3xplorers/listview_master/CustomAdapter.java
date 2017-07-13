package com.w3xplorers.listview_master;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.w3xplorers.listview_master.DB.Config;

/**
 * Created by DELL on 7/10/2017.
 */

public class CustomAdapter extends CursorAdapter{

    LayoutInflater layoutInflater;

    public CustomAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //initial the view
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);
        ViewHolder holder = new ViewHolder();
        holder.txtContact = (TextView) view.findViewById(R.id.idContact);
        holder.txtContactNo = (TextView) view.findViewById(R.id.idContactNo);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.txtContact.setText(cursor.getString(cursor.getColumnIndex(Config.KEY_CONTACTNAME)));
        holder.txtContactNo.setText(cursor.getString(cursor.getColumnIndex(Config.KEY_CONTACTNO)));
    }

    //Create ViewHolder Class
    static class ViewHolder{
        TextView txtContact;
        TextView txtContactNo;
    }
}
