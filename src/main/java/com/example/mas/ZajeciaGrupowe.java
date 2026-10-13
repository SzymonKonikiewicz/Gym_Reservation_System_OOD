package com.example.mas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ZajeciaGrupowe extends ObjectPlusPlus {

    private int idZajec;
    private static int licznik = 0;

    private String nazwa;
    private int maxUczestnikow;
    private LocalDateTime dataRozpoczecia;

    public ZajeciaGrupowe(String nazwa, int max, LocalDateTime data) {
        this.idZajec = ++licznik;
        this.nazwa = nazwa;
        this.maxUczestnikow = max;
        this.dataRozpoczecia = data;
    }

    @Override
    public String toString() {
        return "\nID Zajec: " + idZajec + "\nNazwa: " + nazwa + "\nStart: " + formatDate() + "\nMax Uczestnikow: " + maxUczestnikow;
    }

    public String formatDate(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return this.dataRozpoczecia.format(format);
    }
    public String getNazwa(){
        return nazwa;
    }

    //ZajeciaGrupowe * - 1 Sala
    public void addSala(Sala s) {
        this.addLink(Asocjacja.ZAJECIAGRUPOWE_SALA.getNazwa(), Asocjacja.SALA_ZAJECIAGRUPOWE.getNazwa(), s);
    }

    //ZajeciaGrupowe 1 - * Rezerwacja
    public void addRezerwacja(Rezerwacja r) {
        this.addLink(Asocjacja.ZAJECIAGRUPOWE_REZERWACJA.getNazwa(), Asocjacja.REZERWACJA_ZAJECIAGRUPOWE.getNazwa(), r);
    }

    //ZajeciaGrupowe * - 1 Trener
    public void addTrener(Trener t) {
        this.addLink(Asocjacja.ZAJECIAGRUPOWE_TRENER.getNazwa(), Asocjacja.TRENER_ZAJECIAGRUPOWE.getNazwa(), t);
    }

    public int getMaxUczestnikow(){
        return maxUczestnikow;
    }

    public int dostepnoscMiejsc() {
        int zajeteMiejsca = 0;
        try {
            ObjectPlusPlus[] rezerwacje = this.getLinks(Asocjacja.ZAJECIAGRUPOWE_REZERWACJA.getNazwa());
            for(ObjectPlusPlus obj : rezerwacje){
                Rezerwacja r = (Rezerwacja) obj;
                zajeteMiejsca++;
            }
        }
        catch (Exception e){
            return 0;
        }
        return zajeteMiejsca;
    }
}

