import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pasatiempos {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    public JPanel aplicacion;
    private JLabel pasatiempos;
    private JLabel descPas;
    private JLabel nombApe;
    private JButton InsertarDatos;

    public Pasatiempos() {
        InsertarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre= textField1.getText();
                String pasatiempo=textField2.getText();
                String descPas= textField3.getText();
                ingresoDatos(nombre,pasatiempo,descPas);
            }
        });
    }

    public static void ingresoDatos(String nombre, String pasatiempo, String descPas) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10686342",
                "sql10686342", "FZI2EyHqCS")) {
            String sql = "INSERT INTO pasatiempos(nombre,pasatiempos, descripcion)values(?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, pasatiempo);
                preparedStatement.setString(3, descPas);
                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Inserción exitosa");
                } else {
                    System.out.println("Inserción fallida");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
