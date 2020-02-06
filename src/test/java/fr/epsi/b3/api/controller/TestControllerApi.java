package fr.epsi.b3.api.controller;

import com.google.gson.Gson;
import fr.epsi.b3.api.controleur.ApiGoStyleController;
import fr.epsi.b3.api.modele.Utilisateur;
import fr.epsi.b3.api.service.InvalidEmailException;
import fr.epsi.b3.api.service.UtilisateurService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.NoResultException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class TestControllerApi {

    private MockMvc mockMvc;
    private Gson gson = new Gson();

    @Mock
    UtilisateurService utilisateurService;

    @InjectMocks
    private ApiGoStyleController apiGoStyleController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(apiGoStyleController)
                .build();
    }

    @Test
    public void getUserFromIdTest() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setPassword("pass");
        utilisateur.setEmail("aze@aze.fr");

        when(utilisateurService.getUtilisateurById(1L)).thenReturn(utilisateur);

        mockMvc.perform(get("/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(gson.toJson(utilisateur)));
    }

    @Test
    public void getBadRequestWhenIdDontExiste() throws Exception {

        when(utilisateurService.getUtilisateurById(2L)).thenThrow(new NoResultException());

        mockMvc.perform(get("/user/{id}", 2))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getUserFromEmailAndPasswordJson() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setPassword("pass");
        utilisateur.setEmail("aze@aze.fr");

        when(utilisateurService.getUtilisateurByEmailAndPassword(any(Utilisateur.class))).thenReturn(utilisateur);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(utilisateur)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(gson.toJson(utilisateur)))
                .andExpect(status().isOk());
    }

    @Test
    public void getBadRequestWhenEmailIsInvalid() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setPassword("pass");
        utilisateur.setEmail("azeaze.fr");

        when(utilisateurService.getUtilisateurByEmailAndPassword(any(Utilisateur.class))).thenThrow(new InvalidEmailException("Email Invalid"));

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(utilisateur)))
                .andExpect(status().isBadRequest());
    }
}
