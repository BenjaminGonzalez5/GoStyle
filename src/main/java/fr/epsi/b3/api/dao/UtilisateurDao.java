package fr.epsi.b3.api.dao;

import fr.epsi.b3.api.modele.Utilisateur;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UtilisateurDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Utilisateur getUtilisateurByEmail(String email){
        return entityManager.createQuery("select u from utilisateur u where u.email = :param", Utilisateur.class)
        .setParameter("param", email)
        .getSingleResult();
    }
}
