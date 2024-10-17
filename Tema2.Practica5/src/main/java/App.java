import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class App {
    public static void main(String[] args) {
        BBDDService bbddService = new BBDDService();
        List<Casa> casas = bbddService.recibirDatosCasa();
        List<Estudiante> estudiantes = bbddService.recibirDatosEstudiantes();
        estudiantes.forEach(System.out::println);
        //bbddService.consultaEstidiantesCasa();

    }
}
