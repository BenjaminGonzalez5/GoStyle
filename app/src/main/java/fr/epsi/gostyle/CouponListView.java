package fr.epsi.gostyle;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CouponListView extends GoStyleActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.couponlistview);
        showCameraBtn();
    }

    public static void display(GoStyleActivity activity){
        Intent intent=new Intent(activity,CouponListView.class);
        activity.startActivity(intent);
    }


}
