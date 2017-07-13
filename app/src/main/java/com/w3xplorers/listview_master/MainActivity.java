package com.w3xplorers.listview_master;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.w3xplorers.listview_master.DB.Config;
import com.w3xplorers.listview_master.DB.DBHelper;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit,btnList;
    EditText C_Name,C_No;
    String Contact,Number;

    public void initialMethod(){
        C_Name = (EditText) findViewById(R.id.editeContact);
        C_No = (EditText) findViewById(R.id.editeNo);
        btnSubmit = (Button) findViewById(R.id.idSubmit);
        btnList = (Button) findViewById(R.id.idList);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                Contact = C_Name.getText().toString();
                Number = C_No.getText().toString();
                dbHelper.saveToDatabase(MainActivity.this,Contact,Number,database);

                dbHelper.close();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MainActivity.this,ContactList.class);
                startActivity(mainIntent);
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialMethod();
    }
}
