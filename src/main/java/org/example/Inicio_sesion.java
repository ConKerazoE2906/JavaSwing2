package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Inicio_sesion {
    private JPasswordField password;
    private JButton acceder;
     JPanel Inicio_sesion2;
    private JTextField user;

    List<Object[]> arreglo1 = new ArrayList<>();

    public Inicio_sesion() {
                    acceder.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            arreglo1.clear();

                            String URL="jdbc:mysql://localhost:3306/poo_users";
                            String bd_user="root";
                            String bd_pass=""; //No se puede compartir la contraseña de un servidor local
                            java.sql.Connection conexion= null;
                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                conexion = DriverManager.getConnection(URL, bd_user, bd_pass);
                                Statement statement = conexion.createStatement();
                                ResultSet resultado = statement.executeQuery("SELECT * FROM usuarios");
                                int id;
                                String Nombre, Contras;
                                while (resultado.next())
                                {
                                    id=resultado.getInt("id");
                                    Nombre=resultado.getString("username");
                                    Contras=resultado.getString("password");
                                    Object[] fila = {id, Nombre, Contras};
                                    arreglo1.add(fila);
                                }
                                resultado.close();
                                statement.close();
                                conexion.close();
                                boolean encontrado = false;
                                int id_usr=0;
                                for (Object[] fila : arreglo1) {
                                    String nombre = (String) fila[1];
                                    String contrasena = (String) fila[2];
                                    if (nombre.equals(user.getText()) && contrasena.equals(new String(password.getPassword()))) {
                                        id_usr = (int) fila[0];
                                        encontrado = true;
                                        break;
                                    }
                                }
                                if (!encontrado) {
                                    JOptionPane.showMessageDialog(Inicio_sesion2, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                else {
                                    JFrame inicio = new JFrame();
                                    inicio.setContentPane(new Principal(id_usr).Principal3);
                                    inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    inicio.setSize(800,640);
                                    inicio.setVisible(true);
                                    inicio.pack();
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

            }
    }

