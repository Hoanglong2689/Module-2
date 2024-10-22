package CaseStudy;

public class ActionGame extends Game {
    public ActionGame(String title, double price, int playerCount, String platform) {
        super(title, price, playerCount, platform);
    }

    @Override
    public String getGenre() {
        return "Action";
    }
}
