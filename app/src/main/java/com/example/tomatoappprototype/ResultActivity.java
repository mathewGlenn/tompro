package com.example.tomatoappprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String predClass = intent.getStringExtra("predClass");

        ImageView imageview = findViewById(R.id.imageView);
        TextView diseaseName = findViewById(R.id.dName);
        TextView diseaseInfo = findViewById(R.id.dInfoResult);
        LinearLayout noTomato = findViewById(R.id.layoutNoTomato);
        LinearLayoutCompat layoutInfo = findViewById(R.id.layoutInfo);

        Button btnEmail = findViewById(R.id.btnEmail);

        btnEmail.setOnClickListener(v->{
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"apollogalasinaovino@gmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, "Queries about the Tomato fruit and leaf analyzer app");
            email.putExtra(Intent.EXTRA_TEXT, "Enter your message here...");

            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "Choose an email client: "));
        });

        Button btnPickAnotherPhoto = findViewById(R.id.btnPickAnotherPhoto);

        btnPickAnotherPhoto.setOnClickListener(v -> finish());

        diseaseName.setText(predClass);

        switch (predClass){
            case "Image is not Tomato ":
                imageview.setVisibility(View.GONE);
                layoutInfo.setVisibility(View.GONE);
                noTomato.setVisibility(View.VISIBLE);
                break;

            case "Anthracnose ":
                imageview.setImageResource(R.drawable.anthrac);
                diseaseInfo.setText(R.string.ant_info);
                break;

            case "Blossom End Rot ":
                imageview.setImageResource(R.drawable.blossom_end_rot);
                diseaseInfo.setText(R.string.ber_info);
                break;

            case "Fruit Bacterial Spot ":
                imageview.setImageResource(R.drawable.bactetrial_spot_fruit);
                diseaseInfo.setText(R.string.bs_fruit_info);
                break;

            case "Healty_Tomato ":
                imageview.setImageResource(R.drawable.healthy_tomato);
                diseaseInfo.setText(R.string.ht_info);
                break;

            case "Over Ripe Tomato ":
                imageview.setImageResource(R.drawable.overriped);
                diseaseInfo.setText(R.string.overripe_info);
                break;

            case "Bacterial Spot ":
                imageview.setImageResource(R.drawable.bacterial_spot);
                diseaseInfo.setText(R.string.bs_info);
                break;

            case "Early Blight ":
                imageview.setImageResource(R.drawable.early_blight);
                diseaseInfo.setText(R.string.eb_info);
                break;

            case "Healthy Leaf ":
                imageview.setImageResource(R.drawable.healthy_tomato_leaf);
                diseaseInfo.setText(R.string.ht_info);
                break;

            case "Late Blight ":
                imageview.setImageResource(R.drawable.late_blight);
                diseaseInfo.setText(R.string.lb_info);
                break;

            case "Leaf Curl Virus ":
                imageview.setImageResource(R.drawable.leaf_curl);
                diseaseInfo.setText(R.string.lcv_info);
                break;

            case "Leaf Mold ":
                imageview.setImageResource(R.drawable.leaf_mold);
                diseaseInfo.setText(R.string.lm_info);
                break;

            case "Mosaic Virus ":
                imageview.setImageResource(R.drawable.mosaic);
                diseaseInfo.setText(R.string.mv_info);
                break;


        }

    }
}