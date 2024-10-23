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

    public void calcularPromedioNotas() throws SQLException {
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirdatosEst = "SELECT Estudiante.id_estudiante, nombre, apellido , AVG(calificacion) AS promedio FROM Estudiante " +
                    "LEFT JOIN Estudiante_Asignatura ON Estudiante.id_estudiante = Estudiante_Asignatura.id_estudiante " +
                    "group by Estudiante.id_estudiante,Estudiante.nombre, Estudiante.apellido " +
                    "order by Estudiante.id_estudiante";

            PreparedStatement consultaEst = conexion.prepareStatement(recibirdatosEst);

            ResultSet resultadosEst = consultaEst.executeQuery();

            while (resultadosEst.next()) {
                System.out.println(resultadosEst.getString("nombre")+" "+ resultadosEst.getString("apellido")+" "+ Math.round(Float.parseFloat(resultadosEst.getString("promedio"))));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<String>> estudiantesQuintoGrado(){
        List<List<String>> estudiantesQuinto = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirDatosEst = "SELECT nombre, apellido, nombre_casa FROM Estudiante " +
                    "INNER JOIN Casa ON Estudiante.id_casa = Casa.id_casa " +
                    "where año_curso = 5 " +
                    "Group by Casa.id_casa, nombre, apellido, Estudiante.id_casa " +
                    "Order by Estudiante.id_casa";

            PreparedStatement consulta = conexion.prepareStatement(recibirDatosEst);


            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                estudiantesQuinto.add(List.of(resultados.getString("nombre"), resultados.getString("apellido"), resultados.getString("nombre_casa")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estudiantesQuinto;
    }

    public void estudiantesMejoresCalificaciones(String asignatura) throws SQLException {
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirdatosEst = "SELECT nombre, apellido, calificacion FROM Estudiante " +
                    "INNER JOIN Estudiante_Asignatura ON Estudiante.id_estudiante = Estudiante_Asignatura.id_estudiante " +
                    "INNER JOIN Asignatura ON Estudiante_Asignatura.id_asignatura = Asignatura.id_asignatura " +
                    "where Asignatura.nombre_asignatura = ? " +
                    "order by calificacion desc " +
                    "limit 3";

            PreparedStatement consultaEst = conexion.prepareStatement(recibirdatosEst);
            consultaEst.setString(1, asignatura);

            ResultSet resultadosEst = consultaEst.executeQuery();

            while (resultadosEst.next()) {
                System.out.println(resultadosEst.getString("nombre")+" "+ resultadosEst.getString("apellido")+" "+ resultadosEst.getString("calificacion")+" "+ asignatura);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void promedioCalificacionesCasa(String asignatura){
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirDatos="SELECT AVG(calificacion) AS promedio, nombre_casa FROM Estudiante " +
                    "INNER JOIN Estudiante_Asignatura ON Estudiante.id_estudiante = Estudiante_Asignatura.id_estudiante " +
                    "INNER JOIN Asignatura ON Estudiante_Asignatura.id_asignatura = Asignatura.id_asignatura " +
                    "INNER JOIN Casa ON Estudiante.id_casa = Casa.id_casa " +
                    "where Asignatura.nombre_asignatura = ? " +
                    "GROUP BY Casa.nombre_casa, Casa.id_casa " +
                    "order by Casa.nombre_casa";

            PreparedStatement consulta = conexion.prepareStatement(recibirDatos);
            consulta.setString(1, asignatura);

            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                System.out.println(resultados.getString("nombre_casa")+" "+ Math.round(Float.parseFloat(resultados.getString("promedio"))));
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
