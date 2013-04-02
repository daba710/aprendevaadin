package aprendevaadin.prueba05.demo.jaas.dao.impl;

import aprendevaadin.prueba05.demo.jaas.dao.IDemoGroupPrincipalIdentifier;

public class DemoGroupPrincipalDemoViewExecPermissionJoin implements Comparable<DemoGroupPrincipalDemoViewExecPermissionJoin>{
	
	private DemoGroupPrincipalIdentifier demoGroupPrincipalIdentifier;
	private DemoViewExecPermissionIdentifier demoViewExecPermissionIdentifier;
	
	public DemoGroupPrincipalDemoViewExecPermissionJoin(DemoGroupPrincipalIdentifier demoGroupPrincipalIdentifier, DemoViewExecPermissionIdentifier demoViewExecPermissionIdentifier) {
		this.demoGroupPrincipalIdentifier = demoGroupPrincipalIdentifier;
		this.demoViewExecPermissionIdentifier = demoViewExecPermissionIdentifier;
	}

	public IDemoGroupPrincipalIdentifier getDemoGroupPrincipalIdentifier() {
		return demoGroupPrincipalIdentifier;
	}

	public DemoViewExecPermissionIdentifier getDemoViewExecPermissionIdentifier() {
		return demoViewExecPermissionIdentifier;
	}

	@Override
	public int compareTo(DemoGroupPrincipalDemoViewExecPermissionJoin o) {
		int result = demoGroupPrincipalIdentifier.compareTo(o.demoGroupPrincipalIdentifier);
		if (result == 0) {
			return demoViewExecPermissionIdentifier.compareTo(o.demoViewExecPermissionIdentifier);
		} else {
			return result;
		}
	}

	@Override
	public String toString() {
		return "DemoGroupPrincipalDemoViewExecPermissionJoin [demoGroupPrincipalIdentifier="
				+ demoGroupPrincipalIdentifier
				+ ", demoViewExecPermissionIdentifier="
				+ demoViewExecPermissionIdentifier + "]";
	}
	
}
