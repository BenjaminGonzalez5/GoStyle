package fr.epsi.b3.api.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import fr.epsi.b3.api.dao.UtilisateurDao;
import fr.epsi.b3.api.modele.Utilisateur;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestUtilisateurService {

    @InjectMocks
    UtilisateurService utilisateurService;

    @Mock
    UtilisateurDao utilisateurDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    /* Test si l'utilisateur est bien renvoyer si on passe un email qui existe dans la base */
    public void getUtilisateurByEmailTest() throws UtilisateurServiceException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setPassword("pass");
        utilisateur.setEmail("aze@aze.fr");
        when(utilisateurDao.getUtilisateurByEmail("aze@aze.fr")).thenReturn(utilisateur);

        Utilisateur userFound = utilisateurService.getUtilisateurByEmail("aze@aze.fr");

        assertThat(userFound).isEqualTo(utilisateur);
    }

    @Test
    public void exceptionGetUtilisateurTest() throws UtilisateurServiceException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setPassword("pass");
        utilisateur.setEmail("aze@aze.fr");
        when(utilisateurDao.getUtilisateurByEmail("aze@aze.fr")).thenReturn(utilisateur);

        assertThrows(UtilisateurServiceException.class, () -> {
            Utilisateur userFound = utilisateurService.getUtilisateurByEmail("mauvais@email.fr");
        });
    }

    @Test
    /* Test si l'utilisateur est bien renvoyer si on passe une combinaison email mot de passe valide */
    public void getUtilisateurByEmailAndPasswordTest() throws InvalidEmailException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setPassword("pass");
        utilisateur.setEmail("aze@aze.fr");
        when(utilisateurDao.getUtilisateurByEmailAndPassword("aze@aze.fr", "pass")).thenReturn(utilisateur);

        Utilisateur userFound = utilisateurService.getUtilisateurByEmailAndPassword(new UtilisateurDto(utilisateur));

        assertThat(userFound).isEqualTo(utilisateur);
    }

    @Test
    public void getUtilisateurById() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setPassword("pass");
        utilisateur.setEmail("aze@aze.fr");
        when(utilisateurDao.getUtilisateurById(1L)).thenReturn(utilisateur);

        Utilisateur userFound = utilisateurService.getUtilisateurById(1L);

        assertThat(userFound).isEqualTo(utilisateur);
    }

}
