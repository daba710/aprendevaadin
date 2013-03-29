package aprendevaadin.prueba04.demo.jaas.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import aprendevaadin.prueba04.demo.jaas.dao.IDemoGroupPrincipalData;
import aprendevaadin.prueba04.demo.jaas.dao.IDemoGroupPrincipalIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.IDemoViewExecPermissionData;
import aprendevaadin.prueba04.demo.jaas.dao.IDemoViewExecPermissionIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.ISubjectData;
import aprendevaadin.prueba04.demo.jaas.dao.ISubjectIdentifier;
import aprendevaadin.prueba04.demo.jaas.dao.JassDao;

public class JassDaoImpl extends JassDao {
	
	private Map<ISubjectIdentifier, ISubjectData> subjects = new HashMap<>();
	private Map<IDemoGroupPrincipalIdentifier, IDemoGroupPrincipalData> demoGroupPrincipals = new HashMap<>();
	private Set<SubjectDemoGroupPrincipalJoin> subjectDemoGroupPrincipalJoins = new TreeSet<>();
	private Map<IDemoViewExecPermissionIdentifier, IDemoViewExecPermissionData> demoViewExecPermissions = new HashMap<>();
	private Set<DemoGroupPrincipalDemoViewExecPermissionJoin> demoGroupPrincipalDemoViewExecPermissionJoin = new TreeSet<>();
	
	public JassDaoImpl() {
		installSubjects();
		installDemoGroupPrincipals();
		installSubjectDemoGroupPrincipalJoin();
		installDemoViewExecPermissions();
		installDemoGroupPrincipalDemoViewExecPermissions();
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
	
	private void installDemoViewExecPermissions() {
		demoViewExecPermissions.put(new DemoViewExecPermissionIdentifier(0), new DemoViewExecPermissionData("*"));

		demoViewExecPermissions.put(new DemoViewExecPermissionIdentifier(1), new DemoViewExecPermissionData("cat"));
		demoViewExecPermissions.put(new DemoViewExecPermissionIdentifier(2), new DemoViewExecPermissionData("dog"));
	}
	
	private void installDemoGroupPrincipalDemoViewExecPermissions() {
		demoGroupPrincipalDemoViewExecPermissionJoin.add(new DemoGroupPrincipalDemoViewExecPermissionJoin(new DemoGroupPrincipalIdentifier(0), new DemoViewExecPermissionIdentifier(0)));
		
		demoGroupPrincipalDemoViewExecPermissionJoin.add(new DemoGroupPrincipalDemoViewExecPermissionJoin(new DemoGroupPrincipalIdentifier(1), new DemoViewExecPermissionIdentifier(1)));
		demoGroupPrincipalDemoViewExecPermissionJoin.add(new DemoGroupPrincipalDemoViewExecPermissionJoin(new DemoGroupPrincipalIdentifier(1), new DemoViewExecPermissionIdentifier(2)));
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
	public Set<IDemoGroupPrincipalIdentifier> getDemoGroupPrincipalIdentifiers(ISubjectIdentifier subjectIdentifier) {
		HashSet<IDemoGroupPrincipalIdentifier> result = new HashSet<>();
		for (SubjectDemoGroupPrincipalJoin join : subjectDemoGroupPrincipalJoins) {
			if (join.getSubjectIdentifier().equals(subjectIdentifier)) {
				result.add(join.getDemoGroupPrincipalIdentifier());
			}
		}
		return Collections.unmodifiableSet(result);
	}
	
	@Override
	public IDemoGroupPrincipalData getDemoGroupPrincipalData(IDemoGroupPrincipalIdentifier identifier) {
		IDemoGroupPrincipalData demoGroupPrincipalData = demoGroupPrincipals.get(identifier);
		if (demoGroupPrincipalData == null) {
			throw new IllegalArgumentException("El identificador no es valido.");
		}
		return demoGroupPrincipalData;
	}

	@Override
	public Set<IDemoViewExecPermissionIdentifier> getDemoViewExecPermission(IDemoGroupPrincipalIdentifier identifier) {
		HashSet<IDemoViewExecPermissionIdentifier> result = new HashSet<>();
		for (DemoGroupPrincipalDemoViewExecPermissionJoin join : demoGroupPrincipalDemoViewExecPermissionJoin) {
			if (join.getDemoGroupPrincipalIdentifier().equals(identifier)) {
				result.add(join.getDemoViewExecPermissionIdentifier());
			}
		}
		return Collections.unmodifiableSet(result);
	}

	@Override
	public IDemoViewExecPermissionData getDemoViewExecPermissionData(IDemoViewExecPermissionIdentifier identifier) {
		IDemoViewExecPermissionData demoViewExecPermissionData = demoViewExecPermissions.get(identifier);
		if (demoViewExecPermissionData == null) {
			throw new IllegalArgumentException("El identificador no es valido.");
		}
		return demoViewExecPermissionData;
	}
	
}
