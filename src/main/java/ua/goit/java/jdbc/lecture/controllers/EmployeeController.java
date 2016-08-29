package ua.goit.java.jdbc.lecture.controllers;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ua.goit.java.jdbc.lecture.model.Employee;

import java.util.List;

/**
 * Created by Roman on 23.08.2016.
 */
public class EmployeeController {

    private PlatformTransactionManager txManager;

    public List<Employee> getAllEmployees() {
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));


    }
}
