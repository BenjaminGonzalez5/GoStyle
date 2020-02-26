package fr.epsi.b3.api.service;

import fr.epsi.b3.api.dao.CouponDao;
import fr.epsi.b3.api.modele.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponDao couponDao = new CouponDao();

    /**
     * Renvoie la liste de tout les coupons présents dans la bdd
     * @return
     */
    @Transactional
    public List<Coupon> getListCoupons() {
        return couponDao.getListCoupons();
    }

    /**
     * Renvoie le coupon qui correspond au code entré si il existe
     * @param code
     * @return
     * @throws NoCouponForThisIdException
     */
    @Transactional
    public Coupon getCouponFromId(String code) throws NoCouponForThisIdException {
        Coupon coupon = couponDao.getCouponFromId(code);
        if (coupon == null) {
            throw new NoCouponForThisIdException("Code coupon innexistant");
        }
        return coupon;
    }

    /**
     * Permet de mettre à jour le coupon passé en paramètre
     * @param coupon
     */
    @Transactional
    public void updateCoupon(Coupon coupon) {
        couponDao.updateCoupon(coupon);
    }
}
