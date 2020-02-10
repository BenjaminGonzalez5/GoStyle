package fr.epsi.b3.api.controller;

import com.google.gson.Gson;
import fr.epsi.b3.api.controleur.ApiGoStyleUtilisateurController;
import fr.epsi.b3.api.modele.Coupon;
import fr.epsi.b3.api.modele.Utilisateur;
import fr.epsi.b3.api.service.CouponService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestControllerApiUtilisateur {

    private MockMvc mockMvc;
    private Gson gson = new Gson();

    @Mock
    UtilisateurService utilisateurService;

    @Mock
    CouponService couponService;

    @InjectMocks
    private ApiGoStyleUtilisateurController apiGoStyleUtilisateurController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(apiGoStyleUtilisateurController)
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

//    @Test
//    public void getUtilisateurAfterUpdate() throws Exception {
//        Utilisateur utilisateur = new Utilisateur();
//        List<Coupon> couponList= new ArrayList<>();
//        utilisateur.setEmail("nouveau@email.com");
//        utilisateur.setPassword("pass");
//        utilisateur.setId(1L);
//        Coupon coupon = new Coupon();
//        coupon.setCode("BBGH");
//        coupon.setDescription("90%");
//        couponList.add(coupon);
//        utilisateur.setCoupons(couponList);
//
//        doNothing().when(couponService).updateCoupon(coupon);
//        when(utilisateurService.getUtilisateurById(utilisateur.getId())).thenReturn(utilisateur);
//
//        mockMvc.perform(put("/user")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE)
//                .content(gson.toJson(utilisateur)))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(content().json(gson.toJson(utilisateur)))
//                .andExpect(status().isOk());
//    }
}
