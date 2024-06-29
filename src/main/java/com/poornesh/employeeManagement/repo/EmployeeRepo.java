package com.poornesh.employeeManagement.repo;

import com.poornesh.employeeManagement.model.Department;
import com.poornesh.employeeManagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepo {

    @Autowired
    JdbcTemplate jdbc;

    public void save(Employee e){
        String sql = "insert into Employee (empId,name,departmentId,projectId) values (?,?,?,?)";
        int rows = jdbc.update(sql,e.getEmpId(),e.getName(),e.getDepartmentId(),e.getProjectId());
        System.out.println(rows +" effected");
    }
    public int update(int id,Employee e) {
        String query = "update employee set name = (?), departmentId=(?), projectId=(?) where empId = (?)";
        return jdbc.update(query,e.getName(),e.getDepartmentId(),e.getProjectId(),id);
    }

    public int delete(int id) {
        String query = "delete from employee where empId = (?)";
        return jdbc.update(query,id);
    }


    public Employee find(int id) {
        try {
            String query = "select * from employee where empId = (?)";
            return jdbc.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
                Employee e = new Employee();
                e.setEmpId(rs.getInt("empId"));
                e.setName(rs.getString("name"));
                e.setDepartmentId(rs.getInt("departmentId"));
                e.setProjectId(rs.getInt("projectId"));

                return e;
            });
        }catch (Exception e){
            return null;
        }

    }

    public List<Employee> findAll() {
        String sql = "select * from Employee";
        return jdbc.query(sql, (rs, rowNum) -> {
            Employee e = new Employee();
            e.setEmpId(rs.getInt("empId"));
            e.setName(rs.getString("name"));
            e.setDepartmentId(rs.getInt("departmentId"));
            e.setProjectId(rs.getInt("projectId"));
            return e;
        });

    }
}

