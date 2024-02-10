package Verification;
import java.sql.*;

public class SQLiteJDBC {
    private static Connection connect() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public static void insertSignUp(String username, String password, int status) {
        //1-Admin
        //2-User
        if(status==1) {
            String sql = "INSERT INTO ADMINS(username,password) VALUES(?,?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (status==2) {
            String sql = "INSERT INTO CLIENT(username,password) VALUES(?,?)";
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static String search(String username ,String field, int status){
        if (status==1) {
            String sql = "SELECT * FROM ADMINS WHERE USERNAME=?;";
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                ResultSet resultSet = pstmt.executeQuery();
                String password = resultSet.getString(field);
                return password;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (status==2) {
            String sql = "SELECT * FROM CLIENT WHERE USERNAME=?;";
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                ResultSet resultSet = pstmt.executeQuery();
                String password = resultSet.getString("PASSWORD");
                return password;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return "";
    }
    public static void createNewTable() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:database.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS CLIENT ("
                + "	id integer PRIMARY KEY,"
                + "	USERNAME TEXT NOT NULL,"
                + "	PASSWORD TEXT NOT NULL"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
