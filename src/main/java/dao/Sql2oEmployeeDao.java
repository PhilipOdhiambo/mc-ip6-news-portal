package dao;

import models.Employee;
import models.Role;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

import java.util.List;

public class Sql2oEmployeeDao implements EmployeeDao {
    private final Sql2o SQLO;
    public Sql2oEmployeeDao(Sql2o sql2o) {
        this.SQLO = sql2o;
    }

//    private int id;
//    private String name;
//    private int departmentid;

    @Override
    public void add(Employee employee) {
        String sql = "INSERT INTO employees (name,departmentid) VALUES (:name,:departmentid)";
        try(Connection conn = SQLO.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(employee)
                    .executeUpdate().getKey();
            employee.setId(id);
        }
    }

    @Override
    public void addToRole(Employee employee, Role role) {
        String sql = "";
        try(Connection conn = SQLO.open()){}
    }

    @Override
    public List<Employee> getAll() {
        String sql = "";
        try(Connection conn = SQLO.open()){}
        return null;
    }

    @Override
    public List<Role> getRolesForEmployee(int id) {
        String sql = "";
        try(Connection conn = SQLO.open()){}
        return null;
    }



    @Override
    public void deleteById(int id) {
        String sql = "";
        try(Connection conn = SQLO.open()){}
    }
}
