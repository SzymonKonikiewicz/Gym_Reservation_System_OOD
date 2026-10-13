package com.example.mas;

import java.math.BigDecimal;
import java.util.Date;

public class Platnosc extends ObjectPlusPlus {

    private int idPlatnosci;
    private static int licznik = 0;

    private Date data;
    private BigDecimal kwota;
    private MetodaPlatnosci metoda;
    private boolean status;

    public Platnosc(MetodaPlatnosci metoda, BigDecimal kwota, Date data) {
        this.idPlatnosci = ++licznik;
        this.metoda = metoda;
        this.kwota = kwota;
        this.data = data;
    }

    public void zaksieguj() {

    }

    public String generuPotwierdzenie() {
        return "";
    }

    @Override
    public String toString() {
        return "\nID Platnosci: " + idPlatnosci + "\nMetoda: " + metoda + "\nKwota: " + kwota + "\nData: " + data;
    }

    //Klient 1 - * Platnosc
    public void addKlient(Klient k) {
        this.addLink(Asocjacja.KLIENT_PLATNOSC.getNazwa(), Asocjacja.PLATNOSC_KLIENT.getNazwa(), k);
    }

    //Platnosc 1 - 1 Karnet
    public void addKarnet(Karnet k) {
        this.addLink(Asocjacja.PLATNOSC_KARNET.getNazwa(), Asocjacja.KARNET_PLATNOSC.getNazwa(), k);
    }
}
