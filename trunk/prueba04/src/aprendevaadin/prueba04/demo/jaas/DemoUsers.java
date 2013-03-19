package aprendevaadin.prueba04.demo.jaas;

import java.util.UUID;

public class DemoUsers {
	
	static private UserEntry[] users = new UserEntry[] {
		new UserEntry("admin", "adminpass"),
		new UserEntry("user", "userpass")
	};
	
	static public class UserEntry {
		
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
	
	static public UserEntry getUser(String name, String password) {
		for (UserEntry entry : users) {
			if (entry.getName().equals(name) && entry.getPassword().equals(password)) {
				return entry;
			}
		}
		return null;
	}
	
}
