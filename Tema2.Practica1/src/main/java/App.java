import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws ParseException {
        YoutuberService y = new YoutuberService();
        Path fichero = Path.of("/home/juarodgra2/IdeaProjects/DWES-JRG/Tema2.Practica1/src/main/resources//youtubers.csv");
        List<Youtuber> youtubers = new ArrayList<>();
        y.leerFichero(fichero, youtubers);
        System.out.println("Youtuber con mas subs: "+ y.youtuberConMasSeguidores(youtubers));
        System.out.println("Media de Videos: "+y.mediaVideos(youtubers));
        System.out.println(y.youtubers2013(youtubers));
        System.out.println(y.youtuberTopIngresos3(youtubers));
        y.youtuberForYear(youtubers);
        //y.escribirFichero(fichero, youtubers);
    }
}
