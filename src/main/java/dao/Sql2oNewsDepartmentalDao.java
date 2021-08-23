package dao;

import models.News;
import models.NewsDepartmental;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oNewsDepartmentalDao implements NewsDepartmentalDao {
    public final Sql2o SQLO;

    public Sql2oNewsDepartmentalDao(Sql2o sql2o) {
        this.SQLO = sql2o;
    }

    @Override
    public void add(NewsDepartmental newsDepartmental) {
        String sql = "INSERT INTO newsdepartmental (content, departmentid) VALUES (:content, :departmentid)";
        try(Connection conn = SQLO.open()) {
            int id = (int) conn.createQuery(sql,true)
                    .addParameter("content", newsDepartmental.getContent())
                    .addParameter("departmentid", newsDepartmental.getDepartmentid())
                    .executeUpdate().getKey();
            newsDepartmental.setId(id);
        }
    }

    @Override
    public List<NewsDepartmental> getAll() {
        try(Connection connection = SQLO.open()) {
            return connection.createQuery("SELECT * FROM newsdepartmental")
                    .executeAndFetch(NewsDepartmental.class);
        }
    }

    @Override
    public NewsDepartmental getById(int id) {
        try(Connection connection = SQLO.open()) {
            return connection.createQuery("SELECT * FROM newsdepartmental WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(NewsDepartmental.class);
        }
    }

    @Override
    public void deleteById(int id) {
        try(Connection connection = SQLO.open()) {
            connection.createQuery("DELETE FROM newsdepartmental WHERE id = :id")
                    .addParameter("id", id).executeUpdate();
        }
    }
}
