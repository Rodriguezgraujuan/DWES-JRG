import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws SQLException {
        Scanner in = new Scanner(System.in);
        BBDDService bBDDService = new BBDDService();

        boolean condition = true;
        while (condition) {
            menu();
            switch (in.nextInt()) {
                case 1:
                    List<List<String>> estudiantesCasa = bBDDService.recibirEstudiantesCasa();
                    estudiantesCasa.forEach(System.out::println);
                    break;
                case 2:
                    List<List<String>> estudiantesMascotas = bBDDService.estudiantesMascotas();
                    estudiantesMascotas.forEach(System.out::println);
                    break;
                case 3:
                    List<List<String>> estudiantesConMasc = bBDDService.estudiantesConMascotas();
                    estudiantesConMasc.forEach(System.out::println);
                    break;
                case 4:
                    bBDDService.estudiantesSinMascotas();
                    break;
                case 11:
                    condition = false;
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        }
    }

    public static void menu(){
        System.out.println("1. Consultar estudiantes con sus casas");
        System.out.println("2. Consultar estudiante con sus mascotas");
        System.out.println("3. Consultar estudiantes con mascotas");
        System.out.println("4. Consultar estudiantes sin mascotas");
        System.out.println("11. Salir");
    }
}
