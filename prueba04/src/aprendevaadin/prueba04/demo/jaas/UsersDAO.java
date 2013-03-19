package aprendevaadin.prueba04.demo.jaas;

import java.util.UUID;

public class UsersDAO {
	
	static private UserEntry[] users = new UserEntry[] {
		new UserEntry("admin", "adminpass"),
		new UserEntry("user", "userpass")
	};
	
	static private class UserEntry implements IUserCredentials {
		
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
		
	}
	
	static public IUserCredentials getUser(String name, String password) {
		for (UserEntry entry : users) {
			if (entry.getName().equals(name) && entry.getPassword().equals(password)) {
				return entry;
			}
		}
		return null;
	}
	
}
