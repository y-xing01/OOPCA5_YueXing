package OOPCA5.Part1;

import java.util.Objects;

public class Player{
    private int playerId;
    private int playerWRank;
    private String playerName;
    private int playerAge;
    private float playerHeight;
    private int careerWin;

    public Player(int playerWRank, String playerName, int playerAge, float playerHeight, int careerWin){
        this.playerWRank = playerWRank;
        this.playerName = playerName;
        this.playerAge = playerAge;
        this.playerHeight = playerHeight;
        this.careerWin = careerWin;
    }

    public Player(int playerId ,int playerWRank, String playerName, int playerAge, float playerHeight, int careerWin){
        this.playerId = playerId;
        this.playerWRank = playerWRank;
        this.playerName = playerName;
        this.playerAge = playerAge;
        this.playerHeight = playerHeight;
        this.careerWin = careerWin;
    }
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerWRank() {
        return playerWRank;
    }

    public void setPlayerWRank(int playerWRank) {
        this.playerWRank = playerWRank;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    public float getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    }

    public int getCareerWin(){ return careerWin; }

    public void setCareerWin(int careerWin){ this.careerWin = careerWin; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerWRank == player.playerWRank && playerAge == player.playerAge && Float.compare(player.playerHeight, playerHeight) == 0 && careerWin == player.careerWin && Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerWRank, playerName, playerAge, playerHeight, careerWin);
    }

    @Override
    public String toString() {
        return "Player ID : " + playerId +
                ", Player World Rank : " + playerWRank +
                ", Player Name : " + playerName  +
                ", Player Age : " + playerAge +
                ", Player Height : " + playerHeight;
    }
}
