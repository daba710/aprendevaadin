package aprendevaadin.prueba04.demo.jaas.dao;

import java.security.Principal;
import java.util.Set;

import aprendevaadin.prueba04.demo.jaas.IUserCredentials;
import aprendevaadin.prueba04.demo.jaas.dao.impl.JassDaoImpl;

abstract public class JassDao implements IJassDao {
	
	static private IJassDao instance = null;
	
	static public IJassDao getInstance() {
		synchronized (JassDao.class) {
			if (instance == null) {
				instance = new JassDaoImpl();
			}
		}
		return instance;
	}
	
	abstract public IUserCredentials getUserByCredentials(String name, String password);
	abstract public Set<Principal> getPrincipals(IUserCredentials userCredential);
	
}
