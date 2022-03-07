package com.example.barodegeneratorscanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;

import github.nisrulz.qreader.QRDataListener;
import github.nisrulz.qreader.QREader;

public class ScannerScreen extends AppCompatActivity {

    private static final String cameraPerm = Manifest.permission.CAMERA;
    SurfaceView camera_view;
    QREader qrEader;
    boolean hasCameraPermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_screen);

        camera_view=findViewById(R.id.camera_view);

        hasCameraPermission = RuntimePermissionUtil.checkPermissonGranted(this, cameraPerm);

        if (hasCameraPermission) {
            // Setup QREader
            setupQREader();
        } else {
            RuntimePermissionUtil.requestPermission(ScannerScreen.this, cameraPerm, 100);
        }



    }

    private void setupQREader() {

        qrEader = new QREader.Builder(this, camera_view, new QRDataListener() {
            @Override
            public void onDetected(final String data) {
                // Log.d("QREader", "Value : " + data);
                camera_view.post(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                        intent.putExtra("result",data);
                        startActivity(intent);
                    }
                });
            }
        }).facing(QREader.BACK_CAM)
                .enableAutofocus(true)
                .height(camera_view.getHeight())
                .width(camera_view.getWidth())
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Init and Start with SurfaceView
        // -------------------------------
        if (hasCameraPermission) {

            // Init and Start with SurfaceView
            // -------------------------------
            qrEader.initAndStart(camera_view);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            RuntimePermissionUtil.onRequestPermissionsResult(grantResults, new RPResultListener() {
                @Override
                public void onPermissionGranted() {
                    if (RuntimePermissionUtil.checkPermissonGranted(ScannerScreen.this, cameraPerm)) {
                        recreate();
                    }
                }

                @Override
                public void onPermissionDenied() {
                    // do nothing
                }
            });
        }
    }
    @Override
    protected void onPause() {
        super.onPause();

        // Cleanup in onPause()
        // --------------------
        if (hasCameraPermission) {

            // Cleanup in onPause()
            // --------------------
            qrEader.releaseAndCleanup();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

}