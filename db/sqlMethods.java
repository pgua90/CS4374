package db;
/**
* Methods that generate SQL which is sent to the DB
*/
public class sqlMethods {
    //delete by name search
    public static String delete(String tableName, String name) {
        String sql = "delete from "+tableName+" where Name='"+name+"'";
        return sql;
    }

    // takes the table you want to edit, the column to change, the new info you want to change, the name of the person you want to update
    public static String update(String tableName,String column, String newInfo, String Name) {
        String sql = "update "+tableName+" set "+column+"='"+newInfo+"' where Name="+Name+"";
        return sql;
    }



    public static String clientNew(int id,String name, String email, int phonenum) {
        System.out.print("in here");
        String sql = "insert into Client " + " (ID, Name, Email,Phone_Number)"
                + " values ('"+id+"', '"+name+"', '"+email+"', '"+phonenum+"')";
        return sql;
    }

}
