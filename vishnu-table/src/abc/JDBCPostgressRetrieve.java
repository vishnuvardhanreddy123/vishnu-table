package abc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JDBCPostgressRetrieve {

    private static String Database = "jdbc:postgres://yjblphpkfbbutt:2b8a4325314e6155bc2f3cc2d1076e74f43dd54c30ee1d5c7c3ec1c82090abdd@ec2-23-21-201-12.compute-1.amazonaws.com:5432/d632ej6u0k97cd";
    private static String user = "yjblphpkfbbutt";
    private static String password = "2b8a4325314e6155bc2f3cc2d1076e74f43dd54c30ee1d5c7c3ec1c82090abdd";

    public static String getDatabase() {
        return Database;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public void retrieve(int limit) {

        Statement stmt = null;
        Connection connection = null;
        ResultSet rs = null; 
        int i = 0;
        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
        }

        try {

            connection = DriverManager.getConnection(
                    JDBCPostgressRetrieve.getDatabase(),
                    JDBCPostgressRetrieve.getUser(),
                    JDBCPostgressRetrieve.getPassword());

            if (connection == null) {
                System.out.println("Failed to make connection!");
            } else {

                DateFormat dateFormat = new SimpleDateFormat(
                        "yyyy/MM/dd HH:mm:ss");
                Date date = new Date();

                // Creating Statement for query execution
                stmt = connection.createStatement();
                // creating Query String                
                String query = "select * from salesforce.account";

                // excecuting query
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    // Didplaying data of tables
                    System.out.println("Name : " + rs.getString("name"));
                  //  System.out.println("email : " + rs.getString("email"));
                    System.out.println("mobile_number : " + rs.getString("phone"));
                   // System.out.println("key_skills : " + rs.getString("key_skills"));
                 //   System.out.println("current_employer : " + rs.getString("current_employer"));
                }
            }

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
                stmt.close();
                connection.close();
            }catch(SQLException e) {
                System.err.println("SQLException while closing connection. "+ e.getMessage());
            }           
        }
    }

    public static void main(String args []) {
        JDBCPostgressRetrieve ret = new JDBCPostgressRetrieve();
        ret.retrieve(10);
    }
}
