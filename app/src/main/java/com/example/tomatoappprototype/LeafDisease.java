package com.example.tomatoappprototype;


import android.Manifest;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.example.tomatoappprototype.ml.ModelUnquant;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class LeafDisease extends AppCompatActivity {

    Button Predictbttn;
    CardView openCam, openGallery;
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaf_disease);

        //back button
        getSupportActionBar().setTitle("Diseases");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //permission of camera
        getPermission();

        //Change Data set Label
        String[] labels = new String[1001];
        int cnt = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("labels_leaf.txt")));
            String line = bufferedReader.readLine();
            while (line != null) {
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

        Predictbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //trained model
                try {
                    ModelUnquant model = ModelUnquant.newInstance(LeafDisease.this);

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);

                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3);
                    byteBuffer.order(ByteOrder.nativeOrder());

                    bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true);
                    // get 1D array of 224 * 224 pixels in image
                    int[] intValues = new int[224 * 224];
                    bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

                    int pixel = 0;
                    for (int i = 0; i < 224; i++) {
                        for (int j = 0; j < 224; j++) {
                            int val = intValues[pixel++]; // RGB
                            byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                            byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                            byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
                        }
                    }

                    inputFeature0.loadBuffer(byteBuffer);
                    ModelUnquant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
//                    bitmap = Bitmap.createScaledBitmap(bitmap, 224,224,true);
//                    TensorImage image = new TensorImage(DataType.FLOAT32);
//                    image.load(inputFeature0);
//                    ByteBuffer byteBuffer = image.getBuffer();
//                    inputFeature0.loadBuffer(byteBuffer);
//                   // inputFeature0.loadBuffer(TensorImage.fromBitmap(bitmap).getBuffer());
//                    // Runs model inference and gets result.
//
//                    ModelUnquant.Outputs outputs = model.process(inputFeature0);
//
//                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    String predClass = labels[getMax(outputFeature0.getFloatArray())] + " ";

                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("predClass", predClass);
                    startActivity(intent);

                    // TODO go to result activity

                    // Releases model resources if no longer used.
                    model.close();
                } catch (IOException e) {
                    // TODO Handle the exception

                }
            }
        });

    }

    int getMax(float[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[max]) max = i;
        }
        return max;
    }

    //permissions to access the camera
    void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(LeafDisease.this, new String[]{Manifest.permission.CAMERA}, 11);
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