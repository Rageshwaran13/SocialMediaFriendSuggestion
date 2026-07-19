import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private final Map<Integer, User> users;

    public UserRepository() {
        users = new HashMap<>();
    }

    // Add a new user
    public boolean addUser(User user) {

        if (user == null) {
            return false;
        }

        if (users.containsKey(user.getId())) {
            System.out.println("User ID already exists.");
            return false;
        }

        users.put(user.getId(), user);
        return true;
    }

    // Find user by ID
    public User getUserById(int id) {
        return users.get(id);
    }

    // Find user by Name
    public User getUserByName(String name) {

        for (User user : users.values()) {

            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }

        return null;
    }

    // Get all users
    public Collection<User> getAllUsers() {
        return users.values();
    }

    // Check if user exists
    public boolean containsUser(int id) {
        return users.containsKey(id);
    }

    // Total number of users
    public int getTotalUsers() {
        return users.size();
    }

    // Remove user
    public boolean removeUser(int id) {

        if (!users.containsKey(id)) {
            return false;
        }

        users.remove(id);
        return true;
    }

    // Remove all users
    public void clearRepository() {
        users.clear();
    }

    // Display one user
    public void displayUser(int id) {

        User user = users.get(id);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println(user);
    }

    // Display all users
    public void displayUsers() {

        if (users.isEmpty()) {
            System.out.println("No users available.");
            return;
        }

        System.out.println("\n===== All Users =====");

        for (User user : users.values()) {
            System.out.println(user);
        }
    }
}