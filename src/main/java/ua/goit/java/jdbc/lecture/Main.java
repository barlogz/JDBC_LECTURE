package ua.goit.java.jdbc.lecture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.java.jdbc.lecture.model.EmployeeDAO;
import ua.goit.java.jdbc.lecture.model.jdbc.JdbcEmployeeDAO;

/**
 * Created by Roman on 12.08.2016.
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new JdbcEmployeeDAO();

        System.out.println("All employees");
        employeeDAO.find().forEach(System.out::println);

        System.out.println("ua.goit.java.jdbc.lecture.model.Employee with id 3");
        System.out.println(employeeDAO.load(3));

    }

}
