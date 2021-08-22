package dao;

import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Queue;

public class Sql2oNewsDao implements NewsDao{
    private final Sql2o SQLO;

    public Sql2oNewsDao(Sql2o sql2o) {
        this.SQLO = sql2o;
    }

    @Override
    public void add(News news) {
        String sql = "INSERT INTO news (content) VALUES (:content)";
        try(Connection conn = SQLO.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .addParameter("content", news.getContent())
                    .executeUpdate().getKey();
            news.setId(id);
        }
    }

    @Override
    public List<News> getAll() {
        String sql = "SELECT * FROM news ";
        try(Connection conn = SQLO.open()) {
            return conn.createQuery(sql).executeAndFetch(News.class);
        }
    }

    @Override
    public News getById(int id) {
        try(Connection con = SQLO.open()) {
            return con.createQuery("SELECT * FROM news WHERE id = :id")
                    .addParameter("id", id).executeAndFetchFirst(News.class);
        }
    }


    @Override
    public void deleteById(int id) {
        try(Connection con = SQLO.open()) {
            con.createQuery("DELETE FROM news WHERE id = " + id)
                    .executeUpdate();
        }
    }
}
