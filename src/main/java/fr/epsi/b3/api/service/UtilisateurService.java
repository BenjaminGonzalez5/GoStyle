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

    @Transactional
    public Utilisateur getUtilisateurByEmail(String s) {
        return utilisateurDao.getUtilisateurByEmail(s);
    }
}
