package com.chess.database.DAO;

import com.chess.database.Class.ChessMatch;
import com.chess.database.Utils.DateTimeUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChessMatchDAO {

    // THÊM VÁN ĐẤU MỚI
    // matchID do bên csdl tự thêm vào!
    public static boolean insertMatch(ChessMatch match) {
        String sql = "INSERT INTO chess_match (white_player_id, black_player_id, result, start_time, end_time) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (
            Connection conn = SQLiteConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, match.getWhitePalyerID());
            ps.setInt(2, match.getBlackPlayerID());
            ps.setString(3, match.getResult());
            ps.setString(4, DateTimeUtil.toSQLiteString(match.getStartTime())); // LocalDateTime -> TEXT
            ps.setString(5, DateTimeUtil.toSQLiteString(match.getEndTime()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Lấy mọi match
    public static ArrayList<ChessMatch> getAllMatch() {
        ArrayList<ChessMatch> list = new ArrayList<>();
        String sql = "SELECT * FROM chess_match";

        try (
            Connection conn = SQLiteConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                list.add(mapResultSetToMatch(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    
    // CHỌN VÁN ĐẤU THEO ID
    public static ChessMatch getMatchByID(int id) {
        String sql = "SELECT * FROM chess_match WHERE match_id = ?";

        try (
            Connection conn = SQLiteConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToMatch(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // DELETE
    public static boolean deleteMatch(int id) {
        String sql = "DELETE FROM chess_match WHERE match_id = ?";

        try (
            Connection conn = SQLiteConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // HELPER
    private static ChessMatch mapResultSetToMatch(ResultSet rs) throws SQLException {

        String start = rs.getString("start_time");
        String end = rs.getString("end_time");

        // nhớ chuyển đổi từ TEXT bên SQLite về thành LocaDateTime bên java!
        LocalDateTime startTime = (start != null) ? DateTimeUtil.fromSQLiteString(start) : null;
        LocalDateTime endTime = (end != null) ? DateTimeUtil.fromSQLiteString(end) : null;

        return new ChessMatch(
                rs.getInt("match_id"),
                rs.getInt("white_player_id"),
                rs.getInt("black_player_id"),
                rs.getString("result"),
                startTime,
                endTime
        );
    }
}