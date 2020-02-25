package fr.epsi.gostyle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.epsi.gostyle.modele.Coupon;

public class DetailsCoupon extends GoStyleActivity {

    private Coupon coupon;

    public static void display(GoStyleActivity goStyleActivity, Coupon coupon) {
        Intent intent = new Intent(goStyleActivity, DetailsCoupon.class);
        intent.putExtra("coupon", coupon);
        goStyleActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailscoupon);
        showCameraBtn();
        coupon = (Coupon) getIntent().getExtras().get("coupon");
        TextView textCode = findViewById(R.id.detailsCouponCode);
        TextView textDescription = findViewById(R.id.detailsCouponDescription);
        TextView textDateDebut = findViewById(R.id.dateExpirationDebut);
        TextView textDateFin = findViewById(R.id.dateExpirationFin);
        TextView textErrorMessage = findViewById(R.id.expiredDate);

        textCode.setText(coupon.getCode());
        textDescription.setText(coupon.getDescription());
        textDateDebut.setText(coupon.getDateDebut());
        textDateFin.setText(coupon.getDateFin());
        if (!coupon.isValid()){
            textErrorMessage.setVisibility(View.VISIBLE);
        }
        else {
            textErrorMessage.setVisibility(View.INVISIBLE);
        }

        setTitle(coupon.getCode());
    }


}
