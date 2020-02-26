package fr.epsi.gostyle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.epsi.gostyle.modele.Coupon;
import fr.epsi.gostyle.modele.Utilisateur;

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

        try {
            initData(fileReaderUserData());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void initData(String data) throws IOException, ParseException {
        try {
            JSONObject jsonElement=new JSONObject(data);
            Utilisateur utilisateur = new Utilisateur(jsonElement);
            coupons.addAll(utilisateur.getCoupons());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }

}
