package com.example.mas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class HistoriaZamowienController {

    private Klient wybranyKlient;

    @FXML
    private ListView<Rezerwacja> listaRezerwacji;
    @FXML
    private Button zmienStatusRezerwacji;
    @FXML
    private Button usunRezerwacje;
    @FXML
    private Button anuluj;
    @FXML
    private Button dodajRezerwacje;
    @FXML
    private Label opisRezerwacji;
    @FXML
    private Label opisTrenera;
    @FXML
    private Label peselKlienta;
    @FXML
    private Label imieKlienta;
    @FXML
    private Label nazwKlienta;

    @FXML
    public void initialize() {

        anuluj.setOnAction(event -> wrocDoMenu());
        usunRezerwacje.setOnAction(event -> {
            Rezerwacja rezerwacjaDoUsuniecia = listaRezerwacji.getSelectionModel().getSelectedItem();

            if (rezerwacjaDoUsuniecia != null) {
                try {
                    try {
                        ObjectPlusPlus[] powiazaneZajecia = rezerwacjaDoUsuniecia.getLinks(Asocjacja.REZERWACJA_ZAJECIAGRUPOWE.getNazwa());
                        if (powiazaneZajecia.length > 0) {
                            ObjectPlusPlus zajecia = powiazaneZajecia[0];

                            rezerwacjaDoUsuniecia.removeLink(Asocjacja.REZERWACJA_ZAJECIAGRUPOWE.getNazwa(), zajecia);
                            zajecia.removeLink(Asocjacja.ZAJECIAGRUPOWE_REZERWACJA.getNazwa(), rezerwacjaDoUsuniecia);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        wybranyKlient.removeLink(Asocjacja.KLIENT_REZERWACJA.getNazwa(), rezerwacjaDoUsuniecia);
                        rezerwacjaDoUsuniecia.removeLink(Asocjacja.REZERWACJA_KLIENT.getNazwa(), wybranyKlient);
                    } catch (Exception e) {

                    }

                    Iterable<Rezerwacja> ekstensjaRezerwacji = ObjectPlus.getExtent(Rezerwacja.class);
                    ((List<Rezerwacja>) ekstensjaRezerwacji).remove(rezerwacjaDoUsuniecia);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                listaRezerwacji.getItems().remove(rezerwacjaDoUsuniecia);
                listaRezerwacji.refresh();
                try {
                    odswiezListe(wybranyKlient);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        listaRezerwacji.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null){
                try {
                    opisRezerwacji.setText(newValue.getLinks(Asocjacja.REZERWACJA_ZAJECIAGRUPOWE.getNazwa())[0].toString());
                    opisTrenera.setText(newValue.getLinks(Asocjacja.REZERWACJA_ZAJECIAGRUPOWE.getNazwa())[0].getLinks(Asocjacja.ZAJECIAGRUPOWE_TRENER.getNazwa())[0].toString());
                } catch(Exception e){

                }
            }
        }));

        zmienStatusRezerwacji.setOnAction(event -> zmienStatusRezerwacji());
        dodajRezerwacje.setOnAction(event -> dodajRezerwacje());

    }

    public void setWybranyKlient(Klient klient){
        this.wybranyKlient = klient;
        peselKlienta.setText(klient.getPesel());
        imieKlienta.setText(klient.getImie());
        nazwKlienta.setText(klient.getNazwisko());
        try {
            odswiezListe(wybranyKlient);
        } catch (Exception e){

        }
    }

    private void zmienStatusRezerwacji(){

        if(listaRezerwacji.getSelectionModel().getSelectedItem() != null) {
            ChoiceDialog<StatusRezerwacji> statusRezerwacji = new ChoiceDialog<>(
                    StatusRezerwacji.OCZEKUJACA, StatusRezerwacji.values()
            );
            statusRezerwacji.setTitle("Zmiana statusu rezerwacji");
            statusRezerwacji.setHeaderText("Wybierz nowy status rezerwacji:");

            Button cancel = (Button) statusRezerwacji.getDialogPane().lookupButton(ButtonType.CANCEL);
            cancel.setText("Anuluj");

            Optional<StatusRezerwacji> nowyStatus = statusRezerwacji.showAndWait();

            nowyStatus.ifPresent(wybor -> {
                listaRezerwacji.getSelectionModel().getSelectedItem().zmienStatus(wybor);
                listaRezerwacji.refresh();
            });
        }
    }

    private void odswiezListe(Klient k) {
        listaRezerwacji.getItems().clear();
        if (k == null) {
            return;
        }
        try {
            ObjectPlusPlus[] rezerwacje = k.getLinks(Asocjacja.KLIENT_REZERWACJA.getNazwa());
            for (ObjectPlusPlus obj : rezerwacje) {
                Rezerwacja r = (Rezerwacja) obj;
                listaRezerwacji.getItems().add(r);
            }
            listaRezerwacji.getItems().sort(Comparator.comparing(Rezerwacja::getData));

            if (rezerwacje.length == 0){
                opisRezerwacji.setText("Ten klient nie ma żadnych rezerwacji.");
                opisTrenera.setText("");
            }
        } catch (Exception e){
            opisRezerwacji.setText("Ten klient nie ma żadnych rezerwacji.");
            opisTrenera.setText("");
            //e.printStackTrace();
        }
    }

    private void dodajRezerwacje() {

            try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/mas/zajecia.fxml"));
            Parent root = fxmlLoader.load();

            zajeciaController controller = fxmlLoader.getController();
            controller.setWybranyKlient(wybranyKlient);

            Stage mainStage = (Stage) dodajRezerwacje.getScene().getWindow();
            mainStage.setMinHeight(400);
            mainStage.setMinWidth(500);
            mainStage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
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

}
