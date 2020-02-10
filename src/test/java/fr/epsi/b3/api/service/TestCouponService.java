package fr.epsi.b3.api.service;

import fr.epsi.b3.api.dao.CouponDao;
import fr.epsi.b3.api.modele.Coupon;
import fr.epsi.b3.api.modele.Utilisateur;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestCouponService {

    @InjectMocks
    private CouponService couponService;

    @Mock
    private CouponDao couponDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getListDesCouponsTest(){
        List<Coupon> couponList = new ArrayList<>();
        Coupon coupon = new Coupon();
        coupon.setCode("AAZE");
        coupon.setDescription("10%");
        Coupon coupon1 = new Coupon();
        coupon1.setCode("RRET");
        coupon1.setDescription("80%");
        couponList.add(coupon);
        couponList.add(coupon1);

        when(couponDao.getListCoupons()).thenReturn(couponList);

        assertThat(couponService.getListCoupons()).isEqualTo(couponList);
    }

    @Test
    public void getCouponFromIdTest() throws NoCouponForThisIdException {
        Coupon coupon = new Coupon();
        coupon.setCode("AAZE");
        coupon.setDescription("10%");

        when(couponDao.getCouponFromId("AAZE")).thenReturn(coupon);

        assertThat(couponService.getCouponFromId("AAZE")).isEqualTo(coupon);
    }

    @Test
    public void codeCouponNexistePasRenvoieException(){
        Coupon coupon = new Coupon();
        coupon.setCode("AAZE");
        coupon.setDescription("10%");

        when(couponDao.getCouponFromId("AAZE")).thenReturn(coupon);

        assertThrows(NoCouponForThisIdException.class, () -> {
            Coupon couponFound = couponService.getCouponFromId("mauvaisCode");
        });
    }

}
