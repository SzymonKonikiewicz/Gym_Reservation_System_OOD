package com.example.mas;

import java.time.LocalDate;

public class Recepcjonista extends Osoba {

    public Recepcjonista(String pesel, String imie, String nazwisko, LocalDate dataUrodzenia, String email, String telefon) {
        super(pesel, imie, nazwisko, dataUrodzenia, email, telefon);
    }

    @Override
    public String toString() {
        return "Recepcjonista: " + super.toString();
    }
}

