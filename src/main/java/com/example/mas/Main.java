package com.example.mas;

import javafx.application.Application;

import java.sql.Time;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args){
        final String extensionFile = "extension_file.ser";

        zajeciaController cont = new zajeciaController();
        Application.launch(HelloApplication.class, args);

    }
}