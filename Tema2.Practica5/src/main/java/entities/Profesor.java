package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {

    private int id_profesor;
    private String nombre;
    private String apellido;
    private int id_asignatura;
    private Date fecha_inicio;

    @Override
    public String toString() {
        return "entities.Profesor{" +
                "id_profesor=" + id_profesor +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", id_asignatura=" + id_asignatura +
                ", fecha_inicio=" + fecha_inicio +
                '}';
    }
}
