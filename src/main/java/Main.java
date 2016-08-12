import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Roman on 12.08.2016.
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        loadDriver();
        LOGGER.info("Connecting to DB");
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/company")) {
            LOGGER.info("Successfully connected to DB");
        } catch (SQLException e) {
            LOGGER.error("");
            e.printStackTrace();
        }
    }

    private static void loadDriver() {
        try {
            LOGGER.info("Loading JDBC driver: org.postgresql.Driver");
            Class.forName("org.postgresql.Driver");
            LOGGER.info("Driver has been successfully loaded");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find driver: org.postgresql.Driver" + e);
            throw new RuntimeException(e);
        }
    }
}
