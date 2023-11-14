package com.example.shoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class ShoeDetails extends AppCompatActivity {

    protected EditText editName, editImageSrc, editPrice, editQuantity;
    protected Button saveButton;
    protected DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_details);


        editName = findViewById(R.id.shoe_name_input);
        editImageSrc = findViewById(R.id.shoe_image_src);
        editPrice = findViewById(R.id.shoe_price_input);
        editQuantity = findViewById(R.id.shoe_quantity_input);

        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            dbHelper = new DatabaseHelper(getApplicationContext());
                            String dateNow = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                            dbHelper.insert(
                                    editName.getText().toString(),
                                    editImageSrc.getText().toString(),
                                    Float.parseFloat(editPrice.getText().toString()),
                                    Integer.parseInt(editQuantity.getText().toString()),
                                    dateNow
                            );
                            Toast.makeText(getApplicationContext(), "Shoe created successfully", Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e){
                            if(e.getLocalizedMessage() != null){
                                Log.e(null, e.getLocalizedMessage());
                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                        finally {
                            if(dbHelper != null){
                                dbHelper = null;
                            }
                        }
                    }
                }
        );
    }
}