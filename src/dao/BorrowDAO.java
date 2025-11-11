package dao;
import utils.DBConnection;
import java.sql.*;

public class BorrowDAO {
    public static boolean borrow(int userId, int bookId) {
        String check = "SELECT quantity FROM books WHERE id=?";
        String update = "UPDATE books SET quantity=quantity-1 WHERE id=? AND quantity>0";
        String insert = "INSERT INTO borrows (user_id, book_id) VALUES (?, ?)";
        try (Connection c = DBConnection.getConnection()) {
            c.setAutoCommit(false);
            try (PreparedStatement psCheck = c.prepareStatement(check)) {
                psCheck.setInt(1, bookId);
                try (ResultSet rs = psCheck.executeQuery()) {
                    if (!rs.next() || rs.getInt("quantity") <= 0) {
                        c.rollback();
                        return false;
                    }
                }
            }
            try (PreparedStatement psUp = c.prepareStatement(update)) {
                psUp.setInt(1, bookId);
                if (psUp.executeUpdate() == 0) { c.rollback(); return false; }
            }
            try (PreparedStatement psIns = c.prepareStatement(insert)) {
                psIns.setInt(1, userId);
                psIns.setInt(2, bookId);
                psIns.executeUpdate();
            }
            c.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
