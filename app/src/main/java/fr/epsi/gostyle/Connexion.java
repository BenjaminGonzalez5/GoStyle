package fr.epsi.gostyle;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

public class Connexion extends GoStyleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        findViewById(R.id.buttonConnexion).setOnClickListener(this);

        File file = new File(this.getFilesDir(), FILE_NAME);
        if (file.exists()){
            CouponListView.display(Connexion.this);
        }
    }

    @Override
    public void onClick(View v) {
        try {
            connexion();
        } catch (JSONException | MalformedURLException e) {
            e.printStackTrace();
        }
    }



    public boolean isFileExist(String filename) {
        File f = new File(getFilesDir().getAbsolutePath() + filename);
        return f.exists();
    }

    public void connexion() throws JSONException, MalformedURLException {
        //TODO : retourne un code 200 + le json contenant les infos de l'utilisateur et les coupons de l'utilsateur + fichier texte pour session ?

        EditText textEmail = (EditText) findViewById(R.id.editTextUsername);
        EditText textPassword = (EditText) findViewById(R.id.editTextPassword);
        String url = "http://ec2-18-223-29-120.us-east-2.compute.amazonaws.com:8080/API_GoStyle-0.0.1-SNAPSHOT/user";

        if (!textEmail.getText().toString().equals("") && !textPassword.getText().toString().equals("")) {
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("email", textEmail.getText().toString());
            jsonParam.put("password", textPassword.getText().toString());

            new HttpAsyTask(url, new HttpAsyTask.HttpAsyTaskListener() {
                @Override
                public void webServiceDone(String result) {
                    try {
                        createFile(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void webServiceError(Exception e) {
                    displayToast(e.getMessage());
                }
                }).execute(jsonParam.toString());


        } else {
            displayToast("Veuillez remplir les deux champs pour vous connecter");
        }
    }
    /*
    private void initData(String data) throws ParseException {
        try {
            JSONArray jsonArray=new JSONArray(data);
            for(int i=0;i<jsonArray.length();i++){
                //prend le json, et écrit dans le fichier
                Coupon coupon=new Coupon(jsonArray.getJSONObject(i));
                coupons.add(coupon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        displayToast(String.valueOf(coupons.size()));
        adapter.notifyDataSetChanged();
    }*/
}


//C:\Users\matthieu.rodrigues\Documents\AndroidStudio\DeviceExplorer\Pixel_2_API_29 [emulator-5554]\data\data\fr.epsi.gostyle\files --> lien du fichier txt

//test à faire :

//scan QR CODE
//apell web service
//récup coupon
//affichage coupon
//connexion avec compte test