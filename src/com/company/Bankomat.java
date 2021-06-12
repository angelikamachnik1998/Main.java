package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Bankomat {

    private double max_depozyt;
    private double max_wyplata;
    private List<Klient> klienci;

    public Bankomat() {
        this.max_depozyt = 2000;
        this.max_wyplata = 1000;
        this.klienci = new ArrayList<>();
        this.klienci.add(new Klient(12345678, "klient1", 1111, 5000));
        this.klienci.add(new Klient(99887766, "klient2", 2222, 0));
        this.klienci.add(new Klient(99876543, "klient3", 3333, 340));
    }

    public Klient zaloguj() {
        var scanner = new Scanner(System.in);
        System.out.println("Podaj login:");
        String login = scanner.nextLine();
        Optional<Klient> klientOpt = klienci.stream().filter(e -> e.getLogin().equals(login)).findAny();
        if (klientOpt.isEmpty()) {
            System.out.println("Nieprawidłowy login, spróbuj ponownie.");
            return zaloguj();
        } else {
            Klient klient = klientOpt.get();
            System.out.println("Podaj PIN:");
            if (klient.sprawdzPin(scanner.nextInt())) {
                return klient;
            } else {
                System.out.println("Niepoprawny PIN");
                return zaloguj();
            }
        }
    }

    public void depozyt(Klient klient) {
        System.out.print("Wprowadz kwote do wplaty: ");

        int depozyt_kwota = new Scanner(System.in).nextInt();

        if (depozyt_kwota <= 0) {
            System.out.println("Kwota nie moze byc mniejsza niz 0");
            depozyt(klient);
        }

        if (depozyt_kwota > this.max_depozyt) {
            System.out.println("Kwota nie moze byc wieksza niz: " + this.max_depozyt);
            depozyt(klient);
        }

        klient.setBilans(klient.getBilans() + depozyt_kwota);
    }

    public void wyplata(Klient klient) {
        System.out.print("Wprowadz kwote do wyplaty: ");

        int wyplata_kwota = new Scanner(System.in).nextInt();

        if (wyplata_kwota <= 0) {
            System.out.println("Kwota nie moze byc mniejsza niz 0");
            depozyt(klient);
        }

        if (wyplata_kwota > this.max_wyplata) {
            System.out.println("Kwota nie moze byc wieksza niz: " + this.max_wyplata);
            depozyt(klient);
        }

        if (klient.getBilans() - wyplata_kwota < 0) {
            System.out.println("Nie posiadasz wystarczajacej ilosci srodkow");
        }

        klient.setBilans(klient.getBilans() - wyplata_kwota);
    }

    public void menu() {

        Klient klient = zaloguj();

        System.out.println("Wybierz opcje z menu");
        System.out.println("1. Wyplac");
        System.out.println("2. Depozyt");
        System.out.println("3. Bilans");

        switch (new Scanner(System.in).nextInt()) {
            case 1:
                wyplata(klient);
                System.out.println("Nowy stan konta: " + klient.getBilans());
                break;
            case 2:
                depozyt(klient);
                System.out.println("Nowy stan konta: " + klient.getBilans());
                break;
            case 3:
                System.out.println("Twoj: bilans " + klient.getBilans());
                break;
        }

        if (!endTransactions()) {
            menu();
        }
        ;
    }

    private boolean endTransactions() {
        System.out.println("Konczymy na dzis? [y/n]");
        String answer = new Scanner(System.in).nextLine();
        if (answer.equals("y")) {
            return true;
        } else if (answer.equals("n")) {
            return false;
        } else {
            return endTransactions();
        }
    }
}