package aprendevaadin.prueba05.demo.jaas.dao.impl;

import aprendevaadin.prueba05.demo.jaas.dao.ISubjectData;

public class SubjectData implements ISubjectData {
	
	private String name;
	private String password;
	
	public SubjectData(String name, String password) {
		this.name = name;
		this.password = password;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "SubjectData [name=" + name + ", password=" + password + "]";
	}
	
}
