package OOPCA5.DAOs;

import OOPCA5.Part1.Player;
import OOPCA5.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlPlayerDao extends MySqlDao implements PlayerDaoInterface{
    @Override
    public ArrayList<Player> findAllPlayers() throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        ArrayList<Player> playerList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYER";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int playerWorldRank = resultSet.getInt("PLAYER_WORLD_RANK");
                String playerName = resultSet.getString("PLAYER_NAME");
                int playerAge = resultSet.getInt("PLAYER_AGE");
                float playerHeight = resultSet.getFloat("PLAYER_HEIGHT");
                int playerCareerWin = resultSet.getInt("PLAYER_CAREER_WIN");
                Player p = new Player(playerWorldRank, playerName, playerAge, playerHeight, playerCareerWin);
                playerList.add(p);
            }
        } catch (SQLException e)
        {
            throw new DaoException("Find All Player : " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("Find All Players : " + e.getMessage());
            }
        }
        return playerList;     // may be empty
    }
}
