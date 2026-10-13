package com.example.mas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class KlientController {
    private ZajeciaGrupowe wybraneZajecia;

    private final String extensionFile = "extension_file.ser";

    @FXML
    private TextField pesel;
    @FXML
    private TextField imie;
    @FXML
    private TextField nazwisko;
    @FXML
    private TextField dzien;
    @FXML
    private TextField miesiac;
    @FXML
    private TextField rok;
    @FXML
    private TextField email;
    @FXML
    private TextField nrtel;
    @FXML
    private Button dodajKlienta;
    @FXML
    private Button anuluj;

    @FXML
    public void initialize() {
        ustawOgraniczenie(pesel, 11);
        ustawOgraniczenie(nrtel, 9);
        ustawOgraniczenie(dzien, 2);
        ustawOgraniczenie(miesiac ,2);
        ustawOgraniczenie(rok, 4);

        anuluj.setOnAction(event -> wrocDoMenu());

        dodajKlienta.setOnAction(event -> zapiszKlienta());

    }

    private void ustawOgraniczenie(TextField field, int maxLength) {
        field.textProperty().addListener((observable, oldVal, newVal) -> {
            // Tylko cyfry
            if (!newVal.matches("\\d*")) {
                field.setText(newVal.replaceAll("[^\\d]", ""));
            }
            // Limit znaków
            if (field.getText().length() > maxLength) {
                field.setText(field.getText().substring(0, maxLength));
            }
        });
    }

    private void zapiszKlienta() {
        String p = pesel.getText();
        String i = imie.getText();
        String n = nazwisko.getText();
        String e = email.getText();
        String t = nrtel.getText();

        String dStr = dzien.getText();
        String mStr = miesiac.getText();
        String rStr = rok.getText();

        if (p.length() != 11) {
            pokazBlad("Błąd danych", "PESEL musi składać się z 11 cyfr.");
            return;
        }
        if (t.length() != 9) {
            pokazBlad("Błąd danych", "Numer telefonu musi składać się z 9 cyfr.");
            return;
        }
        if (i.isEmpty() || n.isEmpty()) {
            pokazBlad("Błąd danych", "Podaj imię i nazwisko.");
            return;
        }
        if (dStr.isEmpty() || mStr.isEmpty() || rStr.isEmpty()) {
            pokazBlad("Błąd danych", "Uzupełnij datę urodzenia.");
            return;
        }

        try {
            int d = Integer.parseInt(dStr);
            int m = Integer.parseInt(mStr);
            int y = Integer.parseInt(rStr);

            if (y < 1900 || y > java.time.LocalDate.now().getYear()) {
                pokazBlad("Błąd daty", "Podana data urodzenia jest nieprawidłowa.");
                return;
            }
            LocalDate dataUrodzenia = LocalDate.of(y, m, d);

            Klient nowy = new Klient(p, i, n, dataUrodzenia, e, t);

            System.out.println("Dodano klienta: " + nowy);

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(extensionFile));

            nowy.writeExtents(out);

            wrocDoMenu();

        } catch (NumberFormatException ex) {
            pokazBlad("Błąd formatu", "Data musi składać się tylko z cyfr.");
        } catch (java.time.DateTimeException ex){
            pokazBlad("Błąd daty", "Proszę podać odpowiednią datę");

        } catch (Exception ex) {
            ex.printStackTrace();
            pokazBlad("Błąd zapisu", "Nie udało się zapisać klienta: " + ex.getMessage());
        }

    }
    private void wrocDoMenu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/mas/start-window.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) anuluj.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMinHeight(400);
            stage.setMinWidth(500);

            stage.setTitle("System Rezerwacji");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pokazBlad(String tytul, String tresc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(tytul);
        alert.setHeaderText(null);
        alert.setContentText(tresc);
        alert.showAndWait();
    }
    public void setWybraneZajecia(ZajeciaGrupowe z){
        this.wybraneZajecia = z;
    }
}
