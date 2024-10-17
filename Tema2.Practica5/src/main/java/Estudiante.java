import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    private int id_estudiante;
    private String nombre;
    private String apellido;
    private int id_casa;
    private int anyo_curso;
    private Date fecha_nacimiento;

    @Override
    public String toString() {
        return "Estudiante{" +
                "id_estudiante=" + id_estudiante +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", id_casa=" + id_casa +
                ", anyo_curso=" + anyo_curso +
                ", fecha_nacimiento=" + fecha_nacimiento +
                '}';
    }
}
