import java.util.Objects;

public class Player {
    private int playerWRank;
    private String playerName;
    private int playerAge;
    private float playerHeight;
    private float playerWeight;

    public Player(int playerID, String playerName, int playerAge, float playerHeight, float playerWeight){
        this.playerWRank = playerID;
        this.playerName = playerName;
        this.playerAge = playerAge;
        this.playerHeight = playerHeight;
        this.playerWeight = playerWeight;
    }

    public Player(){
        this.playerWRank = 0;
        this.playerName = "";
        this.playerAge = 0;
        this.playerHeight = 0;
        this.playerWeight = 0;
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

    public float getPlayerWeight() {
        return playerWeight;
    }

    public void setPlayerWeight(int playerWeight) {
        this.playerWeight = playerWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerWRank == player.playerWRank && playerAge == player.playerAge && Float.compare(player.playerHeight, playerHeight) == 0 && Float.compare(player.playerWeight, playerWeight) == 0 && Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerWRank, playerName, playerAge, playerHeight, playerWeight);
    }

    @Override
    public String toString() {
        return "Player World Rank : " + playerWRank +
                ", Player Name : " + playerName  +
                ", Player Age : " + playerAge +
                ", Player Height : " + playerHeight +
                ", Player Weight : " + playerWeight;
    }
}
