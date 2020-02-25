package fr.epsi.gostyle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import fr.epsi.gostyle.modele.Coupon;

public class QRCode extends GoStyleActivity {

    private Button scanBtn;
    public static Coupon couponScanned;

    public static void display(GoStyleActivity activity){
        Intent intent=new Intent(activity,QRCode.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);

        scanBtn = findViewById(R.id.btnscan);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanCodeActivity.class));
            }
        });

//        try {
//
//            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
//            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes
//
//            startActivityForResult(intent, 0);
//
//        } catch (Exception e) {
//
//            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
//            Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
//            startActivity(marketIntent);
//
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
            }
            if(resultCode == RESULT_CANCELED){
                //handle cancel
            }
        }
    }
}
