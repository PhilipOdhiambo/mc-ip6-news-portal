package dao;

import models.Department;
import models.Employee;
import models.NewsDepartmental;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {
    private final Sql2o SQL2O;

    public Sql2oDepartmentDao(Sql2o Sql2o) {
        this.SQL2O = Sql2o;
    }


    @Override
    public void add(Department department) {
        String sql = "INSERT INTO  departments (name,description) VALUES (:name, :description);";
        try(Connection conn = SQL2O.open()){
           int id = (int) conn.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Department> getAll() {
        String sql = "SELECT * FROM departments;";
        try(Connection conn = SQL2O.open()){
            return conn.createQuery(sql).executeAndFetch(Department.class);
        }
    }

    @Override
    public Department getById(int id) {
        String sql = "SELECT * FROM departments WHERE id = :id";
        try(Connection conn = SQL2O.open()){
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }


    @Override
    public List<Employee> getEmployeesByDepartment(int departmentId) {
        String sql = "SELECT * FROM employees WHERE departmentid = :departmentId";
        try(Connection conn = SQL2O.open()){
            return conn.createQuery(sql)
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Employee.class);
        } catch (Sql2oException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public List<NewsDepartmental> getNewsDepartmentalByDepartment(int departmentId) {
        String sql = "SELECT * FROM newsdepartmental WHERE departmentid = :id";
        try(Connection conn = SQL2O.open()){
            return conn.createQuery(sql)
                    .addParameter("departmentid", departmentId)
                    .executeAndFetch(NewsDepartmental.class);
        }
    }


//  testing if this works
    @Override
    public void update(Department newDepartment) {
        String sql = "UPDATE department SET (name, description) = (:name, :description)";
        try(Connection conn = SQL2O.open()){
            conn.createQuery(sql)
                    .bind(newDepartment)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM department WHERE id = :id";
        try(Connection conn = SQL2O.open()){
            conn.createQuery(sql).executeUpdate();
        }
    }

}
