package com.example.tomatoappprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Button btnEmail = findViewById(R.id.btnEmail);

        btnEmail.setOnClickListener(v->{
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"apollogalasinaovino@gmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, "Queries about the Tomato fruit and leaf analyzer app");
            email.putExtra(Intent.EXTRA_TEXT, "Enter your message here...");

            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "Choose an email client: "));
        });
    }
}