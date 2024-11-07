package entitites;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@BsonDiscriminator(value="profile", key="_cls")
public class  Profile {

    @BsonCreator
    public Profile(@BsonProperty(value="name")String name, @BsonProperty(value="status")String status,@BsonProperty(value="age")int age) {
        this.name = name;
        this.status = status;
        this.age = age;
        this.since = LocalDate.now();
    }

    public Profile(@BsonProperty(value="name")String name, @BsonProperty(value="status")String status,@BsonProperty(value="age")int age,@BsonProperty(value = "posts")List<Post> posts) {
        this.name = name;
        this.status = status;
        this.age = age;
        this.since = LocalDate.now();
        this.posts = posts;
    }

    //soy Juan
    @BsonId
    ObjectId id;

    @BsonProperty(value="name")
    String name; // nombre del perfil
    @BsonProperty(value="status")
    String status; // estado del perfil
    @BsonProperty(value="age")
    int age; // edad del perfil
    @BsonProperty(value="since")
    LocalDate since; // fecha de creación del perfil
    @BsonProperty(value="posts")
    List<Post> posts; // lista de amigos del perfil

    public int getSize(){
        int tamano = 0;
        if(posts!=null){
            tamano=posts.size();
        };
        return tamano;
    }


    @Override
    public String toString() {
        String string = "-".repeat(20) + "\n" + name + "\nUsuario desde: " + since + "\nEstado:" + status + "\nEdad: " + age + " años\n";
        if (posts != null) {
            string += "Publicaciones:\n";
            for (Post post : posts) {
                string += post + "\n";
            }
        } else {
            string += "No ha publicado nada todavía.\n";
        }
        string += "-".repeat(20);
        return string;
    }
}

