package fr.epsi.b3.api.service;

import fr.epsi.b3.api.dao.UtilisateurDao;
import fr.epsi.b3.api.modele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurDao utilisateurDao = new UtilisateurDao();

    @Transactional(rollbackFor = UtilisateurServiceException.class)
    /* Renvoi l'utilisateur rataché a l'email passer en paramettre
     * un erreur si l'email est incorrecte */
    public Utilisateur getUtilisateurByEmail(String s) throws UtilisateurServiceException {
        if (utilisateurDao.getUtilisateurByEmail(s) == null) {
            throw new UtilisateurServiceException("Aucun compte utilisateur trouvé.");
        }
        return utilisateurDao.getUtilisateurByEmail(s);
    }

    @Transactional
//    Renvoie l'utilisateur correspondant a la combinaison mdp email si il existe dans la base
    public Utilisateur getUtilisateurByEmailAndPassword(Utilisateur utilisateur) throws InvalidEmailException, PasDUtilisateurPourCetteCombinaisonException {
        if (! utilisateur.matchRegex()){ throw new InvalidEmailException("Email Invalid"); }
        Utilisateur foundUtilisateur = utilisateurDao.getUtilisateurByEmailAndPassword(utilisateur.getEmail(), utilisateur.getPassword());
        if (foundUtilisateur == null){
            throw new PasDUtilisateurPourCetteCombinaisonException("Email ou mot de passe invalide");
        }
        return utilisateurDao.getUtilisateurByEmailAndPassword(utilisateur.getEmail(), utilisateur.getPassword());
    }

    @Transactional
//    Renvoie l'utilisateur correspondant a l'id
    public Utilisateur getUtilisateurById(long id) {
        return utilisateurDao.getUtilisateurById(id);
    }

    @Transactional
//  Met a jour l'utilisateur dans la bdd et le retourne
    public void updateUtilisateur(Utilisateur utilisateur) {
        utilisateurDao.updateUtilisateur(utilisateur);
    }
}
