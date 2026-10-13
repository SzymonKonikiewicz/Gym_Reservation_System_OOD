package com.example.mas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.time.*;

public class zajeciaController {

    private Klient wybranyKlient;

    @FXML
    private ComboBox<String> mojComboBox;
    @FXML
    private Button anulujButton;
    @FXML
    private ListView<ZajeciaGrupowe> listaZajec;
    @FXML
    private Label szczegoly;
    @FXML
    private Button zarezerwujButton;

    @FXML
    public void initialize() throws ClassNotFoundException {
        mojComboBox.getItems().clear();
        mojComboBox.getItems().add("Wybierz Zajecia");

        Set<String> nazwy = new HashSet<>();

        Iterable<ZajeciaGrupowe> zajeciag = ObjectPlus.getExtent(ZajeciaGrupowe.class);
        for (ZajeciaGrupowe zajecia : zajeciag) {
            nazwy.add(zajecia.getNazwa());
        }

        mojComboBox.getItems().addAll(nazwy);
        mojComboBox.setOnAction(event -> {
            String wybranaKategoria = mojComboBox.getValue();
            try {
                odswiezListe(wybranaKategoria);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        listaZajec.getItems().clear();
        listaZajec.getSelectionModel().selectedItemProperty().addListener((observable, staraWartosc, nowaWartosc) -> {

            if (nowaWartosc != null) {
                try {
                    if (nowaWartosc.getLinks(Asocjacja.ZAJECIAGRUPOWE_TRENER.getNazwa())[0] == null) {
                        szczegoly.setText("Te zajęcia nie mają jeszcze przypisanego trenera.");
                    } else {
                        szczegoly.setText(nowaWartosc.getLinks(Asocjacja.ZAJECIAGRUPOWE_TRENER.getNazwa())[0].toString());
                    }
                } catch (Exception e) {
                    szczegoly.setText("Te zajęcia nie mają jeszcze przypisanego trenera.");
                    //throw new RuntimeException(e);
                }
            }
        });
        mojComboBox.getSelectionModel().selectFirst();

        anulujButton.setOnAction(actionEvent -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/mas/start-window.fxml"));
                Parent root = fxmlLoader.load();

                Stage mainStage = (Stage) anulujButton.getScene().getWindow();
                mainStage.setMinHeight(400);
                mainStage.setMinWidth(500);
                mainStage.setScene(new Scene(root));
            }
            catch (Exception e){
                e.printStackTrace();
            }

        });

        zarezerwujButton.setOnAction(actionEvent -> {
            try {
                ZajeciaGrupowe wybraneZajecia = listaZajec.getSelectionModel().getSelectedItem();
                if (wybraneZajecia == null) {
                    System.out.println("Nic nie wybrano");
                    return;
                } else if(wybraneZajecia.getMaxUczestnikow() - wybraneZajecia.dostepnoscMiejsc() <= 0){
                    System.out.println("Brak Wolnych Miejsc");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Brak wolnych miejsc");
                    alert.setHeaderText("Brak wolnych miejsc.");
                    alert.setContentText("Czy chcesz dopisać się do listy rezerwowej?");

                    ButtonType zapisz = new ButtonType("Tak");
                    ButtonType anuluj = new ButtonType("Nie");
                    alert.getButtonTypes().setAll(zapisz, anuluj);

                    Optional<ButtonType> decyzja = alert.showAndWait();

                    if (decyzja.isPresent() && decyzja.get() == zapisz){

                        Rezerwacja rezerwacja = new Rezerwacja(LocalDateTime.now(), StatusRezerwacji.REZERWOWA);

                        wybranyKlient.addRezerwacja(rezerwacja);
                        wybraneZajecia.addRezerwacja(rezerwacja);

                    }

                    return;
                }

                Rezerwacja rezerwacja = new Rezerwacja(LocalDateTime.now());
                wybranyKlient.addRezerwacja(rezerwacja);
                wybraneZajecia.addRezerwacja(rezerwacja);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/mas/start-window.fxml"));
                Parent root = fxmlLoader.load();

                Stage mainStage = (Stage) zarezerwujButton.getScene().getWindow();
                mainStage.setMinHeight(400);
                mainStage.setMinWidth(500);
                mainStage.setScene(new Scene(root));

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Nie udało się otworzyć okna: " + e.getMessage());
            }
        });
    }

    private void odswiezListe(String filtr) throws ClassNotFoundException {

        listaZajec.getItems().clear();

        Iterable<ZajeciaGrupowe> wszystkie = ObjectPlus.getExtent(ZajeciaGrupowe.class);
        for (ZajeciaGrupowe z : wszystkie) {

            if (filtr == null || filtr.equals("Wszystkie")) {
                listaZajec.getItems().add(z);
            }
            else if (z.getNazwa().equals(filtr)) {
                listaZajec.getItems().add(z);
            }
        }
    }
    public void setWybranyKlient(Klient klient){
        this.wybranyKlient = klient;
    }
}
