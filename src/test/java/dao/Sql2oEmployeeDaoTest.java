package dao;

import models.Department;
import models.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import static org.junit.jupiter.api.Assertions.*;

class Sql2oEmployeeDaoTest {
    private Connection conn;
    private Sql2oEmployeeDao employeeDao;


    @BeforeEach
    void setUp() {
        String connectionString =  "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "philip", "1234");
        employeeDao = new Sql2oEmployeeDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        conn.close();
    }

    @Test
    void add_addedEmployeeSetId_id() {
        Employee employee = getNewEmployee();
        employeeDao.add(employee);
        assertNotEquals(0, employee.getId());

    }

    @Test
    void addEmployeeToRole() {
    }

    @Test
    void getAll_savedEmployeeReturned() {
        Employee employee = getNewEmployee();
        employeeDao.add(employee);
        assertEquals(1, employeeDao.getAll().size());
    }

    @Test
    void getById() {
    }

    @Test
    void getRolesForEmployee() {
    }

    @Test
    void deleteById() {
    }


    // helpers
    public Employee getNewEmployee() {
        return new Employee("Philip");
    }
}