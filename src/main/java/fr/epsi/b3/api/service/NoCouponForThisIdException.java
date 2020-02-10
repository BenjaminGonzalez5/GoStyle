package fr.epsi.b3.api.service;

public class NoCouponForThisIdException extends Exception {
    public NoCouponForThisIdException(String message) {
        super(message);
    }
}
