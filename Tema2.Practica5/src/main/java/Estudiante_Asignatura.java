import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante_Asignatura {
    private int id_estudiante;
    private int id_asignatura;
    private double calificacion;
}
