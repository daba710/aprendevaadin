package aprendevaadin.prueba04.demo.jaas.dao.impl;

import java.security.Principal;
import java.util.Set;

import aprendevaadin.prueba04.demo.jaas.IUserCredentials;
import aprendevaadin.prueba04.demo.jaas.dao.IPermissionData;
import aprendevaadin.prueba04.demo.jaas.dao.IPermissionIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.IPrincipalData;
import aprendevaadin.prueba04.demo.jaas.dao.IPrincipalIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.ISubjectData;
import aprendevaadin.prueba04.demo.jaas.dao.ISubjectIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.JassDao;

public class JassDaoImpl extends JassDao {
	
	

	@Override
	public Set<ISubjectIdentifier> getAllSubjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISubjectIdentifier getSubjectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISubjectData getSubject(ISubjectIdentifier subjectIdentifier) {
		// TODO Auto-generated method stub
		return null;
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
