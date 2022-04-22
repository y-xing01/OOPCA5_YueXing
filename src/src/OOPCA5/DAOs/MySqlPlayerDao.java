package OOPCA5.DAOs;

import OOPCA5.Part1.Player;
import OOPCA5.Exceptions.DaoException;
import OOPCA5.Part1.SortType;
import OOPCA5.Part1.ageComparator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
                int playerId = resultSet.getInt("PLAYER_ID");
                int playerWorldRank = resultSet.getInt("PLAYER_WORLD_RANK");
                String playerName = resultSet.getString("PLAYER_NAME");
                int playerAge = resultSet.getInt("PLAYER_AGE");
                float playerHeight = resultSet.getFloat("PLAYER_HEIGHT");
                int playerCareerWin = resultSet.getInt("PLAYER_CAREER_WIN");
                Player p = new Player(playerId, playerWorldRank, playerName, playerAge, playerHeight, playerCareerWin);
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
    public ArrayList<Player> findPlayerByWorldRanking(int player_world_ranking) throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        ArrayList<Player> playerList = new ArrayList<>();

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYER WHERE PLAYER_WORLD_RANK = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,  player_world_ranking);

            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int playerId = resultSet.getInt("PLAYER_ID");
                int playerWorldRank = resultSet.getInt("PLAYER_WORLD_RANK");
                String playerName = resultSet.getString("PLAYER_NAME");
                int playerAge = resultSet.getInt("PLAYER_AGE");
                float playerHeight = resultSet.getFloat("PLAYER_HEIGHT");
                int playerCareerWin = resultSet.getInt("PLAYER_CAREER_WIN");
                Player p = new Player(playerId, playerWorldRank, playerName, playerAge, playerHeight, playerCareerWin);
                playerList.add(p);
            }
        } catch (SQLException e)
        {
            throw new DaoException("Find Player by WORLD RANKING : " + e.getMessage());
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
                throw new DaoException("Find player by WORLD RANKING :  " + e.getMessage());
            }
        }
        return playerList;
    }

    @Override
    public ArrayList<Player> findPlayerById(int player_id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        ArrayList<Player> playerList = new ArrayList<>();

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYER WHERE PLAYER_ID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,  player_id);

            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int playerId = resultSet.getInt("PLAYER_ID");
                int playerWorldRank = resultSet.getInt("PLAYER_WORLD_RANK");
                String playerName = resultSet.getString("PLAYER_NAME");
                int playerAge = resultSet.getInt("PLAYER_AGE");
                float playerHeight = resultSet.getFloat("PLAYER_HEIGHT");
                int playerCareerWin = resultSet.getInt("PLAYER_CAREER_WIN");
                Player p = new Player(playerId, playerWorldRank, playerName, playerAge, playerHeight, playerCareerWin);
                playerList.add(p);
            }
        } catch (SQLException e)
        {
            throw new DaoException("Find Player by WORLD RANKING : " + e.getMessage());
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
                throw new DaoException("Find player by WORLD RANKING :  " + e.getMessage());
            }
        }
        return playerList;
    }

    @Override
    public boolean deletePlayerById(int player_id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean check = false;

        try
        {
            connection = this.getConnection();

            String query = "DELETE FROM PLAYER WHERE PLAYER_ID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, player_id);
            if (ps.executeUpdate() > 0){
                check = true;
            }
        } catch (SQLException e)
        {
            throw new DaoException("DELETE player by ID : " + e.getMessage());
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
                throw new DaoException("DELETE player by ID :  " + e.getMessage());
            }
        }
        return check;
    }

    @Override
    public Player addPlayer(Player player) throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try
        {
            connection = this.getConnection();

            String query = "INSERT INTO PLAYER VALUES(NULL, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, player.getPlayerWRank());
            ps.setString(2, player.getPlayerName());
            ps.setInt(3, player.getPlayerAge());
            ps.setFloat(4, player.getPlayerHeight());
            ps.setInt(5, player.getCareerWin());
            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new DaoException("Add Player : " + e.getMessage());
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
                throw new DaoException("Add Player : " + e.getMessage());
            }
        }
        return player;
    }

    @Override
    public ArrayList<Player> findPlayerByFilter(Comparator<Player> ageComparator, int player_career_won) throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        ArrayList<Player> playerList = new ArrayList<>();

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYER WHERE PLAYER_CAREER_WIN > ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,  player_career_won);

            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int playerId = resultSet.getInt("PLAYER_ID");
                int playerWorldRank = resultSet.getInt("PLAYER_WORLD_RANK");
                String playerName = resultSet.getString("PLAYER_NAME");
                int playerAge = resultSet.getInt("PLAYER_AGE");
                float playerHeight = resultSet.getFloat("PLAYER_HEIGHT");
                int playerCareerWin = resultSet.getInt("PLAYER_CAREER_WIN");
                Player p = new Player(playerId, playerWorldRank, playerName, playerAge, playerHeight, playerCareerWin);
                playerList.add(p);
            }
        } catch (SQLException e)
        {
            throw new DaoException("Find All Player by FILTER(AGE) : " + e.getMessage());
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
                throw new DaoException("Find All Player by FILTER(AGE) : " + e.getMessage());
            }
        }
        Collections.sort(playerList, new ageComparator(SortType.Ascending));
        return playerList;
    }

    @Override
    public String findAllPlayersJson() throws DaoException
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
                int playerId = resultSet.getInt("PLAYER_ID");
                int playerWorldRank = resultSet.getInt("PLAYER_WORLD_RANK");
                String playerName = resultSet.getString("PLAYER_NAME");
                int playerAge = resultSet.getInt("PLAYER_AGE");
                float playerHeight = resultSet.getFloat("PLAYER_HEIGHT");
                int playerCareerWin = resultSet.getInt("PLAYER_CAREER_WIN");
                Player p = new Player(playerId, playerWorldRank, playerName, playerAge, playerHeight, playerCareerWin);
                playerList.add(p);
            }
        } catch (SQLException e)
        {
            throw new DaoException("Find All Player by JSON : " + e.getMessage());
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
                throw new DaoException("Find All Player by JSON : " + e.getMessage());
            }
        }
        Gson gsonParser = new GsonBuilder().setPrettyPrinting().create();;
        return gsonParser.toJson(playerList);
    }

    @Override
    public String findPlayerByIdJson(int player_id) throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        ArrayList<Player> playerList = new ArrayList<>();

        try
        {
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYER WHERE PLAYER_ID = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,  player_id);

            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int playerId = resultSet.getInt("PLAYER_ID");
                int playerWorldRank = resultSet.getInt("PLAYER_WORLD_RANK");
                String playerName = resultSet.getString("PLAYER_NAME");
                int playerAge = resultSet.getInt("PLAYER_AGE");
                float playerHeight = resultSet.getFloat("PLAYER_HEIGHT");
                int playerCareerWin = resultSet.getInt("PLAYER_CAREER_WIN");
                Player p = new Player(playerId, playerWorldRank, playerName, playerAge, playerHeight, playerCareerWin);
                playerList.add(p);
            }
        } catch (SQLException e)
        {
            throw new DaoException("Find player by ID JSON " + e.getMessage());
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
                throw new DaoException("Find player by ID JSON " + e.getMessage());
            }
        }
        Gson gsonParser = new GsonBuilder().setPrettyPrinting().create();
        return gsonParser.toJson(playerList);
    }

    @Override
    public Player updatePlayer(Player player) throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try
        {
            connection = this.getConnection();

            String query = "UPDATE PLAYER VALUES(NULL, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1, player.getPlayerWRank());
            ps.setString(2, player.getPlayerName());
            ps.setInt(3, player.getPlayerAge());
            ps.setFloat(4, player.getPlayerHeight());
            ps.setInt(5, player.getCareerWin());
            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new DaoException("Update Player : " + e.getMessage());
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
                throw new DaoException("Update Player : " + e.getMessage());
            }
        }
        return player;
    }
}
