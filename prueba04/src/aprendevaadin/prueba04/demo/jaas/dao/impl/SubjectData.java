package aprendevaadin.prueba04.demo.jaas.dao.impl;

import aprendevaadin.prueba04.demo.jaas.dao.ISubjectData;

public class SubjectData implements ISubjectData {
	
	private String name;
	
	public SubjectData(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
