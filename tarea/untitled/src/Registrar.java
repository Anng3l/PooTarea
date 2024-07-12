import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registrar extends JFrame{
    private JPanel panel1;
    private JButton enviarButton;
    private JButton volverButton;
    private JTextField nombreTextField;
    private JTextField apellidoTextField;
    private JTextField edadTextField;

    public Registrar() {
        super ("Registro de usuarios");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300,300);

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreTextField.getText();
                String apellido = apellidoTextField.getText();
                String edad = edadTextField.getText();
                Integer age = Integer.parseInt(edad);

                try {
                    Enviar(nombre, apellido, age);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }





    public void Enviar(String nom, String ape, Integer age) throws SQLException
    {
        Connection connection = login();
        String sql = "INSERT INTO estudiantes (nombre, apellido, edad) VALUES (?, ?, ?);";

        PreparedStatement prst = connection.prepareStatement(sql);

        prst.setString(1, nom);
        prst.setString(2, ape);
        prst.setInt(3, age);

        int x = prst.executeUpdate();

        if (x > 0)
        {
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
        }

        prst.close();
        connection.close();
    }

    public Connection login() throws SQLException
    {
        String url = "jdbc:mysql://127.0.0.1:3306/poo";
        String user = "root";
        String password = "vamossobreruedasdefuegoAa@_";


        return DriverManager.getConnection(url, user, password);
    }
}
