package fr.epsi.gostyle;

import org.json.JSONArray;
import org.json.JSONException;

public class Utilisateur {

    private String email;
    private String password;

    public JSONArray connexion() throws JSONException {
        //retourne un code 200 + le json contenant les infos de l'utilisateur
        return new JSONArray("{\n" +
                "    \"utilisateur\":[\n" +
                "    {\"email\":\"email@email.fr\", \"password\" : \"unpassword\", \"codesPromos\" : [\n" +
                "            {\"code\" : \"AF123\", \"description\":\"20% T-Shirts\"},\n" +
                "            {\"code\" : \"AF123\", \"description\":\"20% T-Shirts\"}\n" +
                "        ]},\n" +
                "    {\"email\":\"email@email.fr\", \"password\" : \"unpassword\", \"codesPromos\" : [\n" +
                "            {\"code\" : \"AF123\", \"description\":\"20% T-Shirts\"},\n" +
                "            {\"code\" : \"AF123\", \"description\":\"20% T-Shirts\"}\n" +
                "        ]}\n" +
                "    ]\n" +
                "}");
        //if fichier vide, créer fichier avec texte dedans

    }

    public void deconnexion() {
        //détruit la varible de session --> créer une classe "session" // créer fichier dans le dossier de l'appli sur le portable --> supprimer le fichier ?
    }


}
