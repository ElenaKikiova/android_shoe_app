package com.example.shoes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShoesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_list);
        
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

        ArrayList<Shoe> shoeList = dbHelper.readAll();

        ArrayAdapter adapter = new ArrayAdapter(ShoesList.this, R.layout.activity_shoes_list, shoeList);

        ListView listView = (ListView) findViewById(R.id.shoes_list);
        listView.setAdapter(adapter);
    }
}