import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {

    private final int id;
    private final String name;
    private final Set<User> friends;

    // Constructor
    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.friends = new HashSet<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<User> getFriends() {
        return friends;
    }

    // Add a friend
    public void addFriend(User user) {
        if (user != null && user != this) {
            friends.add(user);
        }
    }

    // Remove a friend
    public void removeFriend(User user) {
        friends.remove(user);
    }

    // Users are equal if they have the same ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof User))
            return false;

        User other = (User) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}