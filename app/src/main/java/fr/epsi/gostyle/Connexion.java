package fr.epsi.gostyle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import javax.net.ssl.HttpsURLConnection;

public class Connexion extends GoStyleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        findViewById(R.id.buttonConnexion).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            connexion();
        } catch (JSONException | MalformedURLException e) {
            e.printStackTrace();
        }
        //createFile();
        //CouponListView.display(Connexion.this);
    }

    public void createFile(String result) {
        File accountFile = new File(getFilesDir().getAbsolutePath() + "/test.txt");
        if (!accountFile.exists()) {
            try {
                accountFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        displayToast("enter in createFile");
        //Device File Explorer
        try {
            //EditText textUsername = (EditText) findViewById(R.id.editTextUsername);
            //EditText textPassword = (EditText) findViewById(R.id.editTextPassword);

            //String content = textUsername.getText().toString() + ";" + textPassword.getText().toString();
            BufferedWriter buf = new BufferedWriter(new FileWriter(accountFile, true));

            try {
                JSONArray jsonArray=new JSONArray(result);
                for(int i=0;i<jsonArray.length();i++){
                    buf.newLine();
                    displayToast("json : " + jsonArray.getJSONObject(i).toString());
                    buf.write(jsonArray.getJSONObject(i).toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
                buf.close();
            }
            buf.close();
            //CouponListView.display(Connexion.this);
        } catch (IOException e) {
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
        String url = "http://10.0.2.2:8081/API_GoStyle/user";

        if (!textEmail.getText().toString().equals("") && !textPassword.getText().toString().equals("")) {
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("email", textEmail.getText().toString());
            jsonParam.put("password", textPassword.getText().toString());

            new HttpAsyTask(url, new HttpAsyTask.HttpAsyTaskListener() {
                @Override
                public void webServiceDone(String result) throws ParseException {
                    //Ici : envoyer à l'API
                    displayToast("result : " + result);
                    createFile(result);
                }

                @Override
                public void webServiceError(Exception e) {
                    displayToast(e.getMessage());
                }
                }).postExecute(url, jsonParam);


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