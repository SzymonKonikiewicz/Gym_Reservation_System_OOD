package com.example.mas;

import java.math.BigDecimal;

public enum TypKarnetu {
    JEDNORAZOWY("Jednorazowy", new BigDecimal(50)),
    OPEN("Open", new BigDecimal(150)),
    STANDARD("Standard", new BigDecimal(100));

    private final String nazwa;
    private final BigDecimal cena;

    TypKarnetu(String nazwa, BigDecimal cena){
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public String getNazwa(){
        return nazwa;
    }
    public BigDecimal getCena(){
        return cena;
    }

    @Override
    public String toString(){
        return nazwa + " (" + cena + "zł)";
    }
}

