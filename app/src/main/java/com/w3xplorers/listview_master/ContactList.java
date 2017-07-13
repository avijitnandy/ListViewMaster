package com.w3xplorers.listview_master;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.w3xplorers.listview_master.DB.Config;
import com.w3xplorers.listview_master.DB.DBHelper;

public class ContactList extends AppCompatActivity {

    private CustomAdapter customAdapter;
    ListView listView;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        DBHelper dbHelper = new DBHelper(this);
        cursor = dbHelper.getContactList();
        customAdapter = new CustomAdapter(ContactList.this,  cursor, 0);
        listView = (ListView) findViewById(R.id.lstContact);

        listView.setAdapter(customAdapter);
    }
}
