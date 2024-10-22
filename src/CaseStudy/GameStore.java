package CaseStudy;

import java.io.*;
import java.util.*;

public class GameStore {
    private List<Game> games = new ArrayList<>();
    private static final String FILE_PATH = "games.csv";

    public void loadGames() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                Game game = createGame(fields);
                if (game != null) {
                    games.add(game);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Game createGame(String[] fields) {
        String genre = fields[0];
        String title = fields[1];
        double price = Double.parseDouble(fields[2]);
        int playerCount = Integer.parseInt(fields[3]);
        String platform = fields[4];

        switch (genre) {
            case "Action":
                return new ActionGame(title, price, playerCount, platform);
            case "Puzzle":
                return new PuzzleGame(title, price, playerCount, platform);
            case "Shooter":
                return new ShooterGame(title, price, playerCount, platform);
            case "Open World":
                return new OpenWorldGame(title, price, playerCount, platform);
            default:
                return null;
        }
    }

    public void saveGames() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Game game : games) {
                bw.write(game.getGenre() + "," + game.getTitle() + "," + game.getPrice() + "," +
                        game.getPlayerCount() + "," + game.getPlatform());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addGame(Game game) {
        games.add(game);
        saveGames();
    }

    public void removeGame(String title) {
        games.removeIf(game -> game.getTitle().equals(title));
        saveGames();
    }

    public void listGames() {
        if (games.isEmpty()) {
            System.out.println("No games available.");
            return;
        }
        for (Game game : games) {
            System.out.println(game);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameStore store = new GameStore();
        store.loadGames();

        System.out.println("Welcome to the Game Store!");
        System.out.print("Enter your role (admin/user): ");
        String role = scanner.nextLine().toLowerCase();

        if (role.equals("admin")) {
            Admin admin = new Admin("Admin");
            String command;
            do {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. List Games");
                System.out.println("2. Add Game");
                System.out.println("3. Remove Game");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                command = scanner.nextLine();

                switch (command) {
                    case "1":
                        store.listGames();
                        break;
                    case "2":
                        System.out.print("Enter game title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter player count: ");
                        int playerCount = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter platform (PC/Console): ");
                        String platform = scanner.nextLine();
                        System.out.print("Enter genre (Action/Puzzle/Shooter/Open World): ");
                        String genre = scanner.nextLine();

                        Game newGame = null;
                        switch (genre) {
                            case "Action":
                                newGame = new ActionGame(title, price, playerCount, platform);
                                break;
                            case "Puzzle":
                                newGame = new PuzzleGame(title, price, playerCount, platform);
                                break;
                            case "Shooter":
                                newGame = new ShooterGame(title, price, playerCount, platform);
                                break;
                            case "Open World":
                                newGame = new OpenWorldGame(title, price, playerCount, platform);
                                break;
                        }
                        if (newGame != null) {
                            store.addGame(newGame);
                            System.out.println("Game added successfully!");
                        } else {
                            System.out.println("Invalid genre.");
                        }
                        break;
                    case "3":
                        System.out.print("Enter game title to remove: ");
                        String titleToRemove = scanner.nextLine();
                        store.removeGame(titleToRemove);
                        System.out.println("Game removed successfully!");
                        break;
                    case "4":
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } while (!command.equals("4"));

        } else if (role.equals("user")) {
            User user = new User("User");
            System.out.println("User Menu:");
            store.listGames();
        } else {
            System.out.println("Invalid role.");
        }

        scanner.close();
    }
}
