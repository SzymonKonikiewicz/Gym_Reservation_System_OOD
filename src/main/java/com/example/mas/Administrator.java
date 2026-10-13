package com.example.mas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Administrator extends Osoba {

    public Administrator(String pesel, String imie, String nazwisko, LocalDate dataUrodzenia, String email, String telefon) {
        super(pesel, imie, nazwisko, dataUrodzenia, email, telefon);
    }

    @Override
    public String toString() {
        return "Admin: " + super.toString();
    }

    public Raport generujRaportFinansowy() {
        Raport r = new Raport(new Date(2026, 1, 1), new Date(2026, 1, 2), new BigDecimal(10000.00), 12, this);
        return r;
    }
}
