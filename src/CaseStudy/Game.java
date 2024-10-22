package CaseStudy;

import java.io.Serializable;

public abstract class Game implements Serializable {
    private String title;
    private double price;
    private int playerCount;
    private String platform;

    public Game(String title, double price, int playerCount, String platform) {
        this.title = title;
        this.price = price;
        this.playerCount = playerCount;
        this.platform = platform;
    }

    public abstract String getGenre();

    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public int getPlayerCount() { return playerCount; }
    public String getPlatform() { return platform; }

    @Override
    public String toString() {
        return "Title: " + title + ", Price: " + price + ", Players: " + playerCount + ", Platform: " + platform;
    }
}
