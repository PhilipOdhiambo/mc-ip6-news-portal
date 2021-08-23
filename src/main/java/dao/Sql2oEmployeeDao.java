package dao;

import models.Employee;
import models.Role;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oEmployeeDao implements EmployeeDao {
    private final Sql2o SQLO;
    public Sql2oEmployeeDao(Sql2o sql2o) {
        this.SQLO = sql2o;
    }



    /* ----  CREATE --------- */

    @Override
    public void add(Employee employee, int departmentId) {
        String sql = "INSERT INTO employees (name,departmentid) VALUES (:name,:departmentid)";
        try(Connection conn = SQLO.open()){
            int id = (int) conn.createQuery(sql, true)
                    .addParameter("name", employee.getName())
                    .addParameter("departmentid", departmentId)
                    .executeUpdate().getKey();
            employee.setId(id);
        }
    }

    @Override
    public void addEmployeeToRole(Employee employee, Role role) {
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

    /* ------ READ ------------ */

    @Override
    public List<Employee> getAll() {
        String sql = "SELECT * FROM employees";
        try(Connection conn = SQLO.open()){
            return conn.createQuery(sql).executeAndFetch(Employee.class);
        }
    }

    @Override
    public Employee getById(int id) {
        String sql = "SELECT * FROM employees WHERE id = :id";
        try(Connection conn = SQLO.open()) {
            return conn.createQuery(sql)
                    .addParameter("id", id).executeAndFetchFirst(Employee.class);
        }
    }

    @Override
    public List<Role> getRolesForEmployee(int id) {
        List<Role> roles = new ArrayList();
        String sql = "SELECT roleid FROM employees_roles WHERE employeeid = :id";
        try (Connection con = SQLO.open()) {
            List<Integer> allRoleIds = con.createQuery(sql)
                    .addParameter("employeeid", id)
                    .executeAndFetch(Integer.class);
            for (Integer roleId : allRoleIds){
                sql = "SELECT * FROM roles WHERE id = :roleId";
                roles.add(
                        con.createQuery(sql).addParameter("roleId", roleId)
                                .executeAndFetchFirst(Role.class));
            }
        } catch (Sql2oException sql2oException){
            System.out.println(sql2oException);
        }
        return roles;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM employees_roles WHERE employeeid = :id";
        try (Connection con = SQLO.open()) {
            con.createQuery(sql)
                    .addParameter("employeeid", id).executeUpdate();
            sql = "DELETE FROM employees WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id",id).executeUpdate();
        } catch (Sql2oException sql2oException){
            System.out.println(sql2oException);
        }
    }
}
