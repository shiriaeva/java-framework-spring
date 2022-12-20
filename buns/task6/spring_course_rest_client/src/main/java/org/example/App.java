package org.example;

import org.example.configuration.MyConfig;
import org.example.entity.Employee;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();
        context.register(MyConfig.class);

        context.refresh();
        Communication communication = context.getBean("communication", Communication.class);
        List<Employee> employees = communication.getAllEmployees();

        System.out.println(employees);
//        Employee empById = communication.getEmployee(2);
//        System.out.println(empById);
//        Employee employee = new Employee("Sveta", "Sokolova", "IT", 1900);
//        employee.setId(5);
//        communication.saveEmployee(employee);
//        communication.deleteEmployee(5);
    }
}
