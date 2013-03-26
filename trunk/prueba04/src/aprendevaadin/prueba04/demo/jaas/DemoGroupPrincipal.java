package aprendevaadin.prueba04.demo.jaas;

import java.security.Principal;

public class DemoGroupPrincipal implements Principal, Comparable<Principal> {

	private String group;
	
	public DemoGroupPrincipal(String group) {
		this.group = group;
	}

	@Override
	public String getName() {
		return group;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
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
		DemoGroupPrincipal other = (DemoGroupPrincipal) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
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
		return "DemoGroupPrincipal [group=" + group + "]";
	}
	
}
