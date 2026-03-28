package com.chess.database.DAO;

import com.chess.database.Class.UserStats;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserStatsDAO {

    // Thêm thành tích 
    public static boolean insertUserStats(UserStats stats) {
        String sql = "INSERT INTO user_stats " +
                "(user_id, wins, total_games, losses, draws, win_rate, winning_streak, losing_streak, elo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = SQLiteConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, stats.getUserID());
            ps.setInt(2, stats.getTotalGames());
            ps.setInt(3, stats.getWins());
            ps.setInt(4, stats.getLosses());
            ps.setInt(5, stats.getDraws());
            ps.setDouble(6, stats.getWinRate());
            ps.setInt(7, stats.getWinningStreak());
            ps.setInt(8, stats.getLosingStreak());
            ps.setInt(9, stats.getElo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // READ BY USER ID
    public static UserStats getUserStatsByUserID(long userID) {
        String sql = "SELECT * FROM user_stats WHERE user_id = ?";

        try (
            Connection conn = SQLiteConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToUserStats(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // READ ALL
    public static ArrayList<UserStats> getAllUserStats() {
        ArrayList<UserStats> list = new ArrayList<>();
        String sql = "SELECT * FROM user_stats";

        try (
            Connection conn = SQLiteConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                list.add(mapResultSetToUserStats(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // UPDATE
    public static boolean updateUserStats(UserStats stats) {
        String sql = "UPDATE user_stats SET " +
                "wins=?, total_games =?, losses=?, draws=?, win_rate=?, winning_streak=?, losing_streak=?, elo=? " +
                "WHERE user_id=?";

        try (
            Connection conn = SQLiteConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            
            ps.setInt(1, stats.getTotalGames());
            ps.setInt(2, stats.getWins());
            ps.setInt(3, stats.getLosses());
            ps.setInt(4, stats.getDraws());
            ps.setDouble(5, stats.getWinRate());
            ps.setInt(6, stats.getWinningStreak());
            ps.setInt(7, stats.getLosingStreak());
            ps.setInt(8, stats.getElo());
            ps.setLong(9, stats.getUserID());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    //UPSERT
    // Phương thức này dùng tốt hơn update thông thường -> nên dùng cái này
    public static boolean upsertUserStats(UserStats stats) {
        String sql = "INSERT INTO user_stats " +
                "(user_id, wins, total_games, losses, draws, win_rate, winning_streak, losing_streak, elo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON CONFLICT(user_id) DO UPDATE SET " +
                "total_games = excluded.total_games," +
                "wins = excluded.wins, " +
                "losses = excluded.losses, " +
                "draws = excluded.draws, " +
                "win_rate = excluded.win_rate, " +
                "winning_streak = excluded.winning_streak, " +
                "losing_streak = excluded.losing_streak, " +
                "elo = excluded.elo";

        try (
            Connection conn = SQLiteConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, stats.getUserID());
            ps.setInt(2, stats.getTotalGames());
            ps.setInt(3, stats.getWins());
            ps.setInt(4, stats.getLosses());
            ps.setInt(5, stats.getDraws());
            ps.setDouble(6, stats.getWinRate());
            ps.setInt(7, stats.getWinningStreak());
            ps.setInt(8, stats.getLosingStreak());
            ps.setInt(9, stats.getElo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE
    public static boolean deleteUserStats(long userID) {
        String sql = "DELETE FROM user_stats WHERE user_id=?";

        try (
            Connection conn = SQLiteConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, userID);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // HELPER
    private static UserStats mapResultSetToUserStats(ResultSet rs) throws SQLException {
        return new UserStats(
                rs.getInt("user_id"),
                rs.getInt("total_games"),
                rs.getInt("wins"),
                rs.getInt("losses"),
                rs.getInt("draws"),
                rs.getDouble("win_rate"),
                rs.getInt("winning_streak"),
                rs.getInt("losing_streak"),
                rs.getInt("elo")
        );
    }
}