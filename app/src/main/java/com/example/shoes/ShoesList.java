package com.example.shoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShoesList extends AppCompatActivity {

    protected DatabaseHelper dbHelper;
    protected FloatingActionButton addShoeButton;

    protected ListView listView;

    public class CustomListAdapter extends ArrayAdapter<Shoe>{
        private Context context;
        private int resource;

        public CustomListAdapter(@NonNull Context context, int resource, @NonNull List objects){
            super(context, resource, objects);
            this.context = context;
            this.resource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            Shoe shoe = getItem(position);
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(resource, parent, false);

                TextView viewId = convertView.findViewById(R.id.list_view_id);
                viewId.setText(shoe.getID().toString());

                ImageView viewImage = convertView.findViewById(R.id.list_view_image);
//                URL url = new URL(shoe.getImageSrc());
//                InputStream content = (InputStream) url.getContent();
//                Drawable d = Drawable.createFromStream(content , "src");
//                viewImage.setImageDrawable(d);

                viewImage.setImageURI(Uri.parse(shoe.getImageSrc()));

                TextView viewName = convertView.findViewById(R.id.list_view_name);
                viewName.setText(shoe.getName());

                TextView viewPrice = convertView.findViewById(R.id.list_view_price);
                viewPrice.setText(shoe.getPrice().toString());
            }

            return convertView;
        }
    }

    protected void FeedList() throws Exception{

        dbHelper = new DatabaseHelper(getApplicationContext());
        List<Shoe> shoeList = dbHelper.readAll();
        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.activity_shoe_list_item, shoeList);

        listView = (ListView) findViewById(R.id.shoes_list);
        listView.setAdapter(adapter);

        dbHelper.close();
        dbHelper = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_list);
        addShoeButton = findViewById(R.id.add_shoe_button);


        try {
            this.FeedList();
        }
        catch (Exception e){
            Log.e(null, e.getLocalizedMessage());
            if(e.getLocalizedMessage() != null){
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        }
        finally {
            if(dbHelper != null){
                dbHelper.close();
                dbHelper = null;
            }
        }


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