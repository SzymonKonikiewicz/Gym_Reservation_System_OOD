package com.example.mas;

import java.time.LocalDate;

public abstract class Osoba extends ObjectPlusPlus {
    protected String pesel;
    protected String imie;
    protected String nazwisko;
    protected LocalDate dataUrodzenia;
    protected String email;
    protected String telefon;

    public Osoba(String pesel, String imie, String nazwisko, LocalDate dataUrodzenia, String email, String telefon) {
        super();
        this.pesel = pesel;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.email = email;
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "PESEL: " + pesel + "\nimie: " + imie + "\nnazwisko: " + nazwisko + "\nemail: " + email + "\ntelefon: " + telefon;
        //+ "\ndataUrodzenia: " + dataUrodzenia
    }

    public String getName(){
        return imie;
    }
}
