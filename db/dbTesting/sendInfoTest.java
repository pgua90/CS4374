
package db.dbTesting;

import db.sendInfo;
import db.sqlMethods;
import org.junit.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test for DB functions
 * */

class sendInfoTest {

    //Tests if new client is created in DB
    @Test
    public void sendTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String sql = sqlMethods.clientNew(1000, "Albert", "albert@miners.utep.edu", 915123456);
        assertTrue(sendInfo.drive(sql));
    }
    //Tests if DB Fails
    @Test
    public void failTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String sql = "Non readable data";
        assertFalse( sendInfo.drive(sql));
    }

    //Checks if the queries are being created correctly, expects given query
    @Test
    public void queryTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        assertEquals("insert into Client  (ID, Name, Email,Phone_Number) values ('1000', 'Mario', 'mario@miners.utep.edu', '915123456')",
                sqlMethods.clientNew(1000, "Albert", "albert@miners.utep.edu", 915123456));
    }





}