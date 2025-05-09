import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialNetwork network = new SocialNetwork();

        while (true) {
            System.out.println("\n--- Social Network Menu ---");
            System.out.println("1. Add User");
            System.out.println("2. Send Friend Request");
            System.out.println("3. Show Pending Requests");
            System.out.println("4. Accept Friend Request");
            System.out.println("5. Reject Friend Request");
            System.out.println("6. Suggest Friends");
            System.out.println("7. Display Network");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String name = scanner.nextLine();
                    network.addUser(name);
                }
                case 2 -> {
                    System.out.print("From user: ");
                    String from = scanner.nextLine();
                    System.out.print("To user: ");
                    String to = scanner.nextLine();
                    network.sendRequest(from, to);
                }
                case 3 -> {
                    System.out.print("Enter username: ");
                    String name = scanner.nextLine();
                    network.showPending(name);
                }
                case 4 -> {
                    System.out.print("Enter your username: ");
                    String to = scanner.nextLine();
                    System.out.print("Enter sender's username: ");
                    String from = scanner.nextLine();
                    network.acceptFriendRequest(to, from);
                }
                case 5 -> {
                    System.out.print("Enter your username: ");
                    String to = scanner.nextLine();
                    System.out.print("Enter username whose request you want to reject: ");
                    String from = scanner.nextLine();
                    network.rejectFriendRequest(to, from);
                }
                case 6 -> {
                    System.out.print("Suggest friends for: ");
                    String username = scanner.nextLine();
                    List<String> suggestions = network.suggestFriends(username);
                    System.out.println("Friend Suggestions: " + suggestions);
                }
                case 7 -> network.displayNetwork();
                case 8 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
