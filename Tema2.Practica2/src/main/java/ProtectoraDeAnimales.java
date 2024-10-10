import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "protectoraDeAnimales")
public class ProtectoraDeAnimales {
    @JacksonXmlElementWrapper(localName = "animales")
    @JacksonXmlProperty(localName = "animal")
    private List<Animal> animals;

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void deleteAnimal(String id) {
        animals.removeIf(p->p.getId().equals(id));
    }

    public void findAnimal(String id) {
        System.out.println(animals.stream().filter(p -> p.getId().equals(id)).findFirst());
    }

    public void showAnimals() {
        animals.forEach(System.out::println);
    }
}
