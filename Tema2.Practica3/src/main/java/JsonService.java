import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonService {
    public static ProtectoraDeAnimales leerArrayObjetosJson(Path ruta) {
        try {
            ObjectMapper objetoMapper = new ObjectMapper();
            return objetoMapper.readValue(ruta.toFile(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirListaObjetosJson(Path ruta,ProtectoraDeAnimales pAnimales) {
        try {
            Files.deleteIfExists(ruta);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(ruta.toFile(), pAnimales);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
