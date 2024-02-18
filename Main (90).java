import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        while (true) {
            char[][] playerBoard = initializeBoard();
            char[][] computerBoard = initializeBoard();

            placeShips(playerBoard);

            int shots = 0;
            while (countShips(playerBoard) > 0) {
                displayBoard(playerBoard);
                System.out.print("Enter your shot (e.g., A3): ");
                String shot = scanner.nextLine().toUpperCase();

                if (isValidShot(shot, playerBoard)) {
                    shots++;
                    processShot(shot, playerBoard, computerBoard);
                } else {
                    System.out.println("Invalid shot! Try again.");
                }

                clearScreen();
            }

            displayBoard(playerBoard);
            System.out.println("Congratulations, " + playerName + "! You sunk all the ships in " + shots + " shots.");

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.nextLine().toLowerCase();
            if (!playAgain.equals("yes")) {
                System.out.println("Thanks for playing! Here is the final ranking:");
                break;
            }
        }

        scanner.close();
    }

    private static char[][] initializeBoard() {
        char[][] board = new char[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }

    private static void displayBoard(char[][] board) {
        System.out.println("   A B C D E F G");
        for (int i = 0; i < 7; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 7; j++) {
                System.out.print("|" + markToSymbol(board[i][j]));
            }
            System.out.println("|");
        }
    }

    private static char markToSymbol(char mark) {
        switch (mark) {
            case ' ':
                return ' ';
            case 'M':
                return 'O'; // Miss
            case 'H':
                return 'X'; // Hit
            case 'S':
                return '#'; // Sunk
            default:
                return mark;
        }
    }

    private static void placeShips(char[][] board) {
    }

    private static int countShips(char[][] board) {
        return 0;
    }

    private static boolean isValidShot(String shot, char[][] board) {
        return false;
    }

    private static void processShot(String shot, char[][] playerBoard, char[][] computerBoard) {
    }

    private static void clearScreen() {
    }
}


