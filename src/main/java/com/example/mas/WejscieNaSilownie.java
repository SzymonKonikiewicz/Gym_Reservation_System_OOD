package com.example.mas;

import java.time.Duration;
import java.time.LocalDateTime;

public class WejscieNaSilownie extends ObjectPlusPlus {

    private int idWejscia;
    private static int licznik = 0;

    private LocalDateTime dataWejscia;
    private LocalDateTime dataWyjscia;
    private Klient klient;

    public WejscieNaSilownie() {
        this.idWejscia = ++licznik;

    }

    public void zarejestrujWyjscie() {

    }

    //Timespan -> Duration
    public Duration obliczCzasPobytu() {
        Duration czasPobytu = Duration.between(this.dataWejscia, this.dataWyjscia);
        return czasPobytu;
    }

    @Override
    public String toString() {
        return "\nID wejscia: " + idWejscia;
    }

    //Klient 1 - * WejscieNaSilownie
    public void addKlient(Klient k) {
        this.addLink(Asocjacja.WEJSCIENASILOWNIE_KLIENT.getNazwa(), Asocjacja.KLIENT_WEJSCIENASILOWNIE.getNazwa(), k);
    }

}
