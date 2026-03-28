package com.chess.database.DAO;

import com.chess.database.Class.ChessMatch;
import com.chess.database.Class.User;
import com.chess.database.Class.UserStats;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
/*
Cấu trúc thư mục hiện tại
Database
----DAO
        ----SQLiteConnection
----db
        ----ltmchess.db
----Lop
*/
public class SQLiteConnection {
    // Chú ý: Cẩn thận với đường dẫn do phương thức .getAbsolutePath có thể bỏ qua root project
    private static final String path = new File("src\\Database\\db\\ltmchess.db").getAbsolutePath();
    
    public static Connection getConnection() {
        // path có thể thay đổi dựa trên các cấu trúc thư mục khác
        
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:" + path);
            return con;
        } catch (SQLException e) {
            System.out.println("Lỗi khi kết nối tới cơ sở dữ liệu: ");
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    
    //################################
    public static void main(String[] args) {
        System.out.println(UserStatsDAO.getAllUserStats().toString() + "\n\n");
        UserStats stats = new UserStats(
            1,      // userID (make sure this user exists in User table!)
            8,
            5,      // wins
            3,      // losses
            2,      // draws
            0.5,    // winRate
            3,      // winningStreak
            1,      // losingStreak
            9999   // elo
        );
        
        if (UserStatsDAO.upsertUserStats(stats)) {
            System.out.println("Insert success!");
            System.out.println(UserStatsDAO.getAllUserStats().toString());
        } else {
            System.out.println("Insert failed!");
        }
    }
}
