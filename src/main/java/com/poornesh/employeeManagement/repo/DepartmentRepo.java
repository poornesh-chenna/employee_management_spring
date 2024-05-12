package com.poornesh.employeeManagement.repo;

import com.poornesh.employeeManagement.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepo {
    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }
    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Department d){
        String sql = "insert into department (depId,name) values (?,?)";
        int rows = jdbc.update(sql,d.getDepId(),d.getName());
        System.out.println(rows+" effected");
    }


    public List<Department> findAll() {
        String sql = "select * from department";
        return jdbc.query(sql, (rs, rowNum) -> {
            Department d = new Department();
            d.setDepId(rs.getInt("depId"));
            d.setName(rs.getString("name"));
            return d;
        });

    }

    public void showTables() {
        String query = "SHOW TABLES";
        List<String> tableNames = jdbc.queryForList(query, String.class);
        System.out.println(tableNames);
    }

    public int update(Department d) {
        String query = "update department set name = (?) where depId = (?)";
        int rows = jdbc.update(query,d.getName(),d.getDepId());
        return rows;
    }

    public int delete(int id) {
        String query = "delete from department where depId = (?)";
        return jdbc.update(query,id);
    }


    public Department find(int id) {
        try {
            String query = "select * from department where depId = (?)";
            return jdbc.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
                Department d = new Department();
                d.setDepId(rs.getInt("depId"));
                d.setName(rs.getString("name"));
                return d;
            });
        }catch (Exception e){
            return null;
        }

    }
}
