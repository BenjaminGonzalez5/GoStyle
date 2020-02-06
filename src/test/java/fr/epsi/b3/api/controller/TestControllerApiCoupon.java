package fr.epsi.b3.api.controller;

import com.google.gson.Gson;
import fr.epsi.b3.api.controleur.ApiGoStyleCouponController;
import fr.epsi.b3.api.controleur.ApiGoStyleUtilisateurController;
import fr.epsi.b3.api.modele.Coupon;
import fr.epsi.b3.api.service.CouponService;
import fr.epsi.b3.api.service.UtilisateurService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestControllerApiCoupon {

    private MockMvc mockMvc;
    private Gson gson = new Gson();

    @Mock
    CouponService couponService;

    @InjectMocks
    private ApiGoStyleCouponController apiGoStyleCouponController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(apiGoStyleCouponController)
                .build();
    }

    @Test
    public void getListCouponsList() throws Exception {
        List<Coupon> couponList = new ArrayList<>();
        Coupon coupon = new Coupon();
        coupon.setCode("AAZE");
        coupon.setDescription("10%");
        Coupon coupon1 = new Coupon();
        coupon1.setCode("RRET");
        coupon1.setDescription("80%");
        couponList.add(coupon);
        couponList.add(coupon1);

        when(couponService.getListCoupons()).thenReturn(couponList);

        mockMvc.perform(get("/coupons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(gson.toJson(couponList)));
    }

    @Test
    public void getCouponFromCode() throws Exception {
        Coupon coupon = new Coupon();
        coupon.setCode("AAZE");
        coupon.setDescription("10%");

        when(couponService.getCouponFromId("AAZE")).thenReturn(coupon);

        mockMvc.perform(get("/coupons/{code}", "AAZE"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(gson.toJson(coupon)));
    }
    
    @Test
    public void testExceptions(){
        // TODO: 06/02/2020
    }
}
