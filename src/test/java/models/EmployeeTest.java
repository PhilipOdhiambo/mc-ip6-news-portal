package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    @Test
    void instance_newEmployeeInstanceCreated_user() {
        Employee employee = getNewEmployee();
        assertTrue(employee instanceof Employee);
    }


    // helpers
    public Employee getNewEmployee() {
        return new Employee("Philip",1);
    }
}