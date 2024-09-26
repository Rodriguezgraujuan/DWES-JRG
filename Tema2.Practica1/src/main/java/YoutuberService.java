import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class YoutuberService {
    static SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ParseException {
        Path fichero = Path.of("/home/juarodgra2/IdeaProjects/DWES-JRG/Tema2.Practica1/src/main/resources//youtubers.csv");
        List<Youtuber> youtubers = new ArrayList<>();
        leerFichero(fichero, youtubers);
        System.out.println("Youtuber con mas subs: "+ youtuberConMasSeguidores(youtubers));
        System.out.println("Media de Videos: "+mediaVideos(youtubers));
        System.out.println(youtubers2013(youtubers));
        System.out.println(youtuberTopIngresos3(youtubers));
        youtuberForYear(youtubers);
    }

    public static Youtuber youtuberConMasSeguidores(List<Youtuber> youtubers) throws ParseException {
        Youtuber youtuber = null;
        if (!(youtubers.isEmpty())) {
              Optional<Youtuber> optionalYou=youtubers.stream().max(Comparator.comparingInt(Youtuber::numSegruidores));
              youtuber = optionalYou.orElse(new Youtuber("",date.parse("2000-0-0"),0,0));
        }
        return youtuber;
    }

    public static Double mediaVideos(List<Youtuber> youtubers){
        OptionalDouble optionalDouble = youtubers.stream().mapToInt(Youtuber::numVideos).average();
        return (double) Math.round(optionalDouble.orElse(0));
    }

    public static List<Youtuber> youtubers2013(List<Youtuber> youtubers){
        return youtubers.stream()
                .filter(p-> {
            try {
                return p.fecha().before((date.parse("2013-00-00")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    public static List<Youtuber> youtuberTopIngresos3(List<Youtuber> youtubers){
        return youtubers.stream().sorted(Comparator.comparingDouble(Youtuber::estimatedIncome).reversed()).toList();
    }

    public static void youtuberForYear(List<Youtuber> youtubers) throws ParseException {
        SimpleDateFormat dateYear = new SimpleDateFormat("yyyy");
        List<String> years = youtubers.stream().map(p-> dateYear.format(p.fecha())).distinct().toList();
        for (String year : years){
            System.out.println(year);
            youtubers.stream().filter(p-> dateYear.format(p.fecha()).equals(year)).forEach(System.out::println);
        }
    }









/*
    public static void escribirFichero(Path fichero){
        try (FileOutputStream fos = new FileOutputStream(fichero.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            Youtuber youtuber = new Youtuber("Juan Palomo",  25, 1400);
            oos.writeObject(youtuber);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
*/
    public static void leerFichero(Path fichero, List<Youtuber> youtubers) {
        try {
            List<String> archivoLeido = Files.readAllLines(fichero);

            for (int i = 1; i<archivoLeido.size();i++){
                String linea = archivoLeido.get(i);
                String[] campos = linea.split(",");

                if (campos.length==4) {
                    String name = campos[0];

                    //Transformo la fecha (String) en date
                    Date fecha = date.parse(campos[1]);

                    int videos = Integer.parseInt(campos[2]);
                    int seguidores = Integer.parseInt(campos[3]);

                    youtubers.add(new Youtuber(name, fecha, videos, seguidores));
                }else {
                    System.out.println("No tiene 4 campos");
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

}
