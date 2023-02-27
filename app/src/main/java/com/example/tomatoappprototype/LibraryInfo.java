package com.example.tomatoappprototype;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class LibraryInfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_info);

        Intent intent = getIntent();
        String tomClass = intent.getStringExtra("tomClass");

        ImageView imageView = findViewById(R.id.libImage);
        TextView tomName = findViewById(R.id.tomName);
        TextView tomInfo = findViewById(R.id.tomInfo);

        imageView.setImageResource(R.drawable.bactetrial_spot_fruit);
        tomName.setText(tomClass);
        tomInfo.setText(R.string.bs_info);
        switch (tomClass) {
            case "bsLeaf":
                imageView.setImageResource(R.drawable.bacterial_spot);
                tomName.setText("Bacterial Spot");
                tomInfo.setText(R.string.bs_info);
                break;

            case "ebLeaf":
                imageView.setImageResource(R.drawable.early_blight);
                tomName.setText("Early Blight");
                tomInfo.setText(R.string.eb_info);
                break;

            case "lbLeaf":
                imageView.setImageResource(R.drawable.late_blight);
                tomName.setText("Late Blight");
                tomInfo.setText(R.string.lb_info);
                break;


            case "lcvLeaf":
                imageView.setImageResource(R.drawable.leaf_curl);
                tomName.setText("Leaf Curl Virus");
                tomInfo.setText(R.string.lcv_info);
                break;

            case "lmLeaf":
                imageView.setImageResource(R.drawable.leaf_mold);
                tomName.setText("Leaf Mold");
                tomInfo.setText(R.string.lm_info);
                break;

            case "mvLeaf":
                imageView.setImageResource(R.drawable.mosaic);
                tomName.setText("Mosaic Virus");
                tomInfo.setText(R.string.mv_info);
                break;

            case "healthyFruit":
                imageView.setImageResource(R.drawable.healthy_tomato);
                tomName.setText("Healthy Tomato");
                tomInfo.setText(R.string.ht_info);
                break;

            case "bsFruit":
                imageView.setImageResource(R.drawable.bactetrial_spot_fruit);
                tomName.setText("Bacterial Spot");
                tomInfo.setText(R.string.bs_fruit_info);
                break;

            case "antFruit":
                imageView.setImageResource(R.drawable.anthrac);
                tomName.setText("Anthracnose");
                tomInfo.setText(R.string.ant_info);
                break;

            case "berFruit":
                imageView.setImageResource(R.drawable.anthrac);
                tomName.setText("Blossom End Rot");
                tomInfo.setText(R.string.ber_info);
                break;
        }
    }
}