package org.example;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        JFrame inicio = new JFrame();
        inicio.setContentPane(new Inicio_sesion().Inicio_sesion2);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio.setSize(800,640);
        inicio.setVisible(true);
        inicio.pack();

    }
}