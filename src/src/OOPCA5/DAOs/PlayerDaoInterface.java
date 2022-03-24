package OOPCA5.DAOs;

import OOPCA5.Exceptions.DaoException;
import OOPCA5.Part1.Player;

import java.util.ArrayList;

public interface PlayerDaoInterface {
    public ArrayList <Player> findAllPlayers() throws DaoException;

    public ArrayList <Player> findPlayerByAge(int player_age1, int player_age2) throws  DaoException;

    public boolean deletePlayerById(int player_id) throws  DaoException;

}
