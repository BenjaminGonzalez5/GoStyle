package fr.epsi.b3.api.dao;

import fr.epsi.b3.api.modele.Coupon;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CouponDao {

    @PersistenceContext
    private EntityManager entityManager;
  
    /**
     * Récupère directement la liste de tout les coupons en base et la retourne
     * @return
     */
    public List<Coupon> getListCoupons() {
        return entityManager.createQuery("select c from coupon c", Coupon.class)
                .getResultList();
    }

    /**
     * Récupère directement le coupon en base en fonction du code passé en paramètre
     * @param code
     * @return
     */
    public Coupon getCouponFromId(String code) {
        return entityManager.createQuery("select c from coupon c where c.code = :param", Coupon.class)
                .setParameter("param", code)
                .getSingleResult();
    }

    /**
     * Permet de mettre à jour le coupon passé en paramètre
     * @param coupon
     */
    public void updateCoupon(Coupon coupon) {
        entityManager.merge(coupon);
        entityManager.flush();
    }
}
