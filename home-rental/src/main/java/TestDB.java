
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Romain <ro.foncier@gmail.com>
 */
public class TestDB {

    public static void main(String[] args) {
        loadJDBC();
        getConnection();
    }

    public static void loadJDBC() {
        try {
            System.out.println("Loading driver...");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find the driver in the classpath!", e);
        }
    }

    public static void getConnection() {
        String url = "jdbc:mysql://mysql1.alwaysdata.com/airboy_hr_db";
        String username = "airboy_hrp";
        String password = "gould1in8";
        Connection connection = null;
        try {
            System.out.println("Connecting database...");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");

            Statement stmt = connection.createStatement();
           System.out.println(formatInJSON(stmt.executeQuery("SELECT * FROM `user`")));
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        } finally {
            System.out.println("Closing the connection.");
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }
    
    private static String formatInJSON(ResultSet res) {
        String result = null;
        try {
            ResultSetMetaData struc = res.getMetaData();
            while (res.next()) {
                result = "{";
                for (int i = 1; i <= struc.getColumnCount(); i++) {
                    String type = struc.getColumnTypeName(i);
                    String columnName = struc.getColumnName(i);
                    if (!type.equals("null")) {
                        //System.out.println("Name : "+columnName+" - Type : "+type);
                        result += "\""+struc.getColumnName(i)+"\": ";
                        result += "\""+res.getString(columnName) +"\"";
                        result += (i < struc.getColumnCount()) ? ", " : "";
                    }
                }
                result += (res.next()) ? "}, " : "}";
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return result;
    }
}