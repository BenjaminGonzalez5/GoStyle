package fr.epsi.b3.api.service;

import fr.epsi.b3.api.modele.Utilisateur;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UtilisateurDto {

    @NotNull(message = "Veuillez renseigner l'adresse mail")
    @Size(min = 1, message = "Veuillez renseigner l'adresse mail")
    private String email;

    @NotNull(message = "Veuillez renseigner le mot de passe")
    @Size(min = 1, message = "Veuillez renseigner le mot de passe")
    private String password;

    public UtilisateurDto(Utilisateur utilisateur){
        this.email = utilisateur.getEmail();
        this.password = utilisateur.getPassword();
    }

    public UtilisateurDto(){
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailException {
        this.email = email;
    }

    public boolean matchRegex() {
        return this.email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
