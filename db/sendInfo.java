package db;
import java.sql.*;
/**
 * Connects to DB
 * Sends a query and updates the DB
 *
 */

public class sendInfo {
    public static boolean drive(String sql) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // TODO Auto-generated method stub
        String url = "jdbc:mysql://172.20.43.226:3306/TicketTraker?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "user";
        String password = "password";
        boolean pass = false;
        Connection myConn = null;
        Statement myStmt = null;


        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection(url, user, password);

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myStmt.executeUpdate(sql);

            System.out.println("DB Updated");
            pass = true;
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
        return pass;
    }



}
