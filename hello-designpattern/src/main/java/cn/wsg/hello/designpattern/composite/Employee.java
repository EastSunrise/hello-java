package cn.wsg.hello.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private final String name;
    private final String dept;
    private final int salary;
    private final List<Employee> subordinates;

    //构造函数
    public Employee(String name, String dept, int sal) {
        this.name = name;
        this.dept = dept;
        this.salary = sal;
        subordinates = new ArrayList<Employee>();
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public String toString() {
        return ("Employee :[ Name : " + name
            + ", dept : " + dept + ", salary :"
            + salary + " ]");
    }
}
