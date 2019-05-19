package com.zxelec.cpug.ferry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxelec.cpug.ferry.enitity.Employee;
import com.zxelec.cpug.ferry.mapper.EmployeeMapper;

@Service
public class FerryService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public Employee getEmpId(Integer id) {
		return employeeMapper.getEmployeeById(id);
	}
}
