package aprendevaadin.prueba04.demo.jaas;

import java.security.Principal;

public class UserGroupPrincipal implements Principal, Comparable<Principal> {
	
	private String name;
	
	public UserGroupPrincipal() {
		this.name = "user";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserGroupPrincipal other = (UserGroupPrincipal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Principal other) {
		if (other == null) 
			throw new NullPointerException();
		return getName().compareTo(other.getName());
	}

	@Override
	public String toString() {
		return "UserGroupPrincipal [name=" + name + "]";
	}

}
