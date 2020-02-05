package fr.epsi.gostyle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;

public class Connexion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
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

//scan QR CODE
//apell web service
//récup coupon
//affichage coupon
//connexion avec compte test