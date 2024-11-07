import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entitites.Post;
import entitites.Profile;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBManager {
    MongoCollection<Profile> profiles; // Colección de perfiles
    Profile myProfile; // Mi perfil

    public MongoDBManager(String uri, String databaseName, String collectionName) {
        MongoClient mongoClient;
        try {
            mongoClient = MongoClients.create(uri);
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            MongoDatabase database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);

            profiles = database.getCollection(collectionName, Profile.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createProfile(String name, String status, int age) {
        myProfile = new Profile(name, status, age);
        profiles.insertOne(myProfile);
    }

    public void publishPost(String title, String content) {
        Post post = new Post(title, content);
        List<Post> posts= myProfile.getPosts();
        if (posts == null) posts = new ArrayList<>();
        posts.add(post);
        profiles.replaceOne(eq(myProfile.getId()), new Profile(myProfile.getName(), myProfile.getStatus(), myProfile.getAge(), posts));
    }

    public void updateStatus(String status) {
        myProfile.setStatus(status);
        profiles.replaceOne(eq(myProfile.getId()), myProfile);
    }

    public void deleteProfile() {
        profiles.deleteOne(eq(myProfile.getId()));
    }

    public void showProfiles() {
        for (Profile profile : profiles.find()) {
            System.out.println(profile);
        }
    }

    public void showPosts(String profileName) {
        Profile perfil = null;
        for (Profile profile : profiles.find(eq("name", profileName))) {
            perfil = profile;
        }
        if (perfil!=null) {
            System.out.println(perfil);
        }else {
            System.out.println("El perfil no existe");
        }

    }

    public void likePost(String profileName, String title) {
        Profile perfil = null;
        for (Profile profile : profiles.find(eq("name", profileName))) {
            perfil = profile;
        }
        if (perfil!=null) {
            if (!perfil.getPosts().isEmpty()) {
                for (Post post : perfil.getPosts()) {
                    if (post.getTitle().equals(title)) {
                        post.setLikes(post.getLikes() + 1);
                    }else {
                        System.out.println("Post no encontrado");
                    }
                }
            }else {
                System.out.println("El perfil no tiene publicaciones");
            }
        }else {
            System.out.println("El perfil no existe");
        }

        assert perfil != null;
        profiles.replaceOne(eq(perfil.getId()), perfil);
    }

    public void commentPost(String profileName, String title, String comment) {
        Profile perfil = null;
        for (Profile profile : profiles.find(eq("name", profileName))) {
            perfil = profile;
        }
        if (perfil!=null) {
            if (!perfil.getPosts().isEmpty()) {
                for (Post post : perfil.getPosts()) {
                    if (post.getTitle().equals(title)) {
                        post.addComment(comment);
                    }else {
                        System.out.println("Post no encontrado");
                    }
                }
            }else {
                System.out.println("El perfil no tiene publicaciones");
            }
        }else {
            System.out.println("El perfil no existe");
        }
        assert perfil != null;
        profiles.replaceOne(eq(perfil.getId()), perfil);
    }

    public void showProfileStats(String profileName) {
        Profile perfil = null;
        for (Profile profile : profiles.find()) {
            if (profile.getName().equals(profileName)) {
                perfil = profile;
            }
        }
        if (perfil != null) {
            System.out.println("Nombre: "+perfil.getName());
            if (!(perfil.getPosts() ==null)) {
                System.out.println("Numero de publicaciones: " + perfil.getPosts().size());
                System.out.println("Total de likes recibidos: " + perfil.getPosts().stream().mapToInt(Post::getLikes).sum());

                int contador=0;
                for (Post post : perfil.getPosts()) {
                    if (post.getComments() != null) {
                        for (String comment : post.getComments()) {
                            contador++;
                        }
                    }
                }
                System.out.println("Total de comentarios: " + contador);

            }else {
                System.out.println("""
                        El perfil no tiene publicaciones
                        El perfil no tiene likes
                        El perfil no tiene comentarios""");
            }
        }

    }

    public void showAllStats() {
        int totalPu=0;
        int totalLikes=0;
        int totalCom=0;
        for (Profile profile : profiles.find()) {
            if (profile.getPosts() == null) continue;
            totalPu+=profile.getPosts().size();
            totalLikes+=profile.getPosts().stream().mapToInt(Post::getLikes).sum();
            for (Post post : profile.getPosts()) {
                if (post.getComments() != null) {
                    for (String comment : post.getComments()) {
                        totalCom++;
                    }
                }
            }
        }
        List<Profile> profilesList = createCopyList();

        System.out.println("Numero total de perfiles: " + profiles.countDocuments());
        System.out.println("Total de publicaciones: " + totalPu);
        System.out.println("Total de likes: " + totalLikes);
        System.out.println("Total de comentarios: " + totalCom);

        profilesList.sort(Comparator.comparingInt(Profile::getAge));
        profilesList.stream().limit(3).forEach(pro-> System.out.println(pro.getName()));

        System.out.println("Los tres perfiles con mas publicaciones");
        profilesList.sort(Comparator.comparingInt(Profile::getSize).reversed());
        profilesList.stream().limit(3).forEach(pro-> System.out.println(pro.getName()));
    }

    private List<Profile> createCopyList() {
        List<Profile> profilesList = new ArrayList<>();
        for (Profile profile : profiles.find()) {
            profilesList.add(profile);
        }
        return profilesList;
    }
}
