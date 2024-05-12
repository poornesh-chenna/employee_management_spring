package com.poornesh.employeeManagement.repo;

import com.poornesh.employeeManagement.dtos.ErrorResponse;
import com.poornesh.employeeManagement.model.Department;
import com.poornesh.employeeManagement.model.Project;
import com.poornesh.employeeManagement.service.DepartmentService;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.lang.Integer;
import java.util.List;

@Repository
public class ProjectRepo {
    private JdbcTemplate jdbc;

    @Autowired
    DepartmentService ds;


    public JdbcTemplate getJdbc() {
        return jdbc;
    }
    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Project p){
        String sql = "insert into project (projectId,name,departmentId) values (?,?,?)";
        int rows = jdbc.update(sql,p.getProjectId(),p.getName(),p.getDepartmentId());
        System.out.println(rows+" effected");
    }


    public Either<ErrorResponse, Integer> update(Project p) {

        Department foundDepartment = ds.find(p.getDepartmentId());
        if(foundDepartment == null)
            return Either.left(new ErrorResponse(String.format("Department with id: %d does not exists", p.getDepartmentId())));

        String query = "update project set name=(?), departmentId=(?) where projectId = (?)";
        int updatedRows = jdbc.update(query,p.getName(),p.getDepartmentId(),p.getProjectId());
        if(updatedRows == 0){
            return Either.left(new ErrorResponse(String.format("Project with id: %d does not exist",p.getProjectId())));
        }
        return Either.right(updatedRows);
    }

    public int delete(int id){
        String query = "delete from project where projectId = (?)";
        return jdbc.update(query,id);
    }

    public List<Project> findAll() {
        String sql = "select * from project";
        return jdbc.query(sql, (rs, rowNum) -> {
            Project p = new Project();
            p.setProjectId(rs.getInt("projectId"));
            p.setName(rs.getString("name"));
            p.setDepartmentId((rs.getInt("departmentId")));
            return p;
        });
    }

    public Project find(int id) {
        try {
            String query = "select * from project where projectId = (?)";
            return jdbc.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
                Project p = new Project();
                p.setProjectId(rs.getInt("projectId"));
                p.setName(rs.getString("name"));
                p.setDepartmentId((rs.getInt("departmentId")));
                return p;
            });
        }catch (Exception e){
            return null;
        }

    }
}
