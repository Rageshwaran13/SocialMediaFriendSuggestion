import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        UserRepository repository = new UserRepository();
        FriendSuggestionService service = new FriendSuggestionService(repository);

        // Sample Users
        repository.addUser(new User(1, "Alice"));
        repository.addUser(new User(2, "Bob"));
        repository.addUser(new User(3, "Charlie"));
        repository.addUser(new User(4, "David"));
        repository.addUser(new User(5, "Eve"));

        // Sample Friendships
        service.addFriendship(1, 2);
        service.addFriendship(1, 3);
        service.addFriendship(2, 4);
        service.addFriendship(3, 5);

        while (true) {

            System.out.println("\n======================================");
            System.out.println(" SOCIAL MEDIA FRIEND SUGGESTION SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Display All Users");
            System.out.println("2. Add User");
            System.out.println("3. Remove User");
            System.out.println("4. Add Friendship");
            System.out.println("5. Remove Friendship");
            System.out.println("6. Display Friends");
            System.out.println("7. Suggest Friends (BFS)");
            System.out.println("8. Display Complete Network");
            System.out.println("9. Network Statistics");
            System.out.println("10. Shortest Connection (BFS)");
            System.out.println("0. Exit");
            System.out.print("\nEnter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    repository.displayUsers();
                    break;

                case 2:
                    addUser(repository);
                    break;

                case 3:
                    removeUser(repository);
                    break;

                case 4:
                    addFriendship(service);
                    break;

                case 5:
                    removeFriendship(service);
                    break;

                case 6:
                    displayFriends(service);
                    break;

                case 7:
                    suggestFriends(service);
                    break;

                case 8:
                    service.displayNetwork();
                    break;

                case 9:
                    service.displayStatistics();
                    break;

                case 10:
                    shortestPath(service);
                    break;

                case 0:
                    System.out.println("Thank you for using the system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addUser(UserRepository repository) {

        System.out.print("Enter User ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter User Name: ");
        String name = scanner.nextLine();

        if (repository.addUser(new User(id, name))) {
            System.out.println("User added successfully.");
        } else {
            System.out.println("Failed to add user.");
        }
    }

    private static void removeUser(UserRepository repository) {

        System.out.print("Enter User ID: ");
        int id = scanner.nextInt();

        if (repository.removeUser(id)) {
            System.out.println("User removed.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void addFriendship(FriendSuggestionService service) {

        System.out.print("First User ID: ");
        int id1 = scanner.nextInt();

        System.out.print("Second User ID: ");
        int id2 = scanner.nextInt();

        service.addFriendship(id1, id2);
    }

    private static void removeFriendship(FriendSuggestionService service) {

        System.out.print("First User ID: ");
        int id1 = scanner.nextInt();

        System.out.print("Second User ID: ");
        int id2 = scanner.nextInt();

        service.removeFriendship(id1, id2);
    }

    private static void displayFriends(FriendSuggestionService service) {

        System.out.print("Enter User ID: ");
        int id = scanner.nextInt();

        service.displayFriends(id);
    }

    private static void suggestFriends(FriendSuggestionService service) {

        System.out.print("Enter User ID: ");
        int id = scanner.nextInt();

        service.suggestFriends(id);
    }

    private static void shortestPath(FriendSuggestionService service) {

        System.out.print("Source User ID: ");
        int source = scanner.nextInt();

        System.out.print("Destination User ID: ");
        int destination = scanner.nextInt();

        service.shortestConnection(source, destination);
    }
}