import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BBDDService bbddService = new BBDDService();
        AppService appService = new AppService();

        List<Casa> casas = bbddService.recibirDatosCasa();
        List<Estudiante> estudiantes = bbddService.recibirDatosEstudiantes();
        List<Asignatura> asignaturas = bbddService.recibirAsignaturas();
        List<Profesor> profesores = bbddService.recibirProfesores();
        List<Mascota> mascotas = bbddService.recibirMascotas();
        List<Estudiante_Asignatura> estudiante_asignatura = bbddService.recibirEstudianteAsignatura();

        boolean condition = true;
        while (condition) {
            menu();
            switch (in.nextInt()) {
                case 1:
                    bbddService.consultaEstidiantesCasa();
                    break;
                case 2:
                    appService.listadoAsignaturasObligatorias(asignaturas);
                    break;
                case 3:
                    in.nextLine();
                    System.out.println("Introduce el nombre del estudiante");
                    appService.mostrarMascotas(mascotas, appService.extraerIdEstudiante(in.nextLine(), estudiantes));
                    break;
                case 4:
                    in.nextLine();
                    appService.mostrarEstudiantesSinMascotas(estudiantes, mascotas);
                    break;
                case 5:
                    appService.mostrarMediaEstudiante(estudiantes, estudiante_asignatura);
                    break;
                case 6:
                    appService.estudiantesPorCasa(casas,estudiantes);
                    break;
                case 7:
                    in.nextLine();
                    appService.estudiantesMatriculaEspecifica(estudiantes, asignaturas, estudiante_asignatura);
                    break;
                case 11:
                    condition = false;
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        }
    }

    public static void menu() {
        System.out.println("1. Consultar estudiantes casa Gryffindor");
        System.out.println("2. Mostrar asignaturas obligatorias");
        System.out.println("3. Mostrar mascotas de un estudiante");
        System.out.println("4. Mostrar estudiantes sin mascotas");
        System.out.println("5. Mostrar media de notas de un estudiante");
        System.out.println("6. Mostrar estudiantes por casa");
        System.out.println("7. Mostrar estudiantes matriculadas en una asignatura");
        System.out.println("11. Salir");
    }
}
