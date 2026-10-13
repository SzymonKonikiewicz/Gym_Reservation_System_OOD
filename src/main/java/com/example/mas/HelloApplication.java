package com.example.mas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HelloApplication extends Application {

    private final String extensionFile = "extension_file.ser";

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setMinHeight(400);
        stage.setMinWidth(500);
        stage.setTitle("System Rezerwacji");
        stage.setScene(scene);
        stage.show();

        /*
        try {
            ObjectPlus.showExtent(Rezerwacja.class);
            ObjectPlus.showExtent(Klient.class);

            Klient k = ((List<Klient>) ObjectPlus.getExtent(Klient.class)).get(0);
            k.showLinks(Asocjacja.KLIENT_REZERWACJA.getNazwa(), System.out);

        } catch(Exception e){

        }
        */

        /*
        try {
            Iterable<Rezerwacja> rezerwacje = ObjectPlus.getExtent(Rezerwacja.class);
            for(Rezerwacja r : rezerwacje){
                System.out.println(r);
            }

            Iterable<ZajeciaGrupowe> zg = ObjectPlus.getExtent(ZajeciaGrupowe.class);
            for(ZajeciaGrupowe zajecia : zg){
                zajecia.showLinks(Asocjacja.ZAJECIAGRUPOWE_REZERWACJA.getNazwa(), System.out);
            }

        } catch(Exception e){

        }
        */

    }

    @Override
    public void init() {
        try {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(extensionFile));
            ObjectPlus.readExtents(in);
            in.close();

            System.out.println("Wczytanie danych z pliku " + extensionFile);

        } catch (FileNotFoundException e) {
            System.out.println("Wczytanie danych startowych");
            stworzDaneStartowe();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Błąd odczytu.");
        }
    }

    @Override
    public void stop() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(extensionFile));
            ObjectPlus.writeExtents(out);
            out.close();
            System.out.println("Zapisano dane do pliku " + extensionFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stworzDaneStartowe() {
        try {
            Klient klient1 = new Klient("95021411223", "Tomasz", "Zieliński", LocalDate.of(1995,12,20), "tomek.z@gmail.com", "555666777");
            Klient klient2 = new Klient("98070133445", "Ewa", "Kamińska", LocalDate.of(2000,3,18), "ewa.kaminska@onet.pl", "666777888");
            Klient klient3 = new Klient("01122455667", "Michał", "Lewandowski", LocalDate.of(1999,9,26), "michal.lew@wp.pl", "777888999");
            Klient klient4 = new Klient("99090999887", "Julia", "Szymańska", LocalDate.of(1989,1,30), "julia.szym@gmail.com", "888999000");

            Trener trener1 = new Trener("88052012309", "Mariusz", "Pudzianowski", LocalDate.of(1980,4,12), "mariusz@silownia.pl", "987123987");
            Trener trener2 = new Trener("90071598765", "Anna", "Lewandowska", LocalDate.of(1983,3,15), "anna.fitness@silownia.pl", "123987123");
            Trener trener3 = new Trener("85120156789", "Sylwester", "Stallone", LocalDate.of(1989,12,30), "sly@boxing.com", "456123789");
            Trener trener4 = new Trener("92033034567", "Chuck", "Norris", LocalDate.of(1990,12,4), "chuck@kick.com", "789456123");

            ZajeciaGrupowe joga1 = new ZajeciaGrupowe("Joga", 15, LocalDateTime.of(2026,9,20,12,0,0));
            ZajeciaGrupowe joga2 = new ZajeciaGrupowe("Joga", 15, LocalDateTime.of(2026,9,20,14,0,0));
            ZajeciaGrupowe joga3 = new ZajeciaGrupowe("Joga", 12, LocalDateTime.of(2026,9,20,16,0,0));
            ZajeciaGrupowe joga4 = new ZajeciaGrupowe("Joga", 20, LocalDateTime.of(2026,9,20,18,0,0));

            ZajeciaGrupowe cross1 = new ZajeciaGrupowe("CrossFit", 20, LocalDateTime.of(2026,9,22,12,0,0));
            ZajeciaGrupowe cross2 = new ZajeciaGrupowe("CrossFit", 15, LocalDateTime.of(2026,9,22,14,0,0));
            ZajeciaGrupowe cross3 = new ZajeciaGrupowe("CrossFit", 20, LocalDateTime.of(2026,9,22,16,0,0));
            ZajeciaGrupowe cross4 = new ZajeciaGrupowe("CrossFit", 25, LocalDateTime.of(2026,9,22,18,0,0));

            ZajeciaGrupowe zumba1 = new ZajeciaGrupowe("Zumba", 30, LocalDateTime.of(2026,9,24,12,0,0));
            ZajeciaGrupowe zumba2 = new ZajeciaGrupowe("Zumba", 25, LocalDateTime.of(2026,9,24,14,0,0));
            ZajeciaGrupowe zumba3 = new ZajeciaGrupowe("Zumba", 20, LocalDateTime.of(2026,9,24,16,0,0));
            ZajeciaGrupowe zumba4 = new ZajeciaGrupowe("Zumba", 40, LocalDateTime.of(2026,9,24,18,0,0));

            ZajeciaGrupowe pilates1 = new ZajeciaGrupowe("Pilates", 12, LocalDateTime.of(2026,9,28,12,0,0));
            ZajeciaGrupowe pilates2 = new ZajeciaGrupowe("Pilates", 15, LocalDateTime.of(2026,9,28,14,0,0));
            ZajeciaGrupowe pilates3 = new ZajeciaGrupowe("Pilates", 10, LocalDateTime.of(2026,9,28,16,0,0));
            ZajeciaGrupowe pilates4 = new ZajeciaGrupowe("Pilates", 2, LocalDateTime.of(2026,9,28,18,0,0));

            trener3.addZajeciaGrupowe(zumba3);
            trener3.addZajeciaGrupowe(zumba1);
            trener3.addZajeciaGrupowe(zumba2);
            trener3.addZajeciaGrupowe(zumba4);

            trener1.addZajeciaGrupowe(joga1);
            trener1.addZajeciaGrupowe(joga2);
            trener1.addZajeciaGrupowe(joga3);
            trener1.addZajeciaGrupowe(joga4);

            trener2.addZajeciaGrupowe(cross1);
            trener2.addZajeciaGrupowe(cross2);
            trener2.addZajeciaGrupowe(cross3);
            trener2.addZajeciaGrupowe(cross4);

            trener4.addZajeciaGrupowe(pilates1);
            trener4.addZajeciaGrupowe(pilates2);
            trener4.addZajeciaGrupowe(pilates3);
            trener4.addZajeciaGrupowe(pilates4);

            Rezerwacja rezerwacja1 = new Rezerwacja(LocalDateTime.of(2026, 5, 21, 12, 12, 12));
            Rezerwacja rezerwacja2 = new Rezerwacja(LocalDateTime.of(2026, 6, 15, 14, 30, 0));
            Rezerwacja rezerwacja3 = new Rezerwacja(LocalDateTime.of(2026, 7, 10, 9, 0, 0));
            Rezerwacja rezerwacja4 = new Rezerwacja(LocalDateTime.of(2026, 8, 5, 18, 45, 30));
            Rezerwacja rezerwacja5 = new Rezerwacja(LocalDateTime.of(2026, 9, 1, 8, 15, 0));
            Rezerwacja rezerwacja6 = new Rezerwacja(LocalDateTime.of(2026, 10, 12, 16, 20, 0));
            Rezerwacja rezerwacja7 = new Rezerwacja(LocalDateTime.of(2026, 11, 20, 10, 50, 45));
            Rezerwacja rezerwacja8 = new Rezerwacja(LocalDateTime.of(2026, 12, 2, 11, 11, 11));
            Rezerwacja rezerwacja9 = new Rezerwacja(LocalDateTime.of(2027, 1, 14, 19, 0, 0));
            Rezerwacja rezerwacja10 = new Rezerwacja(LocalDateTime.of(2027, 2, 28, 20, 30, 0));
            Rezerwacja rezerwacja11 = new Rezerwacja(LocalDateTime.of(2027, 3, 5, 7, 0, 0));
            Rezerwacja rezerwacja12 = new Rezerwacja(LocalDateTime.of(2027, 3, 18, 15, 45, 0));
            Rezerwacja rezerwacja13 = new Rezerwacja(LocalDateTime.of(2027, 4, 2, 9, 30, 0));
            Rezerwacja rezerwacja14 = new Rezerwacja(LocalDateTime.of(2027, 4, 22, 17, 15, 30));

            klient1.addRezerwacja(rezerwacja1);
            rezerwacja1.addZajeciaGrupowe(joga1);
            klient1.addRezerwacja(rezerwacja2);
            rezerwacja2.addZajeciaGrupowe(joga2);
            klient1.addRezerwacja(rezerwacja3);
            rezerwacja3.addZajeciaGrupowe(joga3);
            klient1.addRezerwacja(rezerwacja4);
            rezerwacja4.addZajeciaGrupowe(joga4);
            klient1.addRezerwacja(rezerwacja5);
            rezerwacja5.addZajeciaGrupowe(cross1);
            klient1.addRezerwacja(rezerwacja6);
            rezerwacja6.addZajeciaGrupowe(cross2);
            klient1.addRezerwacja(rezerwacja7);
            rezerwacja7.addZajeciaGrupowe(cross3);

            klient2.addRezerwacja(rezerwacja8);
            rezerwacja8.addZajeciaGrupowe(cross4);
            klient2.addRezerwacja(rezerwacja9);
            rezerwacja9.addZajeciaGrupowe(zumba1);
            klient2.addRezerwacja(rezerwacja10);
            rezerwacja10.addZajeciaGrupowe(zumba2);
            klient2.addRezerwacja(rezerwacja11);
            rezerwacja11.addZajeciaGrupowe(zumba3);

            klient3.addRezerwacja(rezerwacja12);
            rezerwacja12.addZajeciaGrupowe(zumba4);
            klient3.addRezerwacja(rezerwacja13);
            rezerwacja13.addZajeciaGrupowe(pilates1);
            klient3.addRezerwacja(rezerwacja14);
            rezerwacja14.addZajeciaGrupowe(pilates2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
