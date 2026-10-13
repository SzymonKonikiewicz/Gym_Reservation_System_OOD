package com.example.mas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class startController {
    @FXML
    private ListView<Klient> listaKlientow;
    @FXML
    private Button wybierzIstniejacego;
    @FXML
    private Button stworzNowego;
    @FXML
    private Button anuluj;
    @FXML
    private Button historiaZamowien;

    @FXML
    private Button usunKlienta;

    private ZajeciaGrupowe wybraneZajecia;

    @FXML
    public void initialize() {
        try {
            Iterable<Klient> klienci = ObjectPlus.getExtent(Klient.class);
            for (Klient k : klienci) {
                listaKlientow.getItems().add(k);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        wybierzIstniejacego.setOnAction(event -> obsluzWyborIstniejacego());
        stworzNowego.setOnAction(event -> przejdzDoTworzeniaNowego());
        anuluj.setOnAction(event -> {
            Stage stage = (Stage) anuluj.getScene().getWindow();
            stage.close();
        });
        historiaZamowien.setOnAction(event -> pokazHistorieKlienta());

        usunKlienta.setOnAction(event -> {
            Klient wybrany = listaKlientow.getSelectionModel().getSelectedItem();
            if (wybrany == null) return;

            try {
                ObjectPlusPlus[] rezerwacje = wybrany.getLinks(Asocjacja.KLIENT_REZERWACJA.getNazwa());

                for (ObjectPlusPlus obj : rezerwacje) {
                    Rezerwacja rez = (Rezerwacja) obj;

                    try {
                        ObjectPlusPlus[] powiazaneZajecia = rez.getLinks(Asocjacja.REZERWACJA_ZAJECIAGRUPOWE.getNazwa());

                        if (powiazaneZajecia.length > 0) {
                            ObjectPlusPlus zajecia = powiazaneZajecia[0];

                            rez.removeLink(Asocjacja.REZERWACJA_ZAJECIAGRUPOWE.getNazwa(), zajecia);
                            zajecia.removeLink(Asocjacja.ZAJECIAGRUPOWE_REZERWACJA.getNazwa(), rez);
                        }
                    } catch (Exception e) {

                    }

                    wybrany.removeLink(Asocjacja.KLIENT_REZERWACJA.getNazwa(), rez);
                    rez.removeLink(Asocjacja.REZERWACJA_KLIENT.getNazwa(), wybrany);

                    Iterable<Rezerwacja> ekstensjaRezerwacji = ObjectPlus.getExtent(Rezerwacja.class);
                    ((List<Rezerwacja>) ekstensjaRezerwacji).remove(rez);
                }
            } catch (Exception e) {

            }

            try {
                Iterable<Klient> ekstensjaKlientow = ObjectPlus.getExtent(Klient.class);
                ((List<Klient>) ekstensjaKlientow).remove(wybrany);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            listaKlientow.getItems().remove(wybrany);
            listaKlientow.refresh();
        });

    }

    public void setWybraneZajecia(ZajeciaGrupowe zajecia) {
        this.wybraneZajecia = zajecia;
    }

    private void obsluzWyborIstniejacego() {
        Klient wybranyKlient = listaKlientow.getSelectionModel().getSelectedItem();

        if (wybranyKlient == null) {
            pokazAlert("Błąd", "Nie wybrano żadnego klienta z listy");
            return;
        } else try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/mas/zajecia.fxml"));
            Parent root = fxmlLoader.load();

            zajeciaController controller = fxmlLoader.getController();
            controller.setWybranyKlient(wybranyKlient);

            Stage mainStage = (Stage) wybierzIstniejacego.getScene().getWindow();
            mainStage.setMinHeight(400);
            mainStage.setMinWidth(500);
            mainStage.setScene(new Scene(root));


        } catch (Exception e) {
            e.printStackTrace();
            pokazAlert("Błąd", "Nie udało się stworzyć rezerwacji.");
        }
    }

    private void przejdzDoTworzeniaNowego() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/mas/klient-info.fxml"));
            Parent root = loader.load();

            KlientController controller = loader.getController();
            controller.setWybraneZajecia(this.wybraneZajecia);

            Stage stage = (Stage) stworzNowego.getScene().getWindow();
            stage.setMinWidth(500);
            stage.setMinHeight(450);
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pokazAlert(String tytul, String tresc) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(tytul);
        alert.setHeaderText(null);
        alert.setContentText(tresc);
        alert.showAndWait();
    }

    private void pokazHistorieKlienta() {
        Klient wybranyKlient = listaKlientow.getSelectionModel().getSelectedItem();

        if (wybranyKlient == null) {
            pokazAlert("Błąd", "Nie wybrano żadnego klienta z listy");
            return;
        } else try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/mas/historia-zamowien.fxml"));
            Parent root = fxmlLoader.load();

            HistoriaZamowienController controller = fxmlLoader.getController();
            controller.setWybranyKlient(wybranyKlient);

            Stage mainStage = (Stage) historiaZamowien.getScene().getWindow();
            mainStage.setMinHeight(660);
            mainStage.setMinWidth(570);
            mainStage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
