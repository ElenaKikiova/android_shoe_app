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
import java.util.Date;
import java.util.Locale;

public class EditDeleteShoe extends AppCompatActivity {

    protected EditText editName, editImageSrc, editPrice, editQuantity;
    protected Button saveButton;
    protected DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_shoe);

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
                            String name = editName.getText().toString();
                            String imageSrc = editImageSrc.getText().toString();
                            Float price = Float.parseFloat(editPrice.getText().toString());
                            Integer quantity = Integer.parseInt(editQuantity.getText().toString());
                            String dateNow = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                            // Validation
                            boolean valid = true;
                            String error = "";

                            if (name == "" || !Validation.Validate(name, Validation.nameValidator)) {
                                valid = false;
                                error = "Name should be 2-20 symbols, letters and numbers";
                            }
                            if (!Validation.Validate(imageSrc, Validation.urlValidator)) {
                                valid = false;
                                error = "Image source should be a valid URL";
                            }
                            if (!Validation.Validate(price.toString(), Validation.priceValidator)) {
                                valid = false;
                                error = "Price should be a valid number, with floating point or decimal point";
                            }
                            if (!Validation.Validate(quantity.toString(), Validation.numberValidator)) {
                                valid = false;
                                error = "Price should be a valid number, with floating point or decimal point";
                            }

                            if (!valid) {
                                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                            } else {

                                dbHelper = new DatabaseHelper(getApplicationContext());

                                dbHelper.insert(
                                        editName.getText().toString(),
                                        editImageSrc.getText().toString(),
                                        Float.parseFloat(editPrice.getText().toString()),
                                        Integer.parseInt(editQuantity.getText().toString()),
                                        dateNow
                                );
                                Toast.makeText(getApplicationContext(), "Shoe created successfully", Toast.LENGTH_LONG).show();

                                Intent i = new Intent(EditDeleteShoe.this, ShoesList.class);
                                startActivity(i);
                            }
                        } catch (Exception e) {
                            if (e.getLocalizedMessage() != null) {
                                Log.e(null, e.getLocalizedMessage());
                                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        } finally {
                            if (dbHelper != null) {
                                dbHelper.close();
                                dbHelper = null;
                            }
                        }
                    }
                }
        );
    }
}