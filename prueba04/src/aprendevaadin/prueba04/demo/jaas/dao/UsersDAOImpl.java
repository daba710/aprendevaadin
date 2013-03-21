package aprendevaadin.prueba04.demo.jaas.dao;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import aprendevaadin.prueba04.demo.jaas.AdminGroupPrincipal;
import aprendevaadin.prueba04.demo.jaas.IUserCredentials;
import aprendevaadin.prueba04.demo.jaas.UserGroupPrincipal;

class UsersDAOImpl extends UsersDAO {
	
	private UserEntry[] users = new UserEntry[] {
		new UserEntry("admin", "adminpass"),
		new UserEntry("user", "userpass")
	};
	
	private class UserEntry implements IUserCredentials {
		
		private UUID id;
		private String name;
		private String password;
		
		private UserEntry(String name, String password) {
			this.id = UUID.fromString(name);
			this.name = name;
			this.password = password;
		}
		
		public UUID getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
		
		private String getPassword() {
			return password;
		}

		@Override
		public String toString() {
			return "UserEntry [id=" + id + ", name=" + name + "]";
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
	
	private String getUserNameByID(UUID id) {
		for (UserEntry entry : users) {
			if (entry.getId().equals(id)) {
				return entry.getName();
			}
		}
		return null;
	}
	
	
	public Set<Principal> getPrincipals(IUserCredentials userCredential) {
		
		// Se prepara el contenedor de los Principales
		TreeSet<Principal> set = new TreeSet<Principal>();
		
		// Se busca el usuario
		String userName = getUserNameByID(userCredential.getId());
		
		// En funcion del usuario se incorporan los principales.
		if (userName != null) {
			switch (userName) {
			case "user":
				set.add(new UserGroupPrincipal());
			case "admin":
				set.add(new AdminGroupPrincipal());
				break;
			default:
				break;
			}
		}
		return Collections.unmodifiableSet(set);
	}
	
}
