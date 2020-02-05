package fr.epsi.b3.api.controleur;

public class ErreurDto {

    private String message;

    public ErreurDto(Exception e) {
        this.message = e.getMessage();
    }

    public String getMessage() {
        return message;
    }
}