package aprendevaadin.prueba04.demo.jaas.dao;

import java.util.Set;

public interface IJassDao {
	
	public Set<ISubjectIdentifier> getAllSubjects();
	public ISubjectIdentifier getSubjectByName(String name);
	public ISubjectData getSubject(ISubjectIdentifier subjectIdentifier);
	public Set<IDemoGroupPrincipalIdentifier> getDemoGroupPrincipalIdentifier(ISubjectIdentifier subjectIdentifier);
	public IDemoGroupPrincipalData getDemoGroupPrincipalData(IDemoGroupPrincipalIdentifier identifier);

}
