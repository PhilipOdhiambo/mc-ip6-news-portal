package dao;

import models.Employee;
import models.Role;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sqlo2RoleDao implements RoleDao{
    private final Sql2o SQLO;

    public Sqlo2RoleDao(Sql2o sql2o) {
        this.SQLO = sql2o;
    }

    // ------------- CREATE ---------------------- //

    @Override
    public void add(Role role) {
        String sql = "INSERT INTO roles (name) VALUES (:name)";
        try(Connection conn = SQLO.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(role)
                    .executeUpdate().getKey();
            role.setId(id);
        }
    }

    @Override
    public void addRoleToEmloyee(Role role, Employee employee) {
        String sql = "INSERT INTO employees_roles (employeeid, roleid) VALUES (:employeeid, :roleid)";
        try (Connection con = SQLO.open()) {
            con.createQuery(sql)
                    .addParameter("employeeid", employee.getId())
                    .addParameter("roleid", role.getId())
                    .executeUpdate();
        } catch (Sql2oException sql2oException){
            System.out.println(sql2oException);
        }
    }

    @Override
    public List<Role> getAll() {
        String sql = "SELECT * FROM roles";
        try(Connection conn = SQLO.open()){
            return conn.createQuery(sql).executeAndFetch(Role.class);
        }
    }

    @Override
    public Role getById(int id) {
        String sql = "SELECT * FROM roles WHERE id = :id";
        try(Connection conn = SQLO.open()) {
            return conn.createQuery(sql)
                    .addParameter("id", id).executeAndFetchFirst(Role.class);
        }
    }

    @Override
    public List<Employee> getEmployeesForRole(int id) {
        List<Employee> employees = new ArrayList();
        String sql = "SELECT employeeid FROM employees_roles WHERE roleid = :id";
        try (Connection con = SQLO.open()) {
            List<Integer> allEmployeeIds = con.createQuery(sql)
                    .addParameter("roleid", id)
                    .executeAndFetch(Integer.class);
            for (Integer employeeId : allEmployeeIds){
                sql = "SELECT * FROM employees WHERE id = :employeeid";
                employees.add(
                        con.createQuery(sql).addParameter("employeeid", employeeId)
                                .executeAndFetchFirst(Employee.class));
            }
        } catch (Sql2oException sql2oException){
            System.out.println(sql2oException);
        }
        return employees;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM employees_roles WHERE roleid = :id";
        try (Connection con = SQLO.open()) {
            con.createQuery(sql)
                    .addParameter("roleid", id).executeUpdate();
            sql = "DELETE FROM roles WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id",id).executeUpdate();
        } catch (Sql2oException sql2oException){
            System.out.println(sql2oException);
        }
    }
}
