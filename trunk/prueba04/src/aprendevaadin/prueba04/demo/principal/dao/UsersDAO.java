package aprendevaadin.prueba04.demo.principal.dao;

import java.security.Principal;
import java.util.Set;

import aprendevaadin.prueba04.demo.principal.jaas.IUserCredentials;

abstract public class UsersDAO {
	
	static private UsersDAO instance = null;
	
	static public UsersDAO getInstance() {
		synchronized (UsersDAO.class) {
			if (instance == null) {
				instance = new UsersDAOImpl();
			}
		}
		return instance;
	}
	
	abstract public IUserCredentials getUserByCredentials(String name, String password);
	abstract public Set<Principal> getPrincipals(IUserCredentials userCredential);
	
}
