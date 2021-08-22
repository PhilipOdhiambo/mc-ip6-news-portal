package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
    @Test
    public void departmentInstance_newDepartmentInstanceCreated_department() {
        Department department = getNewDepartment();
        assertTrue(department instanceof Department);
    }

    // helpers
    public Department getNewDepartment() {
        return  new Department("Administration", "Deals with administrative issues");
    }
}