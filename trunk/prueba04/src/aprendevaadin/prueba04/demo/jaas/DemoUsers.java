package aprendevaadin.prueba04.demo.jaas;

public class DemoUsers {
	
	static private UserEntry[] users = new UserEntry[] {
		new UserEntry("admin", "adminpass"),
		new UserEntry("user", "userpass")
	};
	
	static private class UserEntry {
		
		private String name;
		private String password;
		
		private UserEntry(String name, String password) {
			this.name = name;
			this.password = password;
		}
		
		public String getName() {
			return name;
		}
		
		public String getPassword() {
			return password;
		}
		
	}
	
	static public boolean checkUser(String name, String password) {
		for (UserEntry entry : users) {
			if (entry.getName().equals(name) && entry.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
}
