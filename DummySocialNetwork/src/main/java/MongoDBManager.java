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
        if (posts == null) posts = new java.util.ArrayList<>();
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
    }

    public void likePost(String profileName, String title) {
        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }

    public void commentPost(String profileName, String title, String comment) {
        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }

    public void showProfileStats() {
        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }

    public void showAllStats() {
        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }
}
