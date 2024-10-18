package entities;

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

    @Override
    public String toString() {
        return "entities.Asignatura{" +
                "id_asignatura=" + id_asignatura +
                ", nombre_asignatura='" + nombre_asignatura + '\'' +
                ", aula='" + aula + '\'' +
                ", obligatoria=" + obligatoria +
                '}';
    }
}
