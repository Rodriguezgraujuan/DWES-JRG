import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProtectoraDeAnimales {

    @JsonProperty("animales")
    private List<Animal> animals;

    public void showAnimals() {
        animals.forEach(System.out::println);
    }

    public void addAnimal(Animal animal){
        animals.add(animal);
    }

    public void deleteAnimal(int id){
        animals.removeIf(p->p.getId()==(id));
    }

    public void findAnimal(int id){
        System.out.println(animals.stream().filter(p -> p.getId()==(id)).findFirst());
    }


}
