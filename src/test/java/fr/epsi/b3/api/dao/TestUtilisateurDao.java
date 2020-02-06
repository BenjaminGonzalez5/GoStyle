/*package fr.epsi.b3.api.dao;

import fr.epsi.b3.api.modele.Utilisateur;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestUtilisateurDao{

    @Mock
    EntityManager entityManager;

    @Mock
    Query query;

    @InjectMocks
    UtilisateurDao utilisateurDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUtilisateurByIdRetourneUtilisateurTest(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setEmail("thibault@nesti.fr");
        utilisateur.setPassword("unpass");

        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.setParameter(anyString(), anyLong())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(utilisateur);

        Utilisateur found = utilisateurDao.getUtilisateurById(1L);

        assertThat(found).isEqualTo(utilisateur);
    }


}*/
/*public class TestUtilisateurDao {

    private MockMvc mockMvc;

    @InjectMocks
    UtilisateurDao utilisateurDao;

    @Mock
    EntityManager entityManager;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUtilisateurFromEmailDao(){
        // TODO: 05/02/2020  
    }

}*/
