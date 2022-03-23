package OOPCA5.DAOs;

import OOPCA5.Part1.Player;
import OOPCA5.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYER";
            ps = connection.prepareStatement(query);

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
        return playerList;
    }

    @Override
    public ArrayList<Player> findPlayerByAge(int player_age1, int player_age2) throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        ArrayList<Player> playerList = new ArrayList<>();

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYER WHERE PLAYER_AGE BETWEEN ? AND ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,  player_age1);
            ps.setInt(2,  player_age2);

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
            throw new DaoException("Find all Player Age between " + e.getMessage());
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
                throw new DaoException("Find all Player Age between  " + e.getMessage());
            }
        }
        return playerList;
    }
}
