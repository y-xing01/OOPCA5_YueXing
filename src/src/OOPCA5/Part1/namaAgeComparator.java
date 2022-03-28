package OOPCA5.Part1;

import java.util.Comparator;

public class namaAgeComparator implements Comparator<Player>{
    @Override
    public int compare(Player p1, Player p2)
    {
        boolean pSameName = p1.getPlayerName().equalsIgnoreCase(p2.getPlayerName());

        if(pSameName) {
            return p1.getPlayerAge() - p2.getPlayerAge();
        }
        else {
            return p1.getPlayerName().compareToIgnoreCase(
                    p2.getPlayerName());
        }
    }
}
