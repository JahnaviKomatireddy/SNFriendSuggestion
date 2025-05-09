import java.util.*;

public class SocialNetwork {
    Map<String, User> users = new HashMap<>();

    public void addUser(String name) {
        if (!users.containsKey(name)) {
            users.put(name, new User(name));
            System.out.println("User " + name + " added.");
        } else {
            System.out.println("User already exists.");
        }
    }

    public void sendRequest(String fromName, String toName) {
        User fromUser = users.get(fromName);
        User toUser = users.get(toName);
        if (fromUser != null && toUser != null) {
            fromUser.sendRequest(toUser);
        } else {
            System.out.println("One or both users not found.");
        }
    }

    public void showPending(String name) {
        User user = users.get(name);
        if (user != null) {
            user.showPendingRequests();
        } else {
            System.out.println("User not found.");
        }
    }

    public void acceptFriendRequest(String toName, String fromName) {
        User toUser = users.get(toName);
        User fromUser = users.get(fromName);
        if (toUser != null && fromUser != null) {
            toUser.acceptRequest(fromUser);
        } else {
            System.out.println("One or both users not found.");
        }
    }

    public void rejectFriendRequest(String toName, String fromName) {
        User toUser = users.get(toName);
        User fromUser = users.get(fromName);
        if (toUser != null && fromUser != null) {
            toUser.rejectFriendRequest(fromUser);
        } else {
            System.out.println("One or both users not found.");
        }
    }

    public List<String> suggestFriends(String username) {
        User user = users.get(username);
        if (user == null) {
            System.out.println("User not found.");
            return new ArrayList<>();
        }

        Map<User, Integer> suggestionMap = new HashMap<>();

        for (User friend : user.getFriends()) {
            for (User friendOfFriend : friend.getFriends()) {
                if (!friendOfFriend.equals(user) && !user.getFriends().contains(friendOfFriend)) {
                    suggestionMap.put(friendOfFriend, suggestionMap.getOrDefault(friendOfFriend, 0) + 1);
                }
            }
        }

        PriorityQueue<Map.Entry<User, Integer>> pq = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue()
        );
        pq.addAll(suggestionMap.entrySet());

        List<String> suggestions = new ArrayList<>();
        while (!pq.isEmpty()) {
            suggestions.add(pq.poll().getKey().name);
        }

        return suggestions;
    }

    public void displayNetwork() {
        for (String name : users.keySet()) {
            User user = users.get(name);
            System.out.print(user.name + " -> ");
            for (User f : user.getFriends()) {
                System.out.print(f.name + " ");
            }
            System.out.println();
        }
    }
}
