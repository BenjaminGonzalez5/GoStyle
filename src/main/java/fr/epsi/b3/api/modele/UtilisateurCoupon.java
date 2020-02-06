package fr.epsi.b3.api.modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity(name = "utilisateur_coupon")
@IdClass(UtilisateurCouponPK.class)
public class UtilisateurCoupon {

    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @Id
    @ManyToOne
    @JoinColumn(name = "coupon_code", nullable = false)
    private Coupon coupon;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
}
