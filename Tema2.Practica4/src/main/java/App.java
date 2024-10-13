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
                        in.nextLine();
                        addPokemon(solicitud, allPokemon);
                        break;
                    case 3:
                        in.nextLine();
                        deletePokemon(allPokemon);
                        break;
                    case 4:
                        showList(allPokemon);
                        break;
                    case 5:
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
        System.out.println("2. Añadir un pokemon");
        System.out.println("3. Eliminar un pokemon");
        System.out.println("4. Mostrar lista");
        System.out.println("5. Salir");
    }

    public static void obtenerInformacionPokemon(SolicitudDatosApi solicitud) throws Exception {
        System.out.println("Ingrese el nombre del pokemon");
        System.out.println(solicitud.getPokemonDetails(in.nextLine()));
    }

    public static void addPokemon(SolicitudDatosApi solicitud, List<Pokemon> allPokemon) throws Exception {
        System.out.println("Ingrese el nombre del pokemon");
        String nombre = in.nextLine();
        PokemonDetallado pokemon = solicitud.getPokemonDetails(nombre);
        if (pokemon == null) {
            System.out.println("El Pokémon no se encontró en la API.");
        } else {
            allPokemon.add(new Pokemon(nombre, "https://pokeapi.co/api/v2/pokemon/"+pokemon.getId()));
        }
    }

    public static void deletePokemon(List<Pokemon> allPokemon){
        System.out.println("Ingrese el nombre del pokemon");
        String nombre = in.nextLine();
        allPokemon.removeIf(pokemon -> pokemon.getName().equals(nombre));
    }

    public static void showList(List<Pokemon> allPokemon) {
        allPokemon.forEach(System.out::println);
    }


}
