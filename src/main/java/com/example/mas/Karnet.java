package com.example.mas;

import java.time.LocalDateTime;

public class Karnet extends ObjectPlusPlus {

    private int idKarnetu;
    private static int licznik = 0;

    private TypKarnetu typ;
    private LocalDateTime dataWaznosci;
    private Platnosc platnosc;
    boolean Wazny = false;

    public Karnet(TypKarnetu typ, LocalDateTime dataWaznosci, Platnosc platnosc) {
        this.idKarnetu = ++licznik;
        this.typ = typ;
        this.dataWaznosci = dataWaznosci;
        this.platnosc = platnosc;

    }

    @Override
    public String toString() {
        return "\nID Karnetu: " + idKarnetu + "\nTyp Karnetu: " + typ + "\nData Waznosci: " + dataWaznosci + "\nPlatnosc: " + platnosc;
    }

    //Karnet * - 1 Klient
    public void addKlient(Klient k) {
        this.addLink(Asocjacja.KARNET_KLIENT.getNazwa(), Asocjacja.KLIENT_KARNET.getNazwa(), k);
    }

    //Karnet 1 - 1 Platnosc
    public void addPlatnosc(Platnosc p) {
        this.addLink(Asocjacja.KARNET_PLATNOSC.getNazwa(), Asocjacja.PLATNOSC_KARNET.getNazwa(), p);
    }

    public void setWazny(boolean b){
        this.Wazny = b;
    }


}

