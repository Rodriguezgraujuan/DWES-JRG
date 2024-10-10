import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

public class App {

    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Path ruta = Path.of("/home/juarodgra2/Documents/Tema2.Practica3/src/main/resources/animales.json");
        ProtectoraDeAnimales pAnimales = JsonService.leerArrayObjetosJson(ruta);

        boolean condition = true;
        while (condition) {
            menu();
            switch (in.nextInt()) {
                case 1:
                    pAnimales.addAnimal(crearAnimal(pAnimales));
                    break;
                case 2:
                    in.nextLine();
                    System.out.println("Introduce el ID");
                    pAnimales.deleteAnimal(in.nextInt());
                    break;
                case 3:
                    in.nextLine();
                    System.out.println("Introduce el ID");
                    pAnimales.findAnimal(in.nextInt());
                    break;
                case 4:
                    pAnimales.showAnimals();
                    break;
                case 5:
                    condition = false;
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        }
        JsonService.escribirListaObjetosJson(ruta, pAnimales);
    }

    public static void menu() {
        System.out.println("1. Añadir Animal");
        System.out.println("2. Borrar Animal");
        System.out.println("3. Consultar animal por ID");
        System.out.println("4. Mostrar Animales");
        System.out.println("5. Salir");
    }

    public static Animal crearAnimal(ProtectoraDeAnimales pAnimales){
        int id = pAnimales.getAnimals().size()+1;
        System.out.println("Introduce el nombre");
        in.nextLine();
        String nombre = in.nextLine();
        System.out.println("Introduce la especie");
        String especie = in.nextLine();
        System.out.println("Introduce la edad");
        int edad = in.nextInt();
        System.out.println("Introduce el sexo");
        in.nextLine();
        String sexo = in.nextLine();
        System.out.println("Introduce la fecha de ingreso");
        LocalDate fechaIngreso = LocalDate.parse(in.nextLine());
        System.out.println("¿Se ha adoptado? si-no");
        boolean adoptado = in.nextLine().equals("si");
        return new Animal(id, nombre, especie, edad, sexo, fechaIngreso, adoptado);
    }

}
