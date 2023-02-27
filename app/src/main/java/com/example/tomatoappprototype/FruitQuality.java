package com.example.tomatoappprototype;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tomatoappprototype.ml.Fruitmodel;
import com.example.tomatoappprototype.ml.FruitmodelV2;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

public class FruitQuality extends AppCompatActivity {

    Button Predictbttn;
    CardView openCam, openGallery;
    TextView result, resultView, description;
    ImageView imageView;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaf_disease);

        //back button
        getSupportActionBar().setTitle("Tomato Quality");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //permission of camera
        getPermission();


        //Change Data set Label
        String[] labels = new String[1001];
        int cnt = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("labels_fruits.txt")));
            String line = bufferedReader.readLine();
            while (line != null){
                labels[cnt] = line;
                cnt++;
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Predictbttn = findViewById(R.id.Predictbttn);
        openCam = findViewById(R.id.cardCamera);
        openGallery = findViewById(R.id.cardGallery);
        imageView = findViewById(R.id.imageView);


        //para ma click take photo button
        openCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 12);
            }
        });

        //para ma click upload button
        openGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 10);
            }
        });
        //para ma click upload button
        Predictbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //train model data set
                try {
                    FruitmodelV2 model = FruitmodelV2.newInstance(FruitQuality.this);

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);
                    bitmap = Bitmap.createScaledBitmap(bitmap, 224,224,true);
                    inputFeature0.loadBuffer(TensorImage.fromBitmap(bitmap).getBuffer());

                    // Runs model inference and gets result.
                    FruitmodelV2.Outputs outputs = model.process(inputFeature0);

                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    String predClass = labels[getMax(outputFeature0.getFloatArray())]+" ";

                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("predClass", predClass);
                    startActivity(intent);

                    // Releases model resources if no longer used.
                    model.close();
                } catch (IOException e) {
                    // TODO Handle the exception
                }



            }
        });


    }

    int getMax(float[] arr){
        int max = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] > arr[max]) max = i;
        }
        return max;
    }


    //permissions to access the camera
    void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(FruitQuality.this, new String[]{Manifest.permission.CAMERA}, 11);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 11) {
            if (grantResults.length > 0) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    this.getPermission();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 10) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else if (requestCode == 12) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);


        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}