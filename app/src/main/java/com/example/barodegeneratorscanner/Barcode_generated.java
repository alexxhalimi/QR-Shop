package com.example.barodegeneratorscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class Barcode_generated extends AppCompatActivity {


    TextView text,generate;
    ImageView barcode_img;
    String input="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_generated);

        text=findViewById(R.id.text);
        generate=findViewById(R.id.generate);
        barcode_img=findViewById(R.id.barcode_img);

        input=getIntent().getStringExtra("name");

        text.setText(input);


        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;


        // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
        QRGEncoder qrgEncoder = new QRGEncoder(input, null, QRGContents.Type.TEXT, smallerDimension);
        qrgEncoder.setColorBlack(Color.BLACK);
        qrgEncoder.setColorWhite(Color.WHITE);
        try {
            // Getting QR-Code as Bitmap
        Bitmap bitmap = qrgEncoder.getBitmap();
            // Setting Bitmap to ImageView
            barcode_img.setImageBitmap(bitmap);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),ItemLiscScreen.class));
    }

}