package db;
/**
* Temporal file to test DB functions
*/
import java.sql.SQLException;

public class tester {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //get inputs then generate sql -->
        String sql = sqlMethods.clientNew(1000, "Mario", "mario@miners.utep.edu", 915123456);
        sendInfo.drive(sql);


    }
}
