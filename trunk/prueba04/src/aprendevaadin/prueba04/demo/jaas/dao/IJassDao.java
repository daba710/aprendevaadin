package aprendevaadin.prueba04.demo.jaas.dao;

import java.util.Set;

public interface IJassDao {
	
	public Set<ISubjectIdentifier> getAllSubjects();
	public ISubjectIdentifier getSubjectByName(String name);
	public ISubjectData getSubject(ISubjectIdentifier subjectIdentifier);
	
	public Set<IPrincipalIdentifier> getPrincipalsBySubject(ISubjectIdentifier subjectIdentifier);
	public IPrincipalData getPrincipal(IPrincipalIdentifier principalIdentifier);
	
	public Set<IPermissionIdentifier> getPermissionsByPrincipal(IPrincipalIdentifier principalIdentifier);
	public IPermissionData getPermission(IPermissionIdentifier permissionIdentifier);
	
}
