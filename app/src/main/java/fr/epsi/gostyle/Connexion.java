package fr.epsi.gostyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

public class Connexion extends GoStyleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        findViewById(R.id.buttonConnexion).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonConnexion:
                CouponListView.display(Connexion.this);
                break;
        }
        // CouponListView.display(Connexion.this);
    }


    public JSONArray connexion() throws JSONException {
        //TODO : retourne un code 200 + le json contenant les infos de l'utilisateur et les coupons de l'utilsateur + fichier texte pour session ?w
        return new JSONArray("{\n" +
                "    \"utilisateur\":[\n" +
                "    {\"email\":\"email@email.fr\", \"password\" : \"unpassword\", \"codesPromos\" : [\n" +
                "            {\"code\" : \"AF123\", \"description\":\"20% T-Shirts\"},\n" +
                "            {\"code\" : \"AF123\", \"description\":\"20% T-Shirts\"}\n" +
                "        ]} ]} ,");

        //if fichier vide, créer fichier avec texte dedans

    }
}

//envoie en json email : input_email
//               password : input_password
//à /user


//scan QR CODE
//apell web service
//récup coupon
//affichage coupon
//connexion avec compte test