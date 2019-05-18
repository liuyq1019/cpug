package com.zxelec.cpug.ferry.enitity;

public class Employee {
	
	private Integer id;
	private String lastName;
	private String email;
	private Integer geder;
	
	private Integer dId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGeder() {
		return geder;
	}

	public void setGeder(Integer geder) {
		this.geder = geder;
	}

	public Integer getdId() {
		return dId;
	}

	public void setdId(Integer dId) {
		this.dId = dId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", geder=" + geder + ", dId="
				+ dId + "]";
	}
	
	
	
}
