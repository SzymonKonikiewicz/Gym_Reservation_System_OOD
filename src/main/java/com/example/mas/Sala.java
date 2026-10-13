package com.example.mas;

public class Sala extends ObjectPlusPlus {

    private String numerSali;
    private int pojemnosc;
    private boolean czyDostepna;

    public Sala(String numerSali, int pojemnosc, boolean czyDostepna) {
        this.numerSali = numerSali;
        this.pojemnosc = pojemnosc;
        this.czyDostepna = czyDostepna;
    }

    public boolean czyWolna() {
        return czyDostepna;
    }

    public void zmienStatus(boolean dostpena) {
        this.czyDostepna = dostpena;
    }

    @Override
    public String toString() {
        return "\nNumer Sali: " + numerSali + "\nPojemnosc: " + pojemnosc + "\nDostepnosc: " + czyDostepna;
    }

    //ZajeciaGrupowe * - * Sala
    public void addZajecia(ZajeciaGrupowe z) {
        this.addLink(Asocjacja.ZAJECIAGRUPOWE_SALA.getNazwa(), Asocjacja.SALA_ZAJECIAGRUPOWE.getNazwa(), z);
    }
}

