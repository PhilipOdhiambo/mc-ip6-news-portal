package dao;

import models.Department;
import models.News;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oNewsDaoTest {

    private Connection conn;
    private Sql2oNewsDao newsDao;


    @BeforeEach
    void setUp() {
        String connectionString =  "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "philip", "1234");
        newsDao = new Sql2oNewsDao(sql2o);

        conn = sql2o.open();
    }


    @AfterEach
    void tearDown() {
        conn.close();
    }

    @Test
    void add_addNewsSetId_id() {
        News news = getTestNews();
        newsDao.add(news);
        assertNotEquals(0, news.getId());
    }

    @Test
    void getAll_addedNewsReturnedFromGetall_1() {
        News news = getTestNews();
        newsDao.add(news);
        assertEquals(1, newsDao.getAll().size());
    }

    @Test
    void noNewsReturnsEmptyList() throws Exception {
        assertEquals(0, newsDao.getAll().size());
    }

    @Test
    void getById_returnsCorrectNews() {
        News news1 = getTestNews();
        News news2 = getTestNews();
        newsDao.add(news1);
        newsDao.add(news2);

        assertEquals(news1, newsDao.getById(news1.getId()));
    }


    // helpers
    public News getTestNews() {
        return  new News("General news");
    }

}