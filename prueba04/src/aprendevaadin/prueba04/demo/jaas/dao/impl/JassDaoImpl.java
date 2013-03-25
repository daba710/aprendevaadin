package aprendevaadin.prueba04.demo.jaas.dao.impl;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import aprendevaadin.prueba04.demo.jaas.AdminGroupPrincipal;
import aprendevaadin.prueba04.demo.jaas.IUserCredentials;
import aprendevaadin.prueba04.demo.jaas.UserGroupPrincipal;
import aprendevaadin.prueba04.demo.jaas.dao.JassDao;

public class JassDaoImpl extends JassDao {
	
	private UserEntry[] users = new UserEntry[] {
		new UserEntry(0, "admin", "adminpass"),
		new UserEntry(1, "user", "userpass")
	};
	
	private class UserEntry implements IUserCredentials {
		
		private UUID uuid;
		private String name;
		private String password;
		
		private UserEntry(long id, String name, String password) {
			this.uuid = new UUID(0, id);
			this.name = name;
			this.password = password;
		}
		
		@Override
		public UUID getUUID() {
			return uuid;
		}
		
		@Override
		public String getName() {
			return name;
		}
		
		private String getPassword() {
			return password;
		}

		@Override
		public String toString() {
			return "UserEntry [id=" + uuid + ", name=" + name + "]";
		}
		
	}
	
	public IUserCredentials getUserByCredentials(String name, String password) {
		for (UserEntry entry : users) {
			if (entry.getName().equals(name) && entry.getPassword().equals(password)) {
				return entry;
			}
		}
		return null;
	}
	
	private String getUserNameByID(UUID uuid) {
		for (UserEntry entry : users) {
			if (entry.getUUID().equals(uuid)) {
				return entry.getName();
			}
		}
		return null;
	}
	
	public Set<Principal> getPrincipals(IUserCredentials userCredential) {
		
		// Se prepara el contenedor de los Principales
		TreeSet<Principal> set = new TreeSet<Principal>();
		
		// Se busca el usuario
		String userName = getUserNameByID(userCredential.getUUID());
		
		// En funcion del usuario se incorporan los principales.
		if (userName != null) {
			switch (userName) {
			case "admin":
				set.add(new AdminGroupPrincipal());
				set.add(new UserGroupPrincipal());
				break;
			case "user":
				set.add(new UserGroupPrincipal());
				break;
			default:
				break;
			}
		}
		return Collections.unmodifiableSet(set);
	}
	
}
