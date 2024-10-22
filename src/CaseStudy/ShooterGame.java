package CaseStudy;

public class ShooterGame extends Game {
    public ShooterGame(String title, double price, int playerCount, String platform) {
        super(title, price, playerCount, platform);
    }

    @Override
    public String getGenre() {
        return "Shooter";
    }
}
