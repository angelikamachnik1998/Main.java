package com.company;

public class Klient {

    private int nrKonta;
    private String login;
    private int pin;
    private double bilans;

    public Klient(int nrKonta, String login, int pin, double bilans) {
        this.nrKonta = nrKonta;
        this.login = login;
        this.pin = pin;
        this.bilans = bilans;
    }

    public int getNrKonta() {
        return nrKonta;
    }

    public void setNrKonta(int nrKonta) {
        this.nrKonta = nrKonta;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBilans() {
        return bilans;
    }

    public void setBilans(double bilans) {
        this.bilans = bilans;
    }

    public boolean sprawdzPin(int pin) {
        return this.pin == pin;
    }
}
