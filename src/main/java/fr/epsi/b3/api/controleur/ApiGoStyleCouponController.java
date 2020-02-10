package fr.epsi.b3.api.controleur;

import fr.epsi.b3.api.modele.Coupon;
import fr.epsi.b3.api.service.CouponService;
import fr.epsi.b3.api.service.NoCouponForThisIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiGoStyleCouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping(path = "/coupons", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Coupon> httpGetForCouponsList() {
        return couponService.getListCoupons();
    }

    @GetMapping(path = "/coupons/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Coupon httpGetCouponFromCode(@PathVariable String code) throws NoCouponForThisIdException {
        return couponService.getCouponFromId(code);
    }
}
