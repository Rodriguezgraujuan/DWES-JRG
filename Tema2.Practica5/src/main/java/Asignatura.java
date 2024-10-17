import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {
    private int id_asignatura;
    private String nombre_asignatura;
    private String aula;
    private boolean obligatoria;
}
