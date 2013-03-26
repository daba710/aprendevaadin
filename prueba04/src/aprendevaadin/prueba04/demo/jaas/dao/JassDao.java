package aprendevaadin.prueba04.demo.jaas.dao;

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
	
}
