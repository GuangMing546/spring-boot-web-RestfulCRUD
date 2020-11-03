package com.example.demo01.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.demo01.entities.Department;
import org.springframework.stereotype.Repository;


@Repository
public class DepartmentDao {

	private static Map<Integer, Department> departments = null;
	
	static{
		departments = new HashMap<Integer, Department>();
		
		departments.put(101, new Department(101, "木叶村"));
		departments.put(102, new Department(102, "岩隐村"));
		departments.put(103, new Department(103, "沙漠村"));
		departments.put(104, new Department(104, "水影村"));
		departments.put(105, new Department(105, "晓组织"));
	}
	
	public Collection<Department> getDepartments(){
		return departments.values();
	}
	
	public Department getDepartment(Integer id){
		return departments.get(id);
	}
	
}
