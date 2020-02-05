package fr.epsi.b3.api.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;

public class TestUtilisateurDao {

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

}
