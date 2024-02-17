import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Player> players = new ArrayList<>();

        for (;;) {
            char[][] board = new char[7][7];
            initializeBoard(board);

            System.out.print("Enter your name: ");
            String playerName = scanner.nextLine();

            System.out.println("Welcome, " + playerName + "! Let's place your ships.");

            System.out.println("All ships placed. Game starts now!");

            int shots = playGame(scanner, board);
            players.add(new Player(playerName, shots));

            System.out.println("Congratulations, " + playerName + "! You sank all the ships in " + shots + " shots.");

            System.out.print("Do you want to start another game? (yes/no): ");
            if (!scanner.nextLine().equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("Game over. Here are the results:");

        players.sort(java.util.Comparator.comparingInt(Player::getShots));

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println((i + 1) + ". " + player.getName() + " - " + player.getShots() + " shots");
        }

        System.out.println("Thank you for playing!");

        scanner.close();
    }

    private static void initializeBoard(char[][] board) {

    }

    private static int playGame(Scanner scanner, char[][] board) {

        return 0; 
    }

    private static class Player {
        private String name;
        private int shots;

        public Player(String name, int shots) {
            this.name = name;
            this.shots = shots;
        }

        public String getName() {
            return name;
        }

        public int getShots() {
            return shots;
        }
    }
}

