import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by Roman on 12.08.2016.
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        loadDriver();
        LOGGER.info("Connecting to DB");
        String url = "jdbc:postgresql://localhost:5432/company";
        String user = "user";
        String password = "111";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            LOGGER.info("Successfully connected to DB");

            String sql = "SELECT * FROM EMPLOYEE";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("ID"));
                employee.setName(resultSet.getString("NAME"));
                employee.setAge(resultSet.getInt("AGE"));
                employee.setAddress(resultSet.getString("ADDRESS"));
                employee.setSalary(resultSet.getFloat("SALARY"));
                employee.setJoinDate(resultSet.getString("JOIN_DATE"));
                System.out.println(employee.toString());

            }

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to BD " + url, e);
            e.printStackTrace();
        }
    }

    private static void loadDriver() {
        try {
            LOGGER.info("Loading JDBC driver: org.postgresql.Driver");
            Class.forName("org.postgresql.Driver");
            LOGGER.info("Driver has been successfully loaded");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find driver: org.postgresql.Driver",  e);
            throw new RuntimeException(e);
        }
    }
}
