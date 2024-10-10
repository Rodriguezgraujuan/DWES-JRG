import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class XmlService {

    public static ProtectoraDeAnimales leerArrayObjetosXml(Path ruta) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(ruta.toFile(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirListaObjetosXml(Path ruta,ProtectoraDeAnimales pAnimales) {
        try {
            Files.deleteIfExists(ruta);
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(ruta.toFile(), pAnimales);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
