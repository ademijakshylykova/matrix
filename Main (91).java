import java.io.IOException;
import java.util.Scanner;

public class Main{
    static String playerName1 = "Player#1";
    static String playerName2 = "Player#2";
    static Scanner scanner = new Scanner(System.in);
    static int[][] battlefield1 = new int[7][7];
    static int[][] battlefield2 = new int[7][7];
    static int[][] monitor1 = new int[7][7];
    static int[][] monitor2 = new int[7][7];

    public static void main(String[] args) {
        System.out.println("Player#1, please enter your name:");
        playerName1 = scanner.nextLine();
        System.out.println("Player#2, please enter your name:");
        playerName2 = scanner.nextLine();
        placeShips(playerName1, battlefield1);
        placeShips(playerName2, battlefield2);
        while (true) {
            makeTurn(playerName1, monitor1, battlefield2);
            if (isWinCondition()) {
                break;
            }
            makeTurn(playerName2, monitor2, battlefield1);
            if (isWinCondition()) {
                break;
            }
        }

    }

    public static void placeShips(String playerName, int[][] battlefield) {
    int[] shipSizes = {3, 2, 2, 1, 1, 1, 1};  

    for (int deck : shipSizes) {
        while (true) {
            System.out.println();
            System.out.println(playerName + ", please place your " + deck + "-deck ship on the battlefield:");
            System.out.println();

            drawField(battlefield);

            System.out.println("Please enter OX coordinate:");
            int x = scanner.nextInt();
            System.out.println("Please enter OY coordinate:");
            int y = scanner.nextInt();
            System.out.println("Choose direction:");
            System.out.println("1. Vertical.");
            System.out.println("2. Horizontal.");
            int direction = scanner.nextInt();
            
            if (!isAvailable(x, y, deck, direction, battlefield)) {
                System.out.println("Wrong coordinates! Try again.");
                continue;
            }
            
            // Place the ship
            for (int i = 0; i < deck; i++) {
                if (direction == 1) {
                    battlefield[x][y + i] = 1;
                } else {
                    battlefield[x + i][y] = 1;
                }
            }

            // Update the battlefield and display
            clearScreen();
            drawField(battlefield);

            break; // Move to the next ship
        }
    }
}

            public static void drawField(int[][] battlefield) {
            System.out.println("  0 1 2 3 4 5 6 ");
                for (int i = 0; i < battlefield.length; i++) {
                System.out.print(i + " ");
                    for (int j = 0; j < battlefield[1].length; j++) {
                        if (battlefield[j][i] == 0) {
                    System.out.print("- ");
                        } else {
                    System.out.print("1 ");
                        }
                    }
                    System.out.println();
                }
            }

            public static void makeTurn(String playerName, int[][] monitor, int[][] battlefield) {
            while (true) {
                System.out.println(playerName + ", please, make your turn.");
                System.out.println("  0 1 2 3 4 5 6");
                for (int i = 0; i < monitor.length; i++) {
                    System.out.print(i + " ");
                    for (int j = 0; j < monitor[1].length; j++) {
                        if (monitor[j][i] == 0) {
                            System.out.print("- ");
                        } else if (monitor[j][i] == 1) {
                            System.out.print(". ");
                        } else if (monitor[j][i] == 2) {
                            System.out.print("X ");
                        } else {
                            System.out.print("S ");
                        }
                    }
                    System.out.println();
                }
                System.out.println("Please enter OX coordinate:");
                int x = scanner.nextInt();
                System.out.println("Please enter OY coordinate:");
                int y = scanner.nextInt();
        
                if (battlefield[x][y] == 1) {
                    System.out.println("Hit! Make your turn again!");
                    monitor[x][y] = 2;
        
                    if (checkSunk(x, y, battlefield, monitor)) {
                        System.out.println("Ship Sunk!");
                        markSunkShip(x, y, battlefield, monitor);
                    }
                } else {
                    System.out.println("Miss! Your opponent's turn!");
                    monitor[x][y] = 1;
                    break;
                }
                clearScreen();
            }
        }

public static void markSunkShip(int x, int y, int[][] battlefield, int[][] monitor) {
    int deck = 0;

    while (x + deck < battlefield.length && battlefield[x + deck][y] == 1) {
        monitor[x + deck][y] = 3; // Mark the cell as "Sunk"
        deck++;
    }
    deck = 0;
    while (y + deck < battlefield[0].length && battlefield[x][y + deck] == 1) {
        monitor[x][y + deck] = 3; // Mark the cell as "Sunk"
        deck++;
    }
}

public static boolean checkSunk(int x, int y, int[][] battlefield, int[][] monitor) {
    int deck = 0;
    while (x + deck < battlefield.length && battlefield[x + deck][y] == 1) {
        if (monitor[x + deck][y] != 2) {
            return false; // Ship not completely hit
        }
        deck++;
    }
    deck = 0;
    while (y + deck < battlefield[0].length && battlefield[x][y + deck] == 1) {
        if (monitor[x][y + deck] != 2) {
            return false; // Ship not completely hit
        }
        deck++;
    }
    return true; // Ship is completely hit and sunk
}
    public static boolean isWinCondition() {
        int counter1 = 0;
        for (int i = 0; i < monitor1.length; i++) {
            for (int j = 0; j < monitor1[i].length; j++) {
                if (monitor1[i][j] == 2) {
                    counter1++;
                }
            }
        }

        int counter2 = 0;
        for (int i = 0; i < monitor2.length; i++) {
            for (int j = 0; j < monitor2[i].length; j++) {
                if (monitor2[i][j] == 2) {
                    counter2++;
                }
            }
        }

        if (counter1 >=11) {
            System.out.println(playerName1 + " WIN!!!");
            return true;
        }
        if (counter2 >=11){
            System.out.println(playerName2 + " WIN!!!");
            return true;
        }
        return false;
    }

    public static boolean isAvailable(int x, int y, int deck, int rotation, int[][] battlefield) {
        // out of bound check
        if (rotation == 1) {
            if (y + deck > battlefield.length) {
                return false;
            }
        }
        if (rotation == 2){
            if (x + deck > battlefield[0].length){
                return false;
            }
        }

        //neighbours check 
       
        while (deck!=0){
            for (int i = 0; i < deck; i++) {
                int xi = 0;
                int yi = 0;
                if (rotation == 1){
                    yi = i;
                } else{
                    xi = i;
                }
//                battlefield[x ][y];
                if (x + 1 + xi < battlefield.length && x + 1 + xi >= 0){
                    if (battlefield[x + 1 + xi][y + yi]!=0){
                        return false;
                    }
                }
                if (x - 1 + xi < battlefield.length && x - 1 + xi >= 0){
                    if (battlefield[x - 1 + xi][y + yi]!=0){
                        return false;
                    }
                }
                if (y + 1 + yi < battlefield.length && y + 1 + yi >= 0){
                    if (battlefield[x + xi][y + 1 + yi]!=0){
                        return false;
                    }
                }
                if (y - 1 + yi < battlefield.length && y - 1 + yi >= 0){
                    if (battlefield[x + xi][y - 1 + yi]!=0){
                        return false;
                    }
                }
            }
            deck--;
        }
        return true;
    }

    public static void clearScreen(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}