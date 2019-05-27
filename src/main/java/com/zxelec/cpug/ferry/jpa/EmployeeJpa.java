package com.zxelec.cpug.ferry.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zxelec.cpug.ferry.enitity.Employee;

public interface EmployeeJpa extends JpaRepository<Employee, Integer>{

}
