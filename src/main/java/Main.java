import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Roman on 12.08.2016.
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.getAll().forEach(System.out::println);


    }

}
