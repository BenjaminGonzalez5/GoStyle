package fr.epsi.gostyle.modele;

import com.google.gson.JsonDeserializer;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Coupon implements Serializable {

    private String code;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private boolean valid = true;

    public Coupon(JSONObject jsonObject) throws ParseException {
        code = jsonObject.optString("code", "");
        description = jsonObject.optString("description", "");
        dateDebut = df.parse(jsonObject.optString("date_debut", ""));
        dateFin = df.parse(jsonObject.optString("date_fin", ""));
        Date now = new Date();
        if (now.after(dateFin)){
            valid = false;
        }
    }

    public String getDateFin() {
        return df.format(dateFin);
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getDateDebut() {
        return df.format(dateDebut);
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
