package ua.goit.java.jdbc.lecture.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java.jdbc.lecture.model.Employee;
import ua.goit.java.jdbc.lecture.model.EmployeeDAO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 12.08.2016.
 */
public class JdbcEmployeeDAO implements EmployeeDAO {

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcEmployeeDAO.class);
//    private String url = "jdbc:postgresql://localhost:5432/company";
//    private String user = "user";
//    private String password = "111";

//    public JdbcEmployeeDAO() {
//        loadDriver();
//    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Employee load(int id) {
//        Employee employee = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM employee WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createEmployee(resultSet);
            } else {
                throw new RuntimeException("cannot findAll employee with id " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to BD " + /*url, */e);
            throw new RuntimeException(e);
        }
    }


    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Employee> findAll() {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");

            while (resultSet.next()) {
                Employee employee = createEmployee(resultSet);
                result.add(employee);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to BD " + /*url,*/ e);
            throw new RuntimeException(e);
        }
        return result;
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("ID"));
        employee.setName(resultSet.getString("NAME"));
        employee.setAge(resultSet.getInt("AGE"));
        employee.setAddress(resultSet.getString("ADDRESS"));
        employee.setSalary(resultSet.getFloat("SALARY"));
        employee.setJoinDate(resultSet.getString("JOIN_DATE"));
        return employee;
    }

//    private void loadDriver() {
//        try {
//            LOGGER.info("Loading JDBC driver: org.postgresql.Driver");
//            Class.forName("org.postgresql.Driver");
//            LOGGER.info("Driver has been successfully loaded");
//        } catch (ClassNotFoundException e) {
//            LOGGER.error("Cannot findAll driver: org.postgresql.Driver", e);
//            throw new RuntimeException(e);
//        }
//    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
