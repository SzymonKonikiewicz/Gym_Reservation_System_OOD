package com.example.mas;

public enum Asocjacja {

    KLIENT_REZERWACJA("Klient -> Rezerwacja"),
    REZERWACJA_KLIENT("Rezerwacja -> Klient"),

    REZERWACJA_ZAJECIAGRUPOWE("Rezerwacja -> ZajeciaGrupowe"),
    ZAJECIAGRUPOWE_REZERWACJA("ZajeciaGrupowe -> Rezerwacja"),

    KLIENT_KARNET("Klient -> Karnet"),
    KARNET_KLIENT("Karnet -> Klient"),

    KARNET_PLATNOSC("Karnet -> Platnosc"),
    PLATNOSC_KARNET("Platnosc -> Karnet"),

    KLIENT_PLATNOSC("Klient -> Platnosc"),
    PLATNOSC_KLIENT("Platnosc -> Klient"),

    ZAJECIAGRUPOWE_TRENER("ZajeciaGrupowe -> Trener"),
    TRENER_ZAJECIAGRUPOWE("Trener -> ZajeciaGrupowe"),

    KLIENT_WEJSCIENASILOWNIE("Klient -> WejscieNaSilownie"),
    WEJSCIENASILOWNIE_KLIENT("WejscieNaSilownie -> Klient"),

    ZAJECIAGRUPOWE_SALA("ZajeciaGrupowe -> Sala"),
    SALA_ZAJECIAGRUPOWE("Sala -> ZajeciaGrupowe");


    private final String nazwa;

    Asocjacja(String nazwa) {
        this.nazwa = nazwa;
    }
    public String getNazwa() {
        return nazwa;
    }

}
