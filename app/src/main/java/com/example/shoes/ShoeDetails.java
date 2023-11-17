package com.example.shoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class ShoeDetails extends AppCompatActivity {

    protected EditText editName, editImageSrc, editPrice, editQuantity;
    protected Button editButton;
    protected DatabaseHelper dbHelper;
    protected Integer shoeId;

    public void setView(Integer shoeId){

        Log.i(null, "shoeid" + shoeId);

        Shoe shoe = dbHelper.getById(shoeId);

        TextView shoeName = findViewById(R.id.shoe_name);
        shoeName.setText(shoe.getName());

        ImageView shoeImage = findViewById(R.id.shoe_image);
        shoeImage.setImageURI(Uri.parse(shoe.getImageSrc()));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_details);

        dbHelper = new DatabaseHelper(getApplicationContext());

        Intent i = getIntent();
        shoeId = i.getIntExtra("shoe_id", 0);

        setView(shoeId);

        editButton = findViewById(R.id.edit_button);

        editButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent i = new Intent(ShoeDetails.this, EditDeleteShoe.class);
                            startActivity(i);
                        }
                        catch (Exception e){
                            Log.e(null, e.getLocalizedMessage());
                            if(e.getLocalizedMessage() != null){
                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                }
        );
    }
}