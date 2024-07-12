import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ver extends JFrame{
    private JPanel panel1;
    private JTextField nombreTextField;
    private JButton buscarButton;
    private JButton volverButton;

    public Ver() {
        super("Visualización de datos");
        setContentPane(panel1);
        setSize(200,200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreTextField.getText();

                try {
                    Visualizacion(nombre);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
    }



    public void Visualizacion(String nombre) throws SQLException
    {
        Connection connection = login();
        String sql = "SELECT * FROM estudiantes WHERE nombre = (?)";

        PreparedStatement prst = connection.prepareStatement(sql);

        prst.setString(1, nombre);

        ResultSet rs = prst.executeQuery();

        if (rs.next())
        {
            String id = rs.getString("id");
            String nom = rs.getString("nombre");
            String ape = rs.getString("apellido");
            Integer edad = rs.getInt("edad");

            JOptionPane.showMessageDialog(null, "id: " + id + "\nnombre: " + nom + "\nApellido: " + ape + "\nEdad: " + edad);
        }

        rs.close();
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
