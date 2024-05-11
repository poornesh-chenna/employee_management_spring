package com.poornesh.employeeManagement;

import com.poornesh.employeeManagement.model.Department;
import com.poornesh.employeeManagement.service.DepartmentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.poornesh.employeeManagement"})
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(EmployeeManagementApplication.class, args);
		Department dp = ctx.getBean(Department.class);
//		dp.setDepId(2);
//		dp.setName("testing");
		DepartmentService ds = ctx.getBean(DepartmentService.class);
		//ds.addDepartment(dp);
		ds.showTables();
//		ds.findAll();
	}

}
