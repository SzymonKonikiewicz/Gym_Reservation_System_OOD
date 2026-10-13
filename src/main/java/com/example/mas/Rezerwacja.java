package com.example.mas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Rezerwacja extends ObjectPlusPlus {

    private LocalDateTime dataUtworzenia;
    private StatusRezerwacji status;

    public Rezerwacja(LocalDateTime dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
        this.status = StatusRezerwacji.OCZEKUJACA;
    }
    public Rezerwacja(LocalDateTime dataUtworzenia, StatusRezerwacji status){
        this.dataUtworzenia = dataUtworzenia;
        this.status = status;
    }

    //Rezerwacja * - 1 ZajeciaGrupowe
    public void addZajeciaGrupowe(ZajeciaGrupowe z) {
        this.addLink(Asocjacja.REZERWACJA_ZAJECIAGRUPOWE.getNazwa(), Asocjacja.ZAJECIAGRUPOWE_REZERWACJA.getNazwa(), z);
    }

    @Override
    public String toString() {
        return "\nStatus: " + status + "\nData Utworzenia: " + sformatowanaData();
    }

    public String sformatowanaData(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return this.dataUtworzenia.format(format);
    }
    public LocalDateTime getData(){
        return this.dataUtworzenia;
    }

    public StatusRezerwacji pobierzStatus() {
        return status;
    }

    public void zmienStatus(StatusRezerwacji status){
        this.status = status;
    }

    //Klient 1 - * Rezerwacja
    public void addKlient(Klient k) {
        this.addLink(Asocjacja.REZERWACJA_KLIENT.getNazwa(), Asocjacja.KLIENT_REZERWACJA.getNazwa(), k);
    }

}

