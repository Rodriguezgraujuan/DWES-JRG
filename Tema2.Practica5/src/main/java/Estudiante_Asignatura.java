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

    @Override
    public String toString() {
        return "Estudiante_Asignatura{" +
                "id_estudiante=" + id_estudiante +
                ", id_asignatura=" + id_asignatura +
                ", calificacion=" + calificacion +
                '}';
    }
}
