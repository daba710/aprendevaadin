package aprendevaadin.prueba04.demo.jaas.dao.impl;

import aprendevaadin.prueba04.demo.jaas.dao.IDemoGroupPrincipalIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.ISubjectIdentifier;

public class DemoGroupPrincipalDemoViewExecPermissionJoin implements Comparable<DemoGroupPrincipalDemoViewExecPermissionJoin>{
	
	private SubjectIdentifier subjectIdentifier;
	private DemoGroupPrincipalIdentifier demoGroupPrincipalIdentifier;
	
	public DemoGroupPrincipalDemoViewExecPermissionJoin(SubjectIdentifier subjectIdentifier, DemoGroupPrincipalIdentifier demoGroupPrincipalIdentifier) {
		this.subjectIdentifier = subjectIdentifier;
		this.demoGroupPrincipalIdentifier = demoGroupPrincipalIdentifier;
	}

	public ISubjectIdentifier getSubjectIdentifier() {
		return subjectIdentifier;
	}

	public IDemoGroupPrincipalIdentifier getDemoGroupPrincipalIdentifier() {
		return demoGroupPrincipalIdentifier;
	}

	@Override
	public int compareTo(DemoGroupPrincipalDemoViewExecPermissionJoin o) {
		int result = subjectIdentifier.compareTo(o.subjectIdentifier);
		if (result == 0) {
			return demoGroupPrincipalIdentifier.compareTo(o.demoGroupPrincipalIdentifier);
		} else {
			return result;
		}
	}
	
	@Override
	public String toString() {
		return "SubjectDemoGroupPrincipalJoin [subjectIdentifier="
				+ subjectIdentifier + ", demoGroupPrincipalIdentifier="
				+ demoGroupPrincipalIdentifier + "]";
	}

}
