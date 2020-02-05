package fr.epsi.b3.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.thetransactioncompany.cors.CORSFilter;
import fr.epsi.b3.api.controleur.AccueilController;
import fr.epsi.b3.api.modele.Utilisateur;
import fr.epsi.b3.api.service.UtilisateurDto;
import fr.epsi.b3.api.service.UtilisateurService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class TestControllerApi {

    private MockMvc mockMvc;

    @Mock
    UtilisateurService utilisateurService;

    @InjectMocks
    private AccueilController accueilController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(accueilController)
                .addFilters(new CORSFilter())
                .build();
    }

    @Test
    public void fromFormGetUtilisateurTest() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setPassword("pass");
        utilisateur.setEmail("aze@aze.fr");
        Gson gson = new Gson();

        when(utilisateurService.getUtilisateurById(1L)).thenReturn(utilisateur);

        mockMvc.perform(get("/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$.id", is(1)))
                .andExpect((ResultMatcher) jsonPath("$.email", is("aze@aze.fr")));
        verify(utilisateurService, times(1)).getUtilisateurById(1);
        verifyNoMoreInteractions(utilisateurService);
    }
}
