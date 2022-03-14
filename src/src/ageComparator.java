import java.util.Comparator;

public class ageComparator implements Comparator<Player> {
    private SortType sortType;

    public ageComparator(SortType sortType){
        this.sortType = sortType;
    }

    @Override
    public int compare(Player p1, Player p2){
        int direction = sortType.getValue();
        return direction * (p1.getPlayerAge() - p2.getPlayerAge());
    }
}
