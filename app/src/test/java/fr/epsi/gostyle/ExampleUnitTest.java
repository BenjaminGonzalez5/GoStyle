package fr.epsi.gostyle;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    private Connexion connexionClass = new Connexion();

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

         //Tester avec les appels API, à tester plus tard

    @Test
    public void ifFileTestReturnTrueTest() {
        boolean expected = true;

        boolean result =  connexionClass.isFileExist("/test.txt");

        assertEquals(expected, result);
    }



    @Test
    public void correctLoginTest() throws JSONException {


        //Reponse : vérifier si le fichier texte existe / a été créer
        //Reponse: code 200 + les infos et les coupons en json api = localhost/8081/gostyle_war/user

/*        new JSONArray("{\n" +
                " \"email\" : matthieu.rodrigues@epsi.fr" +
                " \"password\" : AZERTY" +
                "}");*/

        connexionClass.connexion("matthieu.rodrigues@epsi.fr", "AZERTY"); //Act

        //assertEquals(FILEEXIST,  connexionClass.connexion("matthieu.rodrigues@epsi.fr", "AZERTY")); //Assert
    }
/*
    @Test
    public void incorrectLoginErrorTest() throws JSONException {
        //Envoie du json
        JSONArray json = new JSONArray();

        JSONArray jsonAct = connexionClass.connexion();
        //Reponse : error 400 + affichage du toast "Identifiant incorrect"
    }

    @Test
    public void serverConnectionLostTest() {
        //Envoie du json
        //Reponse : error 500
    }
    /*
    @Test
    public void deconnexionTest() {
        connexionClass  .deconnexion();

        //Tester si le fichier est supprimé ou vide (?)
    }*/


    @Test
    public void clicDetailsListViewTest() {
        //Tester si la page détails d'un élément de la liste view fonctionne
    }

    @Test
    public void correctScanQRCodeTest() {
        //Tester code scanné
    }

    @Test
    public void inccorectScanQRCodeTest() {
        //Tester si le qrcode est mal scanné (?)
    }

    @Test
    public void scanQRCodeDejaScanneTest() {
        //Tester et renvoie d'erreur si le QRcode a déjà été scanné
    }

    @Test
    public void scanQRCodeServerConnectionLostTest() {
        //Tester si le scan de QRCode est bon mais connexion au server ne fonctionne pas (error 500)
    }
}