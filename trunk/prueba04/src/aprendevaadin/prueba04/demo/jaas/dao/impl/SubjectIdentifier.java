package aprendevaadin.prueba04.demo.jaas.dao.impl;

import aprendevaadin.prueba04.demo.jaas.dao.ISubjectIdentifier;

public class SubjectIdentifier implements ISubjectIdentifier {
	
	private long id;
	
	public SubjectIdentifier(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		SubjectIdentifier other = (SubjectIdentifier) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubjectIdentifier [id=" + id + "]";
	}
	
}
