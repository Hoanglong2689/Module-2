package CaseStudy;

public class OpenWorldGame extends Game {
    public OpenWorldGame(String title, double price, int playerCount, String platform) {
        super(title, price, playerCount, platform);
    }

    @Override
    public String getGenre() {
        return "Open World";
    }
}
