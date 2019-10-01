package db.dbTesting;
/**
* Temporal file to test DB functions
*/
import db.sendInfo;
import db.sqlMethods;
import java.sql.SQLException;

public class tester {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //get inputs then generate sql -->
        String sql = sqlMethods.clientNew(1000, "Mario", "mario@miners.utep.edu", 915123456);
       // sendInfo.drive(sql);
        System.out.print( sql );


    }
}
