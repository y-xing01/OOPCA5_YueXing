package OOPCA5.Part1;

import java.util.Objects;

public class Player implements Comparable<Player>{
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

    public Player(){
        this.playerWRank = 0;
        this.playerName = "";
        this.playerAge = 0;
        this.playerHeight = 0;
        this.careerWin = 0;
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
        return "Player World Rank : " + playerWRank +
                ", Player Name : " + playerName  +
                ", Player Age : " + playerAge +
                ", Player Height : " + playerHeight;
    }

    @Override
    public int compareTo(Player o)
    {
        boolean pSameName = this.getPlayerName().equalsIgnoreCase(o.getPlayerName());

        if(pSameName) {
            return this.getPlayerAge() - o.getPlayerAge();
        }
        else {
            return this.getPlayerName().compareToIgnoreCase(
                    o.getPlayerName());
        }
    }
}
