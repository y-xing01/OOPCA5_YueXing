import java.util.Objects;

public class Player {
    private int playerID;
    private String playerName;
    private int playerAge;
    private float playerHeight;
    private float playerWeight;

    public Player(int playerID, String playerName, int playerAge, float playerHeight, float playerWeight){
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerAge = playerAge;
        this.playerHeight = playerHeight;
        this.playerWeight = playerWeight;
    }

    public Player(){
        this.playerID = 0;
        this.playerName = "";
        this.playerAge = 0;
        this.playerHeight = 0;
        this.playerWeight = 0;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
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
        return playerID == player.playerID && playerAge == player.playerAge && Float.compare(player.playerHeight, playerHeight) == 0 && Float.compare(player.playerWeight, playerWeight) == 0 && Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerID, playerName, playerAge, playerHeight, playerWeight);
    }

    @Override
    public String toString() {
        return "playerId : " + playerID +
                ", playerName : " + playerName  +
                ", playerAge : " + playerAge +
                ", playerHeight : " + playerHeight +
                " playerWeight : " + playerWeight+ "\n";
    }
}
