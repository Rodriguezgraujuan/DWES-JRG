import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BBDDService {
    static String urlConexion = "jdbc:postgresql://dwes-jrg.c87fx1s9niuj.us-east-1.rds.amazonaws.com:5432/hogwatsSQL";
    static String usuario = "postgres";
    static String password = "12345687";

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

    public List<Asignatura> recibirAsignaturas() {
        List<Asignatura> asignaturas = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirAsignatura = "SELECT * FROM Asignatura";

            PreparedStatement consulta = conexion.prepareStatement(recibirAsignatura);
            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                Asignatura asignatura = new Asignatura();
                asignatura.setId_asignatura(resultados.getInt("id_asignatura"));
                asignatura.setNombre_asignatura(resultados.getString("nombre_asignatura"));
                asignatura.setAula(resultados.getString("aula"));
                asignatura.setObligatoria(resultados.getBoolean("obligatoria"));
                asignaturas.add(asignatura);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return asignaturas;
    }

    public List<Profesor> recibirProfesores() {
        List<Profesor> profesores = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirProfesor = "SELECT * FROM Profesor";

            PreparedStatement consulta = conexion.prepareStatement(recibirProfesor);
            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                Profesor profesor = new Profesor();
                profesor.setId_profesor(resultados.getInt("id_profesor"));
                profesor.setNombre(resultados.getString("nombre"));
                profesor.setApellido(resultados.getString("apellido"));
                profesor.setId_asignatura(resultados.getInt("id_asignatura"));
                profesor.setFecha_inicio(resultados.getDate("fecha_inicio"));
                profesores.add(profesor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profesores;
    }

    public List<Mascota> recibirMascotas() {
        List<Mascota> mascotas = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirMascota = "SELECT * FROM Mascota";

            PreparedStatement consulta = conexion.prepareStatement(recibirMascota);
            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                Mascota mascota = new Mascota();
                mascota.setId_mascota(resultados.getInt("id_mascota"));
                mascota.setNombre_mascota(resultados.getString("nombre_mascota"));
                mascota.setEspecie(resultados.getString("especie"));
                mascota.setId_estudiante(resultados.getInt("id_estudiante"));
                mascotas.add(mascota);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mascotas;
    }

    public List<Estudiante_Asignatura> recibirEstudianteAsignatura() {
        List<Estudiante_Asignatura> estudiante_asignatura = new ArrayList<>();
        try (Connection conexion = DriverManager.getConnection(urlConexion, usuario, password)) {
            conexion.setAutoCommit(false);

            String recibirEstudianteAsignatura = "SELECT * FROM Estudiante_Asignatura";

            PreparedStatement consulta = conexion.prepareStatement(recibirEstudianteAsignatura);
            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                Estudiante_Asignatura estudiante_asignatura1 = new Estudiante_Asignatura();
                estudiante_asignatura1.setId_estudiante(resultados.getInt("id_estudiante"));
                estudiante_asignatura1.setId_asignatura(resultados.getInt("id_asignatura"));
                estudiante_asignatura1.setCalificacion(resultados.getInt("calificacion"));
                estudiante_asignatura.add(estudiante_asignatura1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return estudiante_asignatura;
    }


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

}