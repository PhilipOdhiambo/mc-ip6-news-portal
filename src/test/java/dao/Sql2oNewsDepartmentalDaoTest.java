package dao;

import models.News;
import models.NewsDepartmental;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oNewsDepartmentalDaoTest {

    private Connection conn;
    private Sql2oNewsDepartmentalDao newsDepartmentalDao;

    @BeforeEach
    void setUp() {
        String connectionString =  "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "philip", "1234");
        newsDepartmentalDao = new Sql2oNewsDepartmentalDao(sql2o);

        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        conn.close();
    }

    @Test
    void add_addNewsDepartmentReturnId_id() {
        NewsDepartmental news = getTestNewsDepartmental();
        newsDepartmentalDao.add(news);
        assertNotEquals(0,news.getId());

    }

    @Test
    void getAll_returnAddedNewsIntGetall_news() {
        NewsDepartmental news = getTestNewsDepartmental();
        newsDepartmentalDao.add(news);
        assertEquals(1, newsDepartmentalDao.getAll().size());
    }

    @Test
    void getById() {
    }

    @Test
    void deleteById() {
    }

    // Helper
    public NewsDepartmental getTestNewsDepartmental() {
        return new NewsDepartmental("Department news",2);
    }
}