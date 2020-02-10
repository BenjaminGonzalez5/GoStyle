package fr.epsi.b3.api.dao;

import fr.epsi.b3.api.modele.Coupon;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CouponDao {

    @PersistenceContext
    private EntityManager entityManager;

//    Récupère directement la liste de tout les coupons en base et la retourne
    public List<Coupon> getListCoupons() {
        return entityManager.createQuery("select c from coupon c", Coupon.class)
                .getResultList();
    }

    public Coupon getCouponFromId(String code) {
        return entityManager.createQuery("select c from coupon c where c.code = :param", Coupon.class)
                .setParameter("param", code)
                .getSingleResult();
    }

    public void updateCoupon(Coupon coupon) {
        entityManager.merge(coupon);
        entityManager.flush();
    }
}
