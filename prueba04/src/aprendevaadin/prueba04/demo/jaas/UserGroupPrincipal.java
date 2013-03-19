package aprendevaadin.prueba04.demo.jaas;

import java.security.Principal;
import java.util.UUID;

public class UserGroupPrincipal implements Principal {
	
	private UUID id;
	private String name;
	
	public UserGroupPrincipal(IUserCredentials userCredentials) {
		this.id = userCredentials.getId();
		this.name = userCredentials.getName();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
