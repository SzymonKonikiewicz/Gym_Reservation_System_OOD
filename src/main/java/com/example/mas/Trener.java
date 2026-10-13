package com.example.mas;

import java.time.LocalDate;

public class Trener extends Osoba {

    public Trener(String pesel, String imie, String nazwisko, LocalDate dataUrodzenia, String email, String telefon) {
        super(pesel, imie, nazwisko, dataUrodzenia, email, telefon);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    //Trener 1 - * ZajeciaGrupowe
    public void addZajeciaGrupowe(ZajeciaGrupowe z) {
        this.addLink(Asocjacja.TRENER_ZAJECIAGRUPOWE.getNazwa(), Asocjacja.ZAJECIAGRUPOWE_TRENER.getNazwa(), z);
    }

}

