package CaseStudy;

public class PuzzleGame extends Game {
    public PuzzleGame(String title, double price, int playerCount, String platform) {
        super(title, price, playerCount, platform);
    }

    @Override
    public String getGenre() {
        return "Puzzle";
    }
}
