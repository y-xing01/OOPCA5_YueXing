package OOPCA5.DAOs;

import OOPCA5.Exceptions.DaoException;
import OOPCA5.Part1.Player;

import java.util.ArrayList;
import java.util.Comparator;

public interface PlayerDaoInterface{
    public ArrayList <Player> findAllPlayers() throws DaoException;

    public ArrayList <Player> findPlayerByWorldRanking(int player_world_rank) throws DaoException;

    public boolean deletePlayerById(int player_id) throws DaoException;

    public Player addPlayer(Player player) throws  DaoException;

    public ArrayList <Player> findPlayerByFilter(Comparator<Player> ageComparator, int player_career_won) throws DaoException;

    public String findAllPlayersJson() throws DaoException;

    public String findPlayerByIdJson(int player_id) throws DaoException;
}
