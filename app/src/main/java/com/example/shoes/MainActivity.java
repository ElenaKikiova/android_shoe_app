package com.example.shoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected EditText adminPassword;
    protected Button logInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminPassword = findViewById(R.id.password);
        logInButton = findViewById(R.id.login_button);

        logInButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {

                        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

                        ArrayList<Shoe> shoeList = dbHelper.readAll();

                        System.out.println("send");
                        Intent i = new Intent(MainActivity.this, ShoesList.class);
                        startActivity(i);
                    }
                    catch (Exception e){
                        System.out.println(e);
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }
        );
    }
}