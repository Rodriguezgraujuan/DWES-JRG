import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AppService {
    static Scanner in = new Scanner(System.in);

    public void listadoAsignaturasObligatorias (List<Asignatura> asignaturas){
        asignaturas.stream().filter(Asignatura::isObligatoria).forEach(System.out::println);
    }

    public void mostrarMascotas(List<Mascota> mascotas, int id_estudiante){
        mascotas.stream().filter(mascota -> mascota.getId_estudiante() == id_estudiante).forEach(System.out::println);
    }

    public int extraerIdEstudiante(String nombre, List<Estudiante> estudiantes){
        return estudiantes.stream().filter(estudiante -> estudiante.getNombre().equals(nombre)).findFirst().get().getId_estudiante();
    }

    public void mostrarEstudiantesSinMascotas(List<Estudiante> estudiantes, List<Mascota> mascotas){
        estudiantes.stream().filter(estudiante -> !mascotas.stream().anyMatch(mascota -> mascota.getId_estudiante() == estudiante.getId_estudiante())).forEach(System.out::println);
    }

    public void mostrarMediaEstudiante(List<Estudiante> estudiantes, List<Estudiante_Asignatura> estudianteAsignaturas){
        System.out.println("Introduce el nombre");
        String nombre = in.nextLine();
        System.out.println("Introduce el apellido");
        String apellido = in.nextLine();
        int id_est= estudiantes.stream().filter(est -> est.getNombre().equals(nombre) && est.getApellido().equals(apellido)).findFirst().map(Estudiante::getId_estudiante).orElse(0);
        if (id_est != 0) {
            List<Double> notas = new ArrayList<>();
            for (Estudiante_Asignatura ea : estudianteAsignaturas) {
                if (ea.getId_estudiante() == id_est) {
                    notas.add(ea.getCalificacion());
                }
            }
            System.out.println(Math.round(notas.stream().mapToDouble(Double::doubleValue).average().getAsDouble()));
        }else {
            System.out.println("Estudiante no encontrado");
        }
    }

    public void estudiantesPorCasa(List<Casa> casas, List<Estudiante> estudiantes){
        casas.forEach(casa -> {
            System.out.println(casa.getNombre_casa() + ":");
            estudiantes.stream().filter(est -> est.getId_casa() == casa.getId_casa()).forEach(System.out::println);
        });
    }

    public void numeroEstudiantesPorCasa(List<Estudiante> estudiantes, List<Casa> casas){
        casas.forEach(casa -> {
            System.out.println(casa.getNombre_casa() + ": " + estudiantes.stream().filter(est -> est.getId_casa() == casa.getId_casa()).count());
        });
    }

    public void estudiantesMatriculaEspecifica(List<Estudiante> estudiantes, List<Asignatura> asignaturas, List<Estudiante_Asignatura> estudianteAsignaturas){
        System.out.println("Introduce la asignatura");
        String asignatura = in.nextLine();
        int id_asign= asignaturas.stream().filter(asig -> asig.getNombre_asignatura().equals(asignatura)).findFirst().get().getId_asignatura();
        List<Integer> id_Estudiantes = new ArrayList<>();

        for (Estudiante_Asignatura ea: estudianteAsignaturas){
            if (id_asign==ea.getId_asignatura()) {
                id_Estudiantes.add(ea.getId_estudiante());
            }
        }

        estudiantes.stream().filter(p->id_Estudiantes.contains(p.getId_estudiante())).forEach(System.out::println);
    }
}
