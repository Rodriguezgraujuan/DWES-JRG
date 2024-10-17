import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {
    private int id_mascota;
    private String nombre_mascota;
    private String especie;
    private int id_estudiante;
}
