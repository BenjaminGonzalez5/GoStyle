package fr.epsi.b3.api.modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "coupon")
public class Coupon {

    @Id
    @NotNull(message = "Code obligatoire")
    @Size(min = 1, message = "Code obligatoire")
    private String code;
    @NotNull(message = "Description du code obligatoire")
    @Size(min = 1, message = "Description du code obligatoire")
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
