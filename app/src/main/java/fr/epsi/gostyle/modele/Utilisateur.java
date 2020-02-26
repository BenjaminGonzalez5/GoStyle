package fr.epsi.gostyle.modele;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur {

    private Long id;
    private String email;
    private String password;
    private List<Coupon> coupons = new ArrayList<>();

    public Utilisateur(JSONObject jsonObject) throws IOException, JSONException, ParseException {
        id = jsonObject.optLong("id");
        email = jsonObject.optString("email", "");
        password = jsonObject.optString("password", "");
        String json = jsonObject.optString("coupons");
        JSONArray jsonArray=new JSONArray(json);
        for(int i=0;i<jsonArray.length();i++){
            Coupon coupon=new Coupon(jsonArray.getJSONObject(i));
            coupons.add(coupon);
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
