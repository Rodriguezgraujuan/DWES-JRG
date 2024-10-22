import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BBDDService {
    static String urlConexion = "jdbc:postgresql://dwes-jrg.c87fx1s9niuj.us-east-1.rds.amazonaws.com:5432/hogwatsSQL";
    static String usuario = "postgres";
    static String password = "12345687";

    public List<List<String>> recibirEstudiantesCasa() {
        List<List<String>> estudianteCasas = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirDatosEst = "SELECT nombre, apellido, nombre_casa FROM Estudiante " +
                    "INNER JOIN Casa ON Estudiante.id_casa = Casa.id_casa ";

            PreparedStatement consulta = conexion.prepareStatement(recibirDatosEst);


            ResultSet resultados = consulta.executeQuery();


            while (resultados.next()) {
                estudianteCasas.add(List.of(resultados.getString("nombre"), resultados.getString("apellido"), resultados.getString("nombre_casa")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estudianteCasas;
    }

    public List<List<String>> estudiantesMascotas() {
        List<List<String>> estudianteCasas = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirDatosEst = "SELECT nombre, apellido, nombre_mascota FROM Estudiante " +
                    "Left JOIN Mascota ON Estudiante.id_estudiante = Mascota.id_estudiante ";

            PreparedStatement consulta = conexion.prepareStatement(recibirDatosEst);


            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                String nombreMascota = resultados.getString("nombre_mascota");
                if (nombreMascota == null) {
                    nombreMascota = "NULL";
                }
                estudianteCasas.add(List.of(resultados.getString("nombre"), resultados.getString("apellido"), nombreMascota));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estudianteCasas;
    }

    public List<List<String>> estudiantesConMascotas() {
        List<List<String>> estudianteCasas = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirDatosEst = "SELECT nombre, apellido, nombre_mascota FROM Estudiante " +
                    "INNER JOIN Mascota ON Estudiante.id_estudiante = Mascota.id_estudiante ";

            PreparedStatement consulta = conexion.prepareStatement(recibirDatosEst);


            ResultSet resultadosEst = consulta.executeQuery();


            while (resultadosEst.next()) {
                estudianteCasas.add(List.of(resultadosEst.getString("nombre"), resultadosEst.getString("apellido"), resultadosEst.getString("nombre_mascota")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estudianteCasas;
    }

    public void estudiantesSinMascotas() throws SQLException {
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirdatosEst = "SELECT nombre, apellido, nombre_mascota FROM Estudiante " +
                    "LEFT JOIN Mascota ON Estudiante.id_estudiante = Mascota.id_estudiante "
                    + "WHERE nombre_mascota IS NULL";

            PreparedStatement consultaEst = conexion.prepareStatement(recibirdatosEst);


            ResultSet resultadosEst = consultaEst.executeQuery();


            while (resultadosEst.next()) {
                String nombreMascota = resultadosEst.getString("nombre_mascota");
                if (nombreMascota == null) {
                    nombreMascota = "";
                }
                System.out.println(resultadosEst.getString("nombre")+" "+ resultadosEst.getString("apellido")+" "+ nombreMascota);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
