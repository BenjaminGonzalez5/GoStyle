package fr.epsi.b3.api.controleur;

import fr.epsi.b3.api.modele.Utilisateur;
import fr.epsi.b3.api.service.UtilisateurDto;
import fr.epsi.b3.api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;

@Controller
public class AccueilController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/")
    public String accueil() {
        return "accueil";
    }

    @GetMapping(path = "/user/{id}", produces = {"application/json"})
    @ResponseBody
    public Utilisateur getById(@PathVariable String id) throws Exception {
        return utilisateurService.getUtilisateurById(Long.valueOf(id));
    }

    @GetMapping(path = "/user/{email}", produces = {"application/json"})
    @ResponseBody
    public Utilisateur getByEmail(@PathVariable String email) throws Exception {
        return utilisateurService.getUtilisateurByEmail(email);
    }

    @PostMapping(path="/utilisateur", produces = "application/json", consumes="application/json")
    @ResponseBody
    public ResponseEntity<Utilisateur> getUtilisateurFromJsonForm(@RequestBody UtilisateurDto utilisateurDto) {
        System.out.println(utilisateurDto.getPassword());
        System.out.println(utilisateurDto.getEmail());
        Utilisateur utilisateur = utilisateurService.getUtilisateurByEmailAndPassword(utilisateurDto.getEmail(), utilisateurDto.getPassword());
        return ResponseEntity.ok().body(utilisateur);
    }
}
