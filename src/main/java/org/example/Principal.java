package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    private JTextArea textArea1;
    private JLabel biblio2;
    JPanel Principal3;

    List<Object[]> arreglo2 = new ArrayList<>();

    public Principal(int ID) {
        String URL = "jdbc:mysql://localhost:3306/poo_users";
        String bd_user = "root";
        String bd_pass = ""; //No se puede compartir la contraseña de un servidor local
        java.sql.Connection conexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, bd_user, bd_pass);
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM usuarios");
            int id;
            String descripcion, usr;
            while (resultado.next()) {
                id = resultado.getInt("id");
                usr = resultado.getString("username");
                descripcion = resultado.getString("biblio");
                Object[] fila = {id, usr, descripcion};
                arreglo2.add(fila);
            }
            resultado.close();
            statement.close();
            conexion.close();
            for (Object[] fila : arreglo2) {
                String desc = (String) fila[2];
                textArea1.setText(desc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
