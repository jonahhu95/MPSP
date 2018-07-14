package monster.artificial.bannerlah;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;


public class QRScannerActivity extends AppCompatActivity {

    private CodeScanner mCodeScanner;
    private CodeScannerView scannerView;
    private final int CAMERA_REQUEST = 3456;
    private int mode = 0;
    private Intent statusActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        mode = getIntent().getIntExtra("mode", 0);
        checkPermission();
        getSupportActionBar().hide();
        scannerView = findViewById(R.id.scanner_view);
        statusActivity = new Intent(getApplicationContext() , StatusActivity.class);
        mode = 1;
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!(checkSelfPermission(Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(QRScannerActivity.this,
                        new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
                return;
            }
        }else{
            startQRScanner();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23) {
            if ((checkSelfPermission(Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_GRANTED)) {
                try{
                    mCodeScanner.startPreview();
                }catch (Exception e){
                    startQRScanner();
                    mCodeScanner.startPreview();
                }
                return;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try{
            mCodeScanner.releaseResources();
        }catch (Exception e){

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try{
            mCodeScanner.releaseResources();
        }catch (Exception e){

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Please grant camera permission to use app", Toast.LENGTH_LONG).show();
                    checkPermission();
                }else{
                    startQRScanner();
                }
                break;
        }
    }

    private void startQRScanner(){

        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                QRScannerActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mode == 0){
                            if (result.getText().equals("https:\\mpsp.gov.my\\")){
                                showSuccess();
                            }else{

                            }
                        }else{
                            startValidateActivity();
                        }
                        // Pass result to next avtivity here
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
                if (mode == 0){
                    showSuccess();
                }else {
                    startValidateActivity();
                }
            }
        });
    }

    private void showSuccess(){
        new MaterialDialog.Builder(this)
                .title("License activation")
                .content("Confirm activate license?")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(getApplicationContext(), "License Activated!", Toast.LENGTH_LONG).show();
                        mCodeScanner.startPreview();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mCodeScanner.startPreview();
                    }
                })
                .show();
    }

    private void startValidateActivity(){
        startActivity(statusActivity);
    }
}
