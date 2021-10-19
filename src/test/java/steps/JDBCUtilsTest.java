package steps;

import org.junit.Assert;
import utilities.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JDBCUtilsTest {

    public static void main(String[] args) throws SQLException {
        JDBCUtils.establishConnection();

        List<Map<String,Object>> dataInfo = JDBCUtils.runQuery("SELECT * FROM employees LIMIT 3");
        System.out.println(dataInfo.get(2).get("first_name"));
        String first_name = dataInfo.get(2).get("first_name").toString();

        Assert.assertEquals(first_name,"Alexander");

        JDBCUtils.closeConnection();
    }

}
