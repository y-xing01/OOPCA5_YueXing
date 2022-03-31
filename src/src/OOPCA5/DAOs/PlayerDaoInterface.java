package OOPCA5.DAOs;

import OOPCA5.Exceptions.DaoException;
import OOPCA5.Part1.Player;
import OOPCA5.Part1.SortType;
import OOPCA5.Part1.ageComparator;

import java.util.ArrayList;
import java.util.Comparator;

public interface PlayerDaoInterface{
    public ArrayList <Player> findAllPlayers() throws DaoException;

    public ArrayList <Player> findPlayerByAge(int player_age1, int player_age2) throws  DaoException;

    public boolean deletePlayerById(int player_id) throws  DaoException;

    public Player addPlayer(Player player) throws  DaoException;

    public ArrayList <Player> findPlayerByFilter(Comparator<Player> ageComparator) throws  DaoException;

    public String findAllPlayersJson() throws  DaoException;

    public String findPlayerByIdJson() throws  DaoException;
}
