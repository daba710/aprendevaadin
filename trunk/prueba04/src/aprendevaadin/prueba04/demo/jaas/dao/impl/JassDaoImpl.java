package aprendevaadin.prueba04.demo.jaas.dao.impl;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.gwt.dev.util.collect.HashMap;

import aprendevaadin.prueba04.demo.jaas.DemoGroupPrincipal;
import aprendevaadin.prueba04.demo.jaas.IUserCredentials;
import aprendevaadin.prueba04.demo.jaas.dao.IDemoGroupPrincipalData;
import aprendevaadin.prueba04.demo.jaas.dao.IDemoGroupPrincipalIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.IPermissionData;
import aprendevaadin.prueba04.demo.jaas.dao.IPermissionIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.ISubjectData;
import aprendevaadin.prueba04.demo.jaas.dao.ISubjectIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.JassDao;

public class JassDaoImpl extends JassDao {
	
	private Map<ISubjectIdentifier, ISubjectData> subjects = new HashMap<>();
	private Map<IDemoGroupPrincipalIdentifier, IDemoGroupPrincipalData> demoGroupPrincipals = new HashMap<>();
	private Set<SubjectDemoGroupPrincipalJoin> subjectDemoGroupPrincipalJoins = new TreeSet<>();
	
	public JassDaoImpl() {
		installSubjects();
		installDemoGroupPrincipals();
	}
	
	private void installSubjects() {
		subjects.put(new SubjectIdentifier(0), new SubjectData("admin", "adminpass"));
		subjects.put(new SubjectIdentifier(1), new SubjectData("user1", "userpass1"));
		subjects.put(new SubjectIdentifier(2), new SubjectData("user2", "userpass2"));
		subjects.put(new SubjectIdentifier(3), new SubjectData("user3", "userpass3"));
	}
	
	private void installDemoGroupPrincipals() {
		demoGroupPrincipals.put(new DemoGroupPrincipalIdentifier(0), new DemoGroupPrincipalData("administrators"));
		demoGroupPrincipals.put(new DemoGroupPrincipalIdentifier(1), new DemoGroupPrincipalData("users"));
	}
	
	private void installSubjectDemoGroupPrincipalJoin() {
		
		subjectDemoGroupPrincipalJoins.add(new SubjectDemoGroupPrincipalJoin(new SubjectIdentifier(0), new DemoGroupPrincipalIdentifier(0)));
		subjectDemoGroupPrincipalJoins.add(new SubjectDemoGroupPrincipalJoin(new SubjectIdentifier(0), new DemoGroupPrincipalIdentifier(1)));
		
		subjectDemoGroupPrincipalJoins.add(new SubjectDemoGroupPrincipalJoin(new SubjectIdentifier(1), new DemoGroupPrincipalIdentifier(1)));
		subjectDemoGroupPrincipalJoins.add(new SubjectDemoGroupPrincipalJoin(new SubjectIdentifier(2), new DemoGroupPrincipalIdentifier(1)));
		subjectDemoGroupPrincipalJoins.add(new SubjectDemoGroupPrincipalJoin(new SubjectIdentifier(3), new DemoGroupPrincipalIdentifier(1)));
		
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
