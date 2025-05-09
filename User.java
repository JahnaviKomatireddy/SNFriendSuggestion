import java.util.*;

public class User {
    String name;
    Set<User> friends;
    Set<User> pendingRequests;

    public User(String name) {
        this.name = name;
        this.friends = new HashSet<>();
        this.pendingRequests = new HashSet<>();
    }

    public void sendRequest(User toUser) {
        if (toUser == this || friends.contains(toUser)) {
            System.out.println("Request not needed or already friends.");
        } else {
            toUser.pendingRequests.add(this);
            System.out.println("Friend request sent to " + toUser.name);
        }
    }

    public void showPendingRequests() {
        if (pendingRequests.isEmpty()) {
            System.out.println("No pending requests.");
        } else {
            System.out.println("Pending requests:");
            for (User u : pendingRequests) {
                System.out.println("- " + u.name);
            }
        }
    }

    public void acceptRequest(User fromUser) {
        if (pendingRequests.contains(fromUser)) {
            friends.add(fromUser);
            fromUser.friends.add(this);
            pendingRequests.remove(fromUser);
            System.out.println("Accepted friend request from " + fromUser.name);
        } else {
            System.out.println("No request from " + fromUser.name);
        }
    }

    public void rejectFriendRequest(User fromUser) {
        if (pendingRequests.contains(fromUser)) {
            pendingRequests.remove(fromUser);
            System.out.println("Rejected friend request from " + fromUser.name);
        } else {
            System.out.println("No pending request from " + fromUser.name);
        }
    }

    public Set<User> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return name;
    }
}
