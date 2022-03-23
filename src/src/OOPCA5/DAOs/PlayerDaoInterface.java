package OOPCA5.DAOs;

import OOPCA5.Exceptions.DaoException;
import OOPCA5.Part1.Player;

import java.util.ArrayList;

public interface PlayerDaoInterface {
    public ArrayList <Player> findAllPlayers() throws DaoException;

}
