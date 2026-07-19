import java.util.*;

public class FriendSuggestionService {

    private final UserRepository repository;

    public FriendSuggestionService(UserRepository repository) {
        this.repository = repository;
    }

    // Create friendship
    public void addFriendship(int userId1, int userId2) {

        if (userId1 == userId2) {
            System.out.println("A user cannot become friends with themselves.");
            return;
        }

        User user1 = repository.getUserById(userId1);
        User user2 = repository.getUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (user1.getFriends().contains(user2)) {
            System.out.println("Users are already friends.");
            return;
        }

        user1.addFriend(user2);
        user2.addFriend(user1);

        System.out.println(user1.getName() + " and " + user2.getName() + " are now friends.");
    }

    // Remove friendship
    public void removeFriendship(int userId1, int userId2) {

        User user1 = repository.getUserById(userId1);
        User user2 = repository.getUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        user1.removeFriend(user2);
        user2.removeFriend(user1);

        System.out.println(user1.getName() + " and " + user2.getName() + " are no longer friends.");
    }

    // Display friends
    public void displayFriends(int userId) {

        User user = repository.getUserById(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("\nFriends of " + user.getName() + ":");

        if (user.getFriends().isEmpty()) {
            System.out.println("No friends.");
            return;
        }

        for (User friend : user.getFriends()) {
            System.out.println("- " + friend.getName());
        }
    }

    // Suggest friends using BFS
    public void suggestFriends(int userId) {

        User user = repository.getUserById(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        Queue<User> queue = new LinkedList<>();
        Set<User> visited = new HashSet<>();
        Map<User, Integer> mutualFriendCount = new HashMap<>();

        queue.offer(user);
        visited.add(user);

        while (!queue.isEmpty()) {

            User current = queue.poll();

            for (User friend : current.getFriends()) {

                if (!visited.contains(friend)) {
                    visited.add(friend);
                    queue.offer(friend);
                }

                if (current != user &&
                        friend != user &&
                        !user.getFriends().contains(friend)) {

                    mutualFriendCount.put(
                            friend,
                            mutualFriendCount.getOrDefault(friend, 0) + 1);
                }
            }
        }

        if (mutualFriendCount.isEmpty()) {
            System.out.println("\nNo friend suggestions available.");
            return;
        }

        List<Map.Entry<User, Integer>> suggestions =
                new ArrayList<>(mutualFriendCount.entrySet());

        suggestions.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("\nFriend Suggestions for " + user.getName());

        for (Map.Entry<User, Integer> entry : suggestions) {

            System.out.println(
                    entry.getKey().getName() +
                    " (" + entry.getValue() + " mutual friend(s))");
        }
    }

    // Display complete social network
    public void displayNetwork() {

        System.out.println("\n========= SOCIAL NETWORK =========");

        for (User user : repository.getAllUsers()) {

            System.out.print(user.getName() + " -> ");

            if (user.getFriends().isEmpty()) {

                System.out.println("No Friends");
                continue;
            }

            for (User friend : user.getFriends()) {

                System.out.print(friend.getName() + " ");
            }

            System.out.println();
        }
    }

    // Total Friendships
    public void displayStatistics() {

        int friendships = 0;

        for (User user : repository.getAllUsers()) {

            friendships += user.getFriends().size();
        }

        friendships /= 2;

        System.out.println("\n========= NETWORK STATISTICS =========");
        System.out.println("Total Users : " + repository.getTotalUsers());
        System.out.println("Total Friendships : " + friendships);
    }

    // BFS Shortest Connection Path
    public void shortestConnection(int sourceId, int destinationId) {

        User source = repository.getUserById(sourceId);
        User destination = repository.getUserById(destinationId);

        if (source == null || destination == null) {
            System.out.println("User not found.");
            return;
        }

        Queue<User> queue = new LinkedList<>();
        Map<User, User> parent = new HashMap<>();
        Set<User> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {

            User current = queue.poll();

            if (current.equals(destination))
                break;

            for (User friend : current.getFriends()) {

                if (!visited.contains(friend)) {

                    visited.add(friend);
                    parent.put(friend, current);
                    queue.offer(friend);
                }
            }
        }

        if (!visited.contains(destination)) {

            System.out.println("No connection exists.");
            return;
        }

        List<User> path = new ArrayList<>();

        User current = destination;

        while (current != null) {

            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);

        System.out.println("\nShortest Connection:");

        for (int i = 0; i < path.size(); i++) {

            System.out.print(path.get(i).getName());

            if (i != path.size() - 1)
                System.out.print(" -> ");
        }

        System.out.println();
    }
}