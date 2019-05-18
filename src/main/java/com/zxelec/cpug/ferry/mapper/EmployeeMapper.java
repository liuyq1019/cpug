package com.zxelec.cpug.ferry.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.zxelec.cpug.ferry.enitity.Employee;

@Mapper
public interface EmployeeMapper {
	
	@Select("select * from employee where id = #{id}")
	public Employee getEmployeeById(Integer id);
	
	@Delete("delete from employee where id = #{id}")
	public void delEmp(Integer id);
}

