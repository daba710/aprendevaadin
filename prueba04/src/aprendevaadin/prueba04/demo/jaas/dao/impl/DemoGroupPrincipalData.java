package aprendevaadin.prueba04.demo.jaas.dao.impl;

import aprendevaadin.prueba04.demo.jaas.dao.IDemoGroupPrincipalData;

public class DemoGroupPrincipalData implements IDemoGroupPrincipalData {
	
	private String group;
	
	public DemoGroupPrincipalData(String group) {
		this.group = group;
	}
	
	@Override
	public String getGroup() {
		return group;
	}

	@Override
	public String toString() {
		return "DemoGroupPrincipalData [group=" + group + "]";
	}
	
}
