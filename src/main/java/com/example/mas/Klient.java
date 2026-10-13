package com.example.mas;

import java.time.LocalDate;

public class Klient extends Osoba {

    public Klient(String pesel, String imie, String nazwisko, LocalDate dataUrodzenia, String email, String telefon) {
        super(pesel, imie, nazwisko, dataUrodzenia, email, telefon);
    }

    //Klient 1 - * Rezerwacja
    public void addRezerwacja(Rezerwacja r) {
        this.addLink(Asocjacja.KLIENT_REZERWACJA.getNazwa(), Asocjacja.REZERWACJA_KLIENT.getNazwa(), r);
    }

    //Klient 1 - * Karnet
    public void addKarnet(Karnet k) {
        this.addLink(Asocjacja.KLIENT_KARNET.getNazwa(), Asocjacja.KARNET_KLIENT.getNazwa(), k);
    }

    //Klient 1 - * WejscieNaSilownie
    public void addWejscieNaSilownie(WejscieNaSilownie w) {
        this.addLink(Asocjacja.KLIENT_WEJSCIENASILOWNIE.getNazwa(), Asocjacja.WEJSCIENASILOWNIE_KLIENT.getNazwa(), w);
    }

    //Klient 1 - * Platnosc
    public void addPlatnosc(Platnosc p) {
        this.addLink(Asocjacja.KLIENT_PLATNOSC.getNazwa(), Asocjacja.PLATNOSC_KLIENT.getNazwa(), p);
    }

    @Override
    public String toString() {
        return "\nKlient: " + super.toString();
    }


    public String getImie(){
        return imie;
    }
    public String getNazwisko(){
        return nazwisko;
    }

    public String getPesel(){
        return this.pesel;
    }

}
