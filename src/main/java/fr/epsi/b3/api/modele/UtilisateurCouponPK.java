package fr.epsi.b3.api.modele;

import javax.persistence.JoinColumn;
import java.io.Serializable;

public class UtilisateurCouponPK implements Serializable {
    @JoinColumn(name = "utilisateur_id", insertable = false, updatable = false)
    protected Utilisateur utilisateur;
    @JoinColumn(name = "coupon_code", insertable = false, updatable = false)
    protected Coupon coupon;

    public UtilisateurCouponPK(){}
    public UtilisateurCouponPK(Utilisateur id_utilisateur, Coupon code_coupon){
        this.utilisateur = id_utilisateur;
        this.coupon = code_coupon;
    }
}
