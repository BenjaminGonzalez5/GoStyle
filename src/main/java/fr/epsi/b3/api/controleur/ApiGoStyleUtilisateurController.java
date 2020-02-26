package fr.epsi.b3.api.controleur;

import fr.epsi.b3.api.modele.Coupon;
import fr.epsi.b3.api.modele.Utilisateur;
import fr.epsi.b3.api.service.CouponService;
import fr.epsi.b3.api.service.InvalidEmailException;
import fr.epsi.b3.api.service.PasDUtilisateurPourCetteCombinaisonException;
import fr.epsi.b3.api.service.UtilisateurService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.ArrayList;

@RestController
public class ApiGoStyleUtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private CouponService couponService;

    /**
     * Retourne une erreur lorsque l'id est introuvable
     * @param e
     * @return
     */
    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErreurDto handleBadIdException(NoResultException e) {
        return new ErreurDto(e);
    }

    /**
     * Retourne une erreur lorsque l'email est introuvable
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErreurDto handleInvalidEmailException(InvalidEmailException e) {
        return new ErreurDto(e);
    }

    /**
     * Retourne une erreur lorsque la connexion à la base de données échoue
     * @param e
     * @return
     */
    @ExceptionHandler(PersistenceException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErreurDto handleBadBddConnection(PersistenceException e) {
        return new ErreurDto(e);
    }

    /**
     * Retourne l'utilisateur en fonction de l'id passé en paramètre
     * @param id
     * @return
     */
    @GetMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Utilisateur getById(@PathVariable String id) {
        return utilisateurService.getUtilisateurById(Long.parseLong(id));
    }

    /**
     * Retourne l'utilisateur à partir du JSON passé en paramètre
     * @param utilisateur
     * @return
     * @throws InvalidEmailException
     * @throws PasDUtilisateurPourCetteCombinaisonException
     */
    @PostMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Utilisateur> getUtilisateurFromJsonForm(@RequestBody Utilisateur utilisateur) throws InvalidEmailException, PasDUtilisateurPourCetteCombinaisonException {
        return ResponseEntity.ok().body(utilisateurService.getUtilisateurByEmailAndPassword(utilisateur));
    }

    /**
     *  Retourne l'utilisateur après l'avoir mis à jour
     * @param utilisateur
     * @return
     */
    @PutMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Utilisateur> getUtilisateurAfterUpdate(@RequestBody Utilisateur utilisateur) {
        Utilisateur updatedUtilisateur = updateUtilisateurCouponListe(utilisateur);
        utilisateurService.updateUtilisateur(updatedUtilisateur);
        return ResponseEntity.ok().body(utilisateurService.getUtilisateurById(utilisateur.getId()));
    }

    /**
     *  Met à jour la liste des coupons de l'utilisateur passé en paramètre
     * @param utilisateur
     * @return
     */
    @NotNull
    private Utilisateur updateUtilisateurCouponListe(@RequestBody Utilisateur utilisateur) {
        Utilisateur updatedUtilisateur = utilisateurService.getUtilisateurById(utilisateur.getId());
        for (Coupon c : new ArrayList<>(updatedUtilisateur.getCoupons())) {
            updatedUtilisateur.removeCoupon(c);
            couponService.updateCoupon(c);
        }
        for (Coupon c : new ArrayList<>(utilisateur.getCoupons())) {
            updatedUtilisateur.addCoupon(c);
            couponService.updateCoupon(c);
        }
        return updatedUtilisateur;
    }
}
