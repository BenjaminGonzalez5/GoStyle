package fr.epsi.b3.api.controleur;

import fr.epsi.b3.api.modele.Coupon;
import fr.epsi.b3.api.modele.Utilisateur;
import fr.epsi.b3.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiGoStyleUtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private CouponService couponService;

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

    @ExceptionHandler(PersistenceException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErreurDto handleBadBddConnection(PersistenceException e){ return new ErreurDto(e); }

    @GetMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Utilisateur getById(@PathVariable String id) {
        return utilisateurService.getUtilisateurById(Long.parseLong(id));
    }

    @PostMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Utilisateur> getUtilisateurFromJsonForm(@RequestBody Utilisateur utilisateur) throws InvalidEmailException, PasDUtilisateurPourCetteCombinaisonException {
        return ResponseEntity.ok().body(utilisateurService.getUtilisateurByEmailAndPassword(utilisateur));
    }

    @PutMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Utilisateur> getUtilisateurAfterUpdate(@RequestBody Utilisateur utilisateur) {
//        List<Utilisateur> utilisateurs = new ArrayList<>();
//        Utilisateur utilisateurAUpdate = new Utilisateur();
//        utilisateurAUpdate.setId(utilisateur.getId());
//        utilisateurAUpdate.setEmail(utilisateur.getEmail());
//        utilisateurAUpdate.setPassword(utilisateur.getPassword());
//        utilisateurs.add(utilisateurAUpdate);
//        for (Coupon coupon:utilisateur.getCoupons()
//             ) {
//            coupon.setUtilisateurs(utilisateurs);
//            utilisateurAUpdate.getCoupons().add(coupon);
//        }
//        utilisateurService.updateUtilisateur(utilisateurAUpdate);
//        return ResponseEntity.ok().body(utilisateurService.getUtilisateurById(utilisateur.getId())utilisateurService.getUtilisateurById(utilisateur.getId()));
        Utilisateur updatedUtilisateur = utilisateurService.getUtilisateurById(utilisateur.getId());
        for (Coupon c : new ArrayList<>(updatedUtilisateur.getCoupons())){
            updatedUtilisateur.removeCoupon(c);
        }
        for (Coupon c : new ArrayList<>(utilisateur.getCoupons())){
            updatedUtilisateur.addCoupon(c);
        }
        utilisateurService.updateUtilisateur(updatedUtilisateur);
        return ResponseEntity.ok().body(utilisateurService.getUtilisateurById(utilisateur.getId()));
    }
}
