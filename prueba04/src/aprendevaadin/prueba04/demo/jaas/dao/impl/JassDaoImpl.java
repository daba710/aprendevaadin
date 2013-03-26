package aprendevaadin.prueba04.demo.jaas.dao.impl;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import aprendevaadin.prueba04.demo.jaas.IUserCredentials;
import aprendevaadin.prueba04.demo.jaas.dao.IPermissionData;
import aprendevaadin.prueba04.demo.jaas.dao.IPermissionIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.IPrincipalData;
import aprendevaadin.prueba04.demo.jaas.dao.IPrincipalIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.ISubjectData;
import aprendevaadin.prueba04.demo.jaas.dao.ISubjectIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.JassDao;

public class JassDaoImpl extends JassDao {
	
	private Map<ISubjectIdentifier, ISubjectData> subjects;
	
	public JassDaoImpl() {
		installSubjects();
	}
	
	private void installSubjects() {
		subjects.put(new SubjectIdentifier(1), new SubjectData("user", "userpass"));
		subjects.put(new SubjectIdentifier(2), new SubjectData("admin", "adminpass"));
	}
	

	@Override
	public Set<ISubjectIdentifier> getAllSubjects() {
		return Collections.unmodifiableSet(new TreeSet<ISubjectIdentifier>(subjects.keySet()));
	}

	@Override
	public ISubjectIdentifier getSubjectByName(String name) {
		for (ISubjectIdentifier subjectIdentifier : getAllSubjects()) {
			ISubjectData subjectData = subjects.get(subjectIdentifier);
			if (subjectData.getName().equals(name)) {
				return subjectIdentifier;
			}
		}
		return null;
	}

	@Override
	public ISubjectData getSubject(ISubjectIdentifier subjectIdentifier) {
		ISubjectData subjectData = subjects.get(subjectIdentifier);
		if (subjectData == null) {
			throw new IllegalArgumentException("El identificador no es valido.");
		}
		return subjectData;
	}

	@Override
	public Set<IPrincipalIdentifier> getPrincipalsBySubject(ISubjectIdentifier subjectIdentifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPrincipalData getPrincipal(IPrincipalIdentifier principalIdentifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IPermissionIdentifier> getPermissionsByPrincipal(IPrincipalIdentifier principalIdentifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPermissionData getPermission(IPermissionIdentifier permissionIdentifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IUserCredentials getUserByCredentials(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Principal> getPrincipals(IUserCredentials userCredential) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
