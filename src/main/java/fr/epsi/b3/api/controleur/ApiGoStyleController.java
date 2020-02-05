package fr.epsi.b3.api.controleur;

import fr.epsi.b3.api.modele.Utilisateur;
import fr.epsi.b3.api.service.InvalidEmailException;
import fr.epsi.b3.api.service.UtilisateurDto;
import fr.epsi.b3.api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@RestController
public class ApiGoStyleController {

    @Autowired
    private UtilisateurService utilisateurService;

    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErreurDto handleBadIdException(NoResultException e){
        return new ErreurDto(e);
    }

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErreurDto handleInvalidEmailException(InvalidEmailException e){
        return new ErreurDto(e);
    }

    @GetMapping(path = "/user/{id}", produces = {"application/json"})
    @ResponseBody
    public Utilisateur getById(@PathVariable String id) throws Exception {
        return utilisateurService.getUtilisateurById(Long.valueOf(id));
    }

    @PostMapping(path = "/user", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Utilisateur> getUtilisateurFromJsonForm(@RequestBody UtilisateurDto utilisateurDto) throws InvalidEmailException {
        Utilisateur utilisateur = utilisateurService.getUtilisateurByEmailAndPassword(utilisateurDto);
        return ResponseEntity.ok().body(utilisateur);
    }
}
