package ua.goit.java.jdbc.lecture.model;

import java.util.List;

/**
 * Created by Roman on 12.08.2016.
 */
public interface EmployeeDAO {
    Employee load(int id);

    List<Employee> findAll();
}
