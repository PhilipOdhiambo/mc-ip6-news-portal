package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    @Test
    void instance_newUserInstanceCreated_user() {
        Employee employee = getNewUser();
        assertTrue(employee instanceof Employee);
    }


    // helpers
    public Employee getNewUser() {
        return new Employee("Philip",1);
    }
}