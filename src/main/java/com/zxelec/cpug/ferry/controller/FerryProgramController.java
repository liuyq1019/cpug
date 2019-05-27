package com.zxelec.cpug.ferry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zxelec.cpug.ferry.enitity.Employee;
import com.zxelec.cpug.ferry.service.FerryService;

@RestController
public class FerryProgramController {
	
	@Autowired
	private FerryService ferryService;
	
	@GetMapping("/get/{id}")
	public String get(@PathVariable("id") Integer id) {
		Employee emp = ferryService.getEmpId(id);
		System.out.println("emp:"+JSONObject.toJSONString(emp));
		return JSONObject.toJSONString(emp);
	}
}
