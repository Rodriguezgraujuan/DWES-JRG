import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SolicitudDatosApi {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    // Metodo para hacer la solicitud GET a la API
    private String getApiData() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        JsonNode rootNode = objectMapper.readTree(new URL("https://pokeapi.co/api/v2/pokemon/"));
        return  rootNode.toString();
        //List<Location> locations = objectMapper.readValue(rootNode.get("results").traverse(), new TypeReference<>(){});
    /*
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Error: HTTP code " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;
        StringBuilder response = new StringBuilder();
        while ((output = br.readLine()) != null) {
            response.append(output);
        }

        conn.disconnect();
        return response.toString();

     */
    }

    // Metodo para obtener los primeros 20 Pokémon (sin detalles adicionales)
    public List<Pokemon> getPokemons() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Hacemos la solicitud a la API para obtener la lista de los primeros 20 Pokémon
        String jsonResponse = getApiData();

        // Deserializamos la respuesta de la lista de Pokémon
        ListaPokemons pokemonListResponse = objectMapper.readValue(jsonResponse, ListaPokemons.class);

        // Devolvemos la lista de resultados (nombres y URLs)
        return pokemonListResponse.getResults();
    }

    public PokemonDetallado getPokemonDetails(String identifier) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "https://pokeapi.co/api/v2/pokemon/" + identifier;  // Construimos la URL para el Pokémon

        // Hacemos la solicitud a la API para obtener los detalles del Pokémon
        JsonNode rootNode = objectMapper.readTree(new URL(url));
        String jsonResponse = rootNode.toString();

        // Deserializamos el JSON del Pokémon en un objeto Pokémon
        return objectMapper.readValue(jsonResponse, PokemonDetallado.class);
    }

}
