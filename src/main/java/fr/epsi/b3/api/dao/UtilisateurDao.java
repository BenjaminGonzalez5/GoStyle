package fr.epsi.b3.api.dao;

import fr.epsi.b3.api.modele.Utilisateur;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UtilisateurDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Utilisateur getUtilisateurByEmail(String email) {
        return entityManager.createQuery("select u from utilisateur u where u.email = :param", Utilisateur.class)
                .setParameter("param", email)
                .getSingleResult();
    }

    public Utilisateur getUtilisateurById(long id) {
        return entityManager.createQuery("select u from utilisateur u where u.id = :param", Utilisateur.class)
                .setParameter("param", id)
                .getSingleResult();
    }

    public Utilisateur getUtilisateurByEmailAndPassword(String email, String pass) {
        return entityManager.createQuery("select u from utilisateur u where u.email = :paramEmail and u.password = :paramPass", Utilisateur.class)
                .setParameter("paramEmail", email)
                .setParameter("paramPass", pass)
                .getSingleResult();
    }

    public void updateUtilisateur(Utilisateur utilisateur) {
        entityManager.merge(utilisateur);
        entityManager.flush();
    }
}
