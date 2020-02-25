package fr.epsi.gostyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;
    private UserDataManager userDataManager = new UserDataManager();

    public static void display(GoStyleActivity activity){
        Intent intent=new Intent(activity,ScanCodeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
    }

    @Override
    public void handleResult(Result result) {
        userDataManager.updateUserData("http://10.0.2.2:8080/API_GoStyle_war/coupons" + result);
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }
}