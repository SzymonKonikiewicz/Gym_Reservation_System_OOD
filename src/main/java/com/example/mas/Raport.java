package com.example.mas;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Raport extends ObjectPlusPlus{

    private int idRaportu;
    private static int licznik = 0;

    private LocalDateTime dataWygenerowania;
    private Date okresOd;
    private Date okresDo;
    private BigDecimal calkowityPrzychod;
    private int liczbaTransakcji;
    private Administrator autor;

    public Raport(Date okresod, Date okresdo, BigDecimal przychod, int liczbaTransakcji, Administrator autor){

        this.idRaportu = ++licznik;

        this.dataWygenerowania = LocalDateTime.now();
        this.okresOd = okresod;
        this.okresDo = okresdo;
        this.calkowityPrzychod = przychod;
        this.liczbaTransakcji = liczbaTransakcji;
        this.autor = autor;

    }
    @Override
    public String toString(){
        return "ID Raportu: " + idRaportu;
    }
    public void eksportujDoPDF(){

    }
    public String pobierzSzcegoly(){

        return "";
    }

}

