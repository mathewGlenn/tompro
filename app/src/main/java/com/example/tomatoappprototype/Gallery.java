package com.example.tomatoappprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class Gallery extends AppCompatActivity {
    CardView bsLeaf, ebLeaf, lbLeaf, lcvLeaf, lmLeaf, mvLeaf, healthyFruit, bsFruit, antFruit, berFruit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //back button
        getSupportActionBar().setTitle("Gallery");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bsLeaf = findViewById(R.id.cardBsLeaf);
        ebLeaf = findViewById(R.id.cardEbLeaf);
        lbLeaf = findViewById(R.id.cardLbLeaf);
        lcvLeaf = findViewById(R.id.cardLCVLeaf);
        lmLeaf = findViewById(R.id.cardLmLeaf);
        mvLeaf = findViewById(R.id.cardMvLeaf);
        healthyFruit = findViewById(R.id.cardHealthyFruit);
        bsFruit = findViewById(R.id.cardBsFruit);
        antFruit = findViewById(R.id.cardAntFruit);
        berFruit = findViewById(R.id.cardBerFruit);

        bsLeaf.setOnClickListener(v->{
            Intent intent = new Intent(this, LibraryInfo.class);
            intent.putExtra("tomClass", "bsLeaf");
            startActivity(intent);
        });

        ebLeaf.setOnClickListener(v->{
            Intent intent = new Intent(this, LibraryInfo.class);
            intent.putExtra("tomClass", "ebLeaf");
            startActivity(intent);
        });

        lbLeaf.setOnClickListener(v->{
            Intent intent = new Intent(this, LibraryInfo.class);
            intent.putExtra("tomClass", "lbLeaf");
            startActivity(intent);
        });

        lcvLeaf.setOnClickListener(v->{
            Intent intent = new Intent(this, LibraryInfo.class);
            intent.putExtra("tomClass", "lcvLeaf");
            startActivity(intent);
        });

        lmLeaf.setOnClickListener(v->{
            Intent intent = new Intent(this, LibraryInfo.class);
            intent.putExtra("tomClass", "lmLeaf");
            startActivity(intent);
        });

        mvLeaf.setOnClickListener(v->{
            Intent intent = new Intent(this, LibraryInfo.class);
            intent.putExtra("tomClass", "mvLeaf");
            startActivity(intent);
        });

        healthyFruit.setOnClickListener(v->{
            Intent intent = new Intent(this, LibraryInfo.class);
            intent.putExtra("tomClass", "healthyFruit");
            startActivity(intent);
        });

        bsFruit.setOnClickListener(v->{
            Intent intent = new Intent(this, LibraryInfo.class);
            intent.putExtra("tomClass", "bsFruit");
            startActivity(intent);
        });

        antFruit.setOnClickListener(v->{
            Intent intent = new Intent(this, LibraryInfo.class);
            intent.putExtra("tomClass", "antFruit");
            startActivity(intent);

        });

        berFruit.setOnClickListener(v->{
            Intent intent = new Intent(this, LibraryInfo.class);
            intent.putExtra("tomClass", "berFruit");
            startActivity(intent);
        });
    }
}