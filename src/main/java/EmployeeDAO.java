import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 12.08.2016.
 */
public class EmployeeDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDAO.class);
    private String url = "jdbc:postgresql://localhost:5432/company";
    private String user = "user";
    private String password = "111";

    public EmployeeDAO() {
        loadDriver();
    }

    public Employee load(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?")) {
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to BD " + url, e);
            throw new RuntimeException(e);
        }
    }


        public List<Employee> getAll () {
            List<Employee> result = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(url, user, password);
                 Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");

                while (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setId(resultSet.getInt("ID"));
                    employee.setName(resultSet.getString("NAME"));
                    employee.setAge(resultSet.getInt("AGE"));
                    employee.setAddress(resultSet.getString("ADDRESS"));
                    employee.setSalary(resultSet.getFloat("SALARY"));
                    employee.setJoinDate(resultSet.getString("JOIN_DATE"));
                    result.add(employee);
                }
            } catch (SQLException e) {
                LOGGER.error("Exception occurred while connecting to BD " + url, e);
                throw new RuntimeException(e);
            }
            return result;
        }

    private void loadDriver() {
        try {
            LOGGER.info("Loading JDBC driver: org.postgresql.Driver");
            Class.forName("org.postgresql.Driver");
            LOGGER.info("Driver has been successfully loaded");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find driver: org.postgresql.Driver", e);
            throw new RuntimeException(e);
        }
    }
}
