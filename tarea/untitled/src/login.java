import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JFrame{
    private JPanel panel1;
    private JTextField userJTextField;
    private JTextField passwordJTextField;
    private JButton iniciarButton;

    public login() {
        super("Inicio de sesión");
        setContentPane(panel1);
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        //Action listeners
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean resultado;
                String c = userJTextField.getText();
                String p = passwordJTextField.getText();

                try {
                    resultado = verifiacion(c, p);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                if (resultado == true)
                {
                    JOptionPane.showMessageDialog(null, "Inició sesión exitosamente");
                    dispose();
                    Menu menu = new Menu();
                    menu.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Parámetros incorrectos");
                }


            }
        });
    }








    public Boolean verifiacion(String email, String contra) throws SQLException {
        String correo = "";
        String password = "";
        Boolean xyz;

        Connection connection = login();
        String sql = "SELECT * FROM login WHERE correo = ?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "angel@gmail.com");

        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {
            correo = rs.getString("correo");
            password = rs.getString("password");
        }

        if (email.equals(correo) && contra.equals(password))
        {
            xyz = true;
        }
        else
        {
            xyz = false;
        }


        return xyz;
    }




    public Connection login() throws SQLException
    {
        String url = "jdbc:mysql://127.0.0.1:3306/poo";
        String user = "root";
        String password = "vamossobreruedasdefuegoAa@_";


        return DriverManager.getConnection(url, user, password);
    }
}
