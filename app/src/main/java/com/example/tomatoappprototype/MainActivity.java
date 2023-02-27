package com.example.tomatoappprototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView Gallery = findViewById(R.id.Gallery);
        CardView Leaf = findViewById(R.id.Leaf);
        CardView Fruit = findViewById(R.id.Fruit);
        CardView AboutUs = findViewById(R.id.AboutUs);


        //gallery para sa pictures and descriptions
        Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        Leaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeaf();
            }
        });

        Fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFruit();
            }
        });

        AboutUs.setOnClickListener(v->{
            startActivity(new Intent(this, AboutUs.class));
        });
    }

    //pang open sa gallery tab
    private void openGallery() {
        Intent intent = new Intent(this, Gallery.class);
        startActivity(intent);
    }

    private void openLeaf() {
        Intent intent = new Intent(this, LeafDisease.class);
        startActivity(intent);
    }

    private void openFruit() {
        Intent intent = new Intent(this, FruitQuality.class);
        startActivity(intent);
    }
}

