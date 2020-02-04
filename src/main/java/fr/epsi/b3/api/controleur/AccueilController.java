package fr.epsi.b3.api.controleur;

import fr.epsi.b3.api.modele.Utilisateur;
import fr.epsi.b3.api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccueilController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/")
    public String accueil() {
        return "accueil";
    }

    @GetMapping(path = "/user/{email}", produces = {"application/json"})
    @ResponseBody
    public Utilisateur getByEmail(@PathVariable String email) {
        System.out.println(email);
        return utilisateurService.getUtilisateurByEmail(email);
    }
}
