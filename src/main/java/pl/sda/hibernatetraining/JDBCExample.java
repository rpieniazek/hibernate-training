package pl.sda.hibernatetraining;

import java.sql.*;

public class JDBCExample {
    static final String DB_URL = "jdbc:mysql://localhost:3307/LIBRARY";

    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        System.out.println("Connecting to database...");
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement()
        ) {
            System.out.println("Creating statement...");
            String sql = "SELECT id, title, book_year FROM book";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Extracting results ...");

            while (rs.next()) {
                extractFromResultDate(rs);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Goodbye!");
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