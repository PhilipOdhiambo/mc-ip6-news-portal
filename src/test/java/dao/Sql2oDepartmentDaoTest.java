package dao;

import models.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oDepartmentDaoTest {

    private Connection conn;
    private Sql2oDepartmentDao departmentDao;


    @BeforeEach
    void setUp() {
        String connectionString =  "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "philip", "1234");
        departmentDao = new Sql2oDepartmentDao(sql2o);

        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        conn.close();
    }

    @Test
    void add_departmentAddedToDatabaseAndIdChanged_id() {
        Department department = getTestDepartment();
        departmentDao.add(department);
        assertNotEquals(0, department.getId());
    }

    @Test
    void getAll_addedDepartmentReturnedFromGetall_1() {
        Department department = getTestDepartment();
        departmentDao.add(department);
        assertEquals(1, departmentDao.getAll().size());
    }

    @Test
    void noDepartmentsReturnsEmptyList() throws Exception {
        assertEquals(0, departmentDao.getAll().size());
    }

    @Test
    void getById_returnsCorrectDepartment() {
        Department department1 = getTestDepartment();
        Department department2 = getTestDepartment();
        departmentDao.add(department1);
        departmentDao.add(department2);

        assertEquals(department1, departmentDao.getById(department1.getId()));
    }


    // helpers
    public Department getTestDepartment() {
        return  new Department("Administration", "Deals with administrative issues");
    }
}