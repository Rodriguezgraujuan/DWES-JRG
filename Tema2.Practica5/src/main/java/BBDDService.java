import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BBDDService {
    static String urlConexion = "jdbc:postgresql://dwes-jrg.c87fx1s9niuj.us-east-1.rds.amazonaws.com:5432/hogwatsSQL";
    static String usuario = "postgres";
    static String password = "12345687";

    public void consultaEstidiantesCasa() {
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirDatosEst = "SELECT nombre, apellido FROM Estudiante " +
                    "WHERE id_casa = ?";

            PreparedStatement consulta = conexion.prepareStatement(recibirDatosEst);

            consulta.setInt(1, 1);

            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                System.out.println("Nombre: " + resultados.getString("nombre") + " Apellido: " + resultados.getString("apellido"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Casa> recibirDatosCasa() {
        List<Casa> casas = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirDatos = "SELECT * FROM Casa";

            PreparedStatement consulta = conexion.prepareStatement(recibirDatos);
            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                Casa casa = new Casa();
                casa.setId_casa(resultados.getInt("id_casa"));
                casa.setNombre_casa(resultados.getString("nombre_casa"));
                casa.setFundador(resultados.getString("fundador"));
                casa.setJefe_Casa(resultados.getString("jefe_casa"));
                casa.setFantasma(resultados.getString("fantasma"));
                casas.add(casa);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return casas;
    }

    public List<Estudiante> recibirDatosEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirDatos = "SELECT * FROM Estudiante";

            PreparedStatement consulta = conexion.prepareStatement(recibirDatos);
            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId_estudiante(resultados.getInt("id_estudiante"));
                estudiante.setNombre(resultados.getString("nombre"));
                estudiante.setApellido(resultados.getString("apellido"));
                estudiante.setId_casa(resultados.getInt("id_casa"));
                estudiante.setAnyo_curso(resultados.getInt("año_curso"));
                estudiante.setFecha_nacimiento(resultados.getDate("fecha_nacimiento"));
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estudiantes;
    }
}