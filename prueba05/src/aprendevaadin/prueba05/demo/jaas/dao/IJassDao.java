package aprendevaadin.prueba05.demo.jaas.dao;

import java.util.Set;

public interface IJassDao {
	
	public Set<ISubjectIdentifier> getAllSubjects();
	public ISubjectIdentifier getSubjectByName(String name);
	public ISubjectData getSubject(ISubjectIdentifier subjectIdentifier);
	public Set<IDemoGroupPrincipalIdentifier> getDemoGroupPrincipalIdentifiers(ISubjectIdentifier subjectIdentifier);
	public IDemoGroupPrincipalData getDemoGroupPrincipalData(IDemoGroupPrincipalIdentifier identifier);
	public Set<IDemoViewExecPermissionIdentifier> getDemoViewExecPermission(IDemoGroupPrincipalIdentifier identifier);
	public IDemoViewExecPermissionData getDemoViewExecPermissionData(IDemoViewExecPermissionIdentifier identifier);
	
}
