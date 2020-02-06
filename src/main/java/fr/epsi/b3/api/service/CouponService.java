package fr.epsi.b3.api.service;

import fr.epsi.b3.api.dao.CouponDao;
import fr.epsi.b3.api.modele.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponDao couponDao = new CouponDao();

    @Transactional
//    Renvoie la liste de tout les coupons présents dans la bdd
    public List<Coupon> getListCoupons() {
        return couponDao.getListCoupons();
    }

    @Transactional
//    Renvoie le coupon qui correspond au code entré si il existe
    public Coupon getCouponFromId(String code) throws NoCouponForThisIdException {
        Coupon coupon = couponDao.getCouponFromId(code);
        if(coupon == null){
            throw new NoCouponForThisIdException("Code coupon innexistant");
        }
        return coupon;
    }
}
