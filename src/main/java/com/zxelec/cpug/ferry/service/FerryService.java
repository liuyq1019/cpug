package com.zxelec.cpug.ferry.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.zxelec.cpug.ferry.enitity.Employee;
import com.zxelec.cpug.ferry.jpa.EmployeeJpa;

@Service
public class FerryService {
	
	@Autowired
	private EmployeeJpa employeeJpa;
//	private EmployeeMapper employeeMapper;
	
	public Employee getEmpId(Integer id) {
		return employeeJpa.getOne(id);
	}
	int c = 0;
	@Transactional
	@Scheduled(cron = "0/2 * *  * * ?")
	public void sch() {
		System.out.println("0/2 * *  * * ?");
		Employee emp = new Employee();
		emp.setLastName("aaaa:"+(c++));
		emp.setGeder(1);
		emp.setEmail("9999@qq.com");
		try {
			employeeJpa.save(emp);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}
}
