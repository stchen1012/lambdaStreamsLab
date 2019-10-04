package com.company;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.Test;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lab {
    private List<Employee> employees = Arrays.asList(
            new Employee("Bezos, Jeff", LocalDate.of(2004, 4, 2), 68_109.00, "Male"),
            new Employee("Sheryl Sandberg", LocalDate.of(2014, 7, 1), 87_846.00,"Female"),
            new Employee("Buffet, Warren", LocalDate.of(2011, 7, 23), 95_035.00, "Male"),
            new Employee("Susan Wojcick", LocalDate.of(2015, 6, 1), 37_210.00, "Female"),
            new Employee("Zuckerberg, Mark", LocalDate.of(2016, 5, 12), 48_450.00, "Male"),
            new Employee("Brin, Sergey", LocalDate.of(2016, 8, 5), 74_416.00, "Male")
    );

    private <T> void printList(List<T> list) {
        //list.forEach(employee -> System.out.println(employee.toString()));
        list.forEach(employee -> System.out.println(employee.toString()));
    }

    @Test
    public void getEmployeesOver50k() {
        List<Employee> employees = this.employees.stream().filter(employee -> employee.getSalary() > 50000).collect(Collectors.toList());
        printList(employees);
    }

    @Test
    public void getEmployeeNamesHiredAfter2012() {
        LocalDate endOf2012 = LocalDate.of(2012, 12, 31);

        List<String> employees = this.employees.stream()
                .filter(employee -> employee.getHireDate().isAfter(endOf2012))
                .map(employee -> employee.toString())
                .collect(Collectors.toList());
        printList(employees);
    }

    @Test
    public void getMaxSalary() {
        double max = employees.stream()
                .map(employee -> employee.getSalary())
                .max(Double::compare)
                .orElse(0.0);
        System.out.println("Max:" + max);
    }

    @Test
    public void getMinSalary() {
        double min = employees.stream()
                .map(employee -> employee.getSalary())
                .min(Double::compare)
                .orElse(0.0);
        System.out.println("Min:" + min);
    }

    @Test
    public void getAverageSalaries() {
        double averageMale = employees.stream()
                .filter(employee -> employee.getGender().equals("Male"))
                .map(employee -> employee.getSalary())
                .reduce(0d, (e1, e2) -> (e1 + e2)/2);
        double averageFemale = employees.stream()
                .filter(employee -> employee.getGender().equals("Female"))
                .map(employee -> employee.getSalary())
                .reduce(0d, (e1, e2) -> (e1 + e2)/2);

        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
    }

    @Test
    public void getMaximumPaidEmployee() {
        Employee highest = employees.stream()
                .reduce(employees.get(0), (a, e) -> e.getSalary() > a.getSalary() ? e : a)
                ;
        System.out.println(highest);
    }
}
