package fr.epsi.b3.api.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import fr.epsi.b3.api.dao.UtilisateurDao;
import fr.epsi.b3.api.modele.Coupon;
import fr.epsi.b3.api.modele.Utilisateur;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class TestUtilisateurService {

    private Utilisateur utilisateur = new Utilisateur();
    private Coupon coupon = new Coupon();

    @InjectMocks
    UtilisateurService utilisateurService;

    @Mock
    UtilisateurDao utilisateurDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        utilisateur.setId(1L);
        utilisateur.setPassword("pass");
        utilisateur.setEmail("aze@aze.fr");
        coupon.setCode("AAEE");
        coupon.setDescription("10% T-Shirts");
        coupon.setCode("OOPP");
        coupon.setDescription("10% swets");
    }

    /**
     * Test si l'utilisateur est bien renvoyer si on passe un email qui existe dans la base
     * @throws UtilisateurServiceException
     */
    @Test
    public void getUtilisateurByEmailTest() throws UtilisateurServiceException {
        when(utilisateurDao.getUtilisateurByEmail("aze@aze.fr")).thenReturn(utilisateur);

        Utilisateur userFound = utilisateurService.getUtilisateurByEmail("aze@aze.fr");

        assertThat(userFound).isEqualTo(utilisateur);
    }

    /**
     * Renvoie une erreur si l'utilisateur n'est pas dans la base de données
     * @throws UtilisateurServiceException
     */
    @Test
    public void exceptionGetUtilisateurTest() throws UtilisateurServiceException {
        when(utilisateurDao.getUtilisateurByEmail("aze@aze.fr")).thenReturn(utilisateur);

        assertThrows(UtilisateurServiceException.class, () -> {
            Utilisateur userFound = utilisateurService.getUtilisateurByEmail("mauvais@email.fr");
        });
    }

    /**
     * Test si l'utilisateur est bien renvoyer si on passe une combinaison email mot de passe valide
     * @throws InvalidEmailException
     * @throws PasDUtilisateurPourCetteCombinaisonException
     */
    @Test
    public void getUtilisateurByEmailAndPasswordTest() throws InvalidEmailException, PasDUtilisateurPourCetteCombinaisonException {
        when(utilisateurDao.getUtilisateurByEmailAndPassword("aze@aze.fr", "pass")).thenReturn(utilisateur);

        Utilisateur userFound = utilisateurService.getUtilisateurByEmailAndPassword(utilisateur);

        assertThat(userFound).isEqualTo(utilisateur);
    }

    /**
     * Test si l'utilisateur est bien renvoyer si on passe un id valide
     * @throws InvalidEmailException
     * @throws PasDUtilisateurPourCetteCombinaisonException
     */
    @Test
    public void getUtilisateurByIdTest() {
        when(utilisateurDao.getUtilisateurById(1L)).thenReturn(utilisateur);

        Utilisateur userFound = utilisateurService.getUtilisateurById(1L);

        assertThat(userFound).isEqualTo(utilisateur);
    }

    @Test
    public void testExceptionEmailEtc(){
        // TODO: 06/02/2020  
    }

}
