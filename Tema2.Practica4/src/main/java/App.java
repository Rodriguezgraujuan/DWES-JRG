import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            SolicitudDatosApi solicitud = new SolicitudDatosApi();
            // Obtenemos todos los Pokémon
            List<Pokemon> allPokemon = solicitud.getPokemons();

            // Mostramos la cantidad total de Pokémon obtenidos
            System.out.println("Se obtuvieron " + allPokemon.size() + " Pokémon en total.");

            allPokemon.forEach(System.out::println);

            boolean condition = true;
            while (condition) {
                menu();
                int option = in.nextInt();
                switch (option) {
                    case 1:
                        in.nextLine();
                        obtenerInformacionPokemon(solicitud);
                        break;
                    case 2:
                        condition = false;
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void menu() {
        System.out.println("1. Obtener información de los pokemons");
        System.out.println("2. Salir");
    }

    public static void obtenerInformacionPokemon(SolicitudDatosApi solicitud) throws Exception {
        System.out.println("Ingrese el nombre del pokemon");
        System.out.println(solicitud.getPokemonDetails(in.nextLine()));
    }

}
