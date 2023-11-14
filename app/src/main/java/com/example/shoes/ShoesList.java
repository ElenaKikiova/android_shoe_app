package com.example.shoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ShoesList extends AppCompatActivity {

    protected FloatingActionButton addShoeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_list);
        addShoeButton = findViewById(R.id.add_shoe_button);
        
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

        ArrayList<Shoe> shoeList = dbHelper.readAll();

        ArrayAdapter adapter = new ArrayAdapter(ShoesList.this, R.layout.activity_shoes_list, shoeList);

        ListView listView = (ListView) findViewById(R.id.shoes_list);
        listView.setAdapter(adapter);


        addShoeButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ShoesList.this, ShoeDetails.class);
                        startActivity(i);
                    }
                }
        );
    }
}