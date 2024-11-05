import java.util.Scanner;

public class DummySocialNetwork {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bienvenido a DummySocialNetwork");
        MongoDBManager mongoDBManager = new MongoDBManager("mongodb://98.84.89.194:27017", "JuanRodriguez", "Perfiles");
        int opcion;
        do {
            opcion = menu();
            switch (opcion) {
                case 1:
                    newProfile(mongoDBManager);
                    break;
                case 2:
                    publishPost(mongoDBManager);
                    break;
                case 3:
                    mongoDBManager.showProfiles();
                    break;
                case 4:
                    updateStatus(mongoDBManager);
                    break;
                case 5:
                    showProfile(mongoDBManager);
                    break;
                case 6:
                    likePost(mongoDBManager);
                    break;
                case 7:
                    commentPost(mongoDBManager);
                    break;
                case 8:
                    showStatsProfile(mongoDBManager);
                    break;
                case 9:
                    mongoDBManager.showAllStats();
                    break;
                case 10:
                    System.out.println("Borrar mi perfil");
                    mongoDBManager.deleteProfile();
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private static int menu() {
        System.out.println("0. Salir");
        System.out.println("1. Crear mi perfil");
        System.out.println("2. Crear una publicación en mi perfil");
        System.out.println("3. Ver todos los perfiles");
        System.out.println("4. Actualizar mi estado");
        System.out.println("5. Ver un perfil y sus publicaciones");
        System.out.println("6. Dar like a una publicación");
        System.out.println("7. Comentar en una publicación");
        System.out.println("8. Mostrar estadísticas de mi perfil");
        System.out.println("9. Mostrar estadísticas de la red social");
        System.out.println("10. Borrar mi perfil");
        System.out.println("Seleccione una opción:");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    private static void newProfile(MongoDBManager mongoDBManager) {
        System.out.println("Crear mi perfil");
        System.out.print("Introduce tu nombre:");
        String name = scanner.nextLine();
        System.out.print("Introduce tu estado:");
        String status = scanner.nextLine();
        System.out.print("Introduce tu edad:");
        int age = scanner.nextInt();
        scanner.nextLine();
        mongoDBManager.createProfile(name, status, age);
    }

    private static void publishPost(MongoDBManager mongoDBManager) {
        System.out.println("Crear una publicación en mi perfil");
        System.out.print("Introduce el título de la publicación:");
        String title = scanner.nextLine();
        System.out.print("Introduce el contenido de la publicación:");
        String content = scanner.nextLine();
        mongoDBManager.publishPost(title, content);
    }

    private static void updateStatus(MongoDBManager mongoDBManager) {
        System.out.println("Actualizar mi estado");
        System.out.print("Introduce tu nuevo estado:");
        String status = scanner.nextLine();
        mongoDBManager.updateStatus(status);
    }

    private static void likePost(MongoDBManager mongoDBManager) {
        System.out.println("Dar like a una publicación");
        System.out.println("Introduce el nombre del perfil:");
        String profileName = scanner.nextLine();
        System.out.println("Introduce el título de la publicación:");
        String postTitle = scanner.nextLine();
        mongoDBManager.likePost(profileName, postTitle);
    }

    private static void commentPost(MongoDBManager mongoDBManager) {
        System.out.println("Comentar en una publicación");
        System.out.print("Introduce el nombre del perfil:");
        String profileName = scanner.nextLine();
        System.out.print("Introduce el título de la publicación:");
        String postTitle = scanner.nextLine();
        System.out.print("Introduce el comentario:");
        String comment = scanner.nextLine();
        mongoDBManager.commentPost(profileName, postTitle, comment);
    }

    private static void showProfile(MongoDBManager mongoDBManager) {
        System.out.println("Ver publicaciones de un perfil");
        System.out.print("Introduce el nombre del perfil:");
        String profileName = scanner.nextLine();
        mongoDBManager.showPosts(profileName);
    }

    private static void showStatsProfile(MongoDBManager mongoDBManager) {
        System.out.print("Introduce el nombre del perfil:");
        String profileName = scanner.nextLine();
        mongoDBManager.showProfileStats(profileName);
    }
}
