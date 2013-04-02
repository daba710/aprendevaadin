package aprendevaadin.prueba05.demo.jaas;

import java.security.Principal;

import aprendevaadin.prueba05.demo.jaas.dao.IDemoGroupPrincipalIdentifier;

public class DemoGroupPrincipal implements Principal, Comparable<Principal> {

	private String group;
	private IDemoGroupPrincipalIdentifier identifier;
	
	public DemoGroupPrincipal(String group, IDemoGroupPrincipalIdentifier identifier) {
		this.group = group;
		this.identifier = identifier;
	}

	@Override
	public String getName() {
		return group;
	}
	
	public IDemoGroupPrincipalIdentifier getIdentifier() {
		return identifier;
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
		return "DemoGroupPrincipal [group=" + group + ", identifier=" + identifier + "]";
	}
	
}
