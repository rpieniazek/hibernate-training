package pl.sda.hibernatetraining;

import java.sql.*;

public class JDBCExample {
    static final String DB_URL = "jdbc:mysql://localhost:3306/LIBRARY";

    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        String insertSql = "INSERT INTO  book ( title, book_year) VALUES  (?,?)";

        System.out.println("Connecting to database...");
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                PreparedStatement preparedStatemen = conn.prepareStatement(insertSql)

        ) {
            insertMockBook(preparedStatemen);
            selectAllBooks(stmt);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Goodbye!");
    }

    private static void selectAllBooks(Statement stmt) throws SQLException {
        System.out.println("Selecting data ...");
        String selectSql = "SELECT id, title, book_year FROM book";
        ResultSet rs = stmt.executeQuery(selectSql);
        System.out.println("Extracting results ...");
        while (rs.next()) {
            extractFromResultDate(rs);
        }
        rs.close();
    }

    private static void insertMockBook(PreparedStatement preparedStatemen) throws SQLException {
        System.out.println("Inserting data");
        preparedStatemen.setString(1, "Refactoring 3");
        preparedStatemen.setInt(2, 2011);
        preparedStatemen.executeUpdate();
    }

    private static void extractFromResultDate(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String bookYear = rs.getString("book_year");

        System.out.print("ID: " + id);
        System.out.print(", Title: " + title);
        System.out.println(", bookYear: " + bookYear);
    }
}