package fr.epsi.b3.api.dao;

import fr.epsi.b3.api.modele.Utilisateur;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UtilisateurDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Récupère directement un utilisateur à partir de son email passé en paramètre
     * @param email
     * @return
     */
    public Utilisateur getUtilisateurByEmail(String email) {
        return entityManager.createQuery("select u from utilisateur u where u.email = :param", Utilisateur.class)
                .setParameter("param", email)
                .getSingleResult();
    }

    /**
     * Récupère directement un utilisateur à partir de son id passé en paramètre
     * @param id
     * @return
     */
    public Utilisateur getUtilisateurById(long id) {
        return entityManager.createQuery("select u from utilisateur u where u.id = :param", Utilisateur.class)
                .setParameter("param", id)
                .getSingleResult();
    }

    /**
     * Récupère directement un utilisateur à partir de son email et de son mot de passe passé en paramètre
     * @param email
     * @param pass
     * @return
     */
    public Utilisateur getUtilisateurByEmailAndPassword(String email, String pass) {
        return entityManager.createQuery("select u from utilisateur u where u.email = :paramEmail and u.password = :paramPass", Utilisateur.class)
                .setParameter("paramEmail", email)
                .setParameter("paramPass", pass)
                .getSingleResult();
    }

    /**
     * Permet de mettre à jour l'utilisateur passé en paramètre
     * @param utilisateur
     */
    public void updateUtilisateur(Utilisateur utilisateur) {
        entityManager.merge(utilisateur);
        entityManager.flush();
    }
}
