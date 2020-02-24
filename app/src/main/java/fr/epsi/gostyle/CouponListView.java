package fr.epsi.gostyle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import fr.epsi.gostyle.modele.Coupon;

public class CouponListView extends GoStyleActivity {

    private CouponAdapter adapter;
    private ArrayList<Coupon> coupons = new ArrayList<>();

    public static void display(GoStyleActivity activity){
        Intent intent=new Intent(activity,CouponListView.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.couponlistview);
        showCameraBtn();
        final ListView listView = findViewById(R.id.listViewCoupons);
        adapter = new CouponAdapter(this, R.layout.c_coupon, coupons);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailsCoupon.display(CouponListView.this,coupons.get(position));
            }
        });
        String url = "http://10.0.2.2:8080/API_GoStyle_war/coupons";

        new HttpAsyTask(url, new HttpAsyTask.HttpAsyTaskListener() {
            @Override
            public void webServiceDone(String result) throws ParseException {
                initData(result);
            }

            @Override
            public void webServiceError(Exception e) {
                displayToast(e.getMessage());
            }
        }).execute();
    }

    private void initData(String data) throws ParseException {
        try {
            JSONArray jsonArray=new JSONArray(data);
            for(int i=0;i<jsonArray.length();i++){
                Coupon coupon=new Coupon(jsonArray.getJSONObject(i));
                coupons.add(coupon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        displayToast(String.valueOf(coupons.size()));
        adapter.notifyDataSetChanged();
    }

}
