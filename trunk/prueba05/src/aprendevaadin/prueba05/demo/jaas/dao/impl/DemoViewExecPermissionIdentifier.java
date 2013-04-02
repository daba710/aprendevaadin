package aprendevaadin.prueba05.demo.jaas.dao.impl;

import aprendevaadin.prueba05.demo.jaas.dao.IDemoViewExecPermissionIdentifier;

public class DemoViewExecPermissionIdentifier implements IDemoViewExecPermissionIdentifier, Comparable<DemoViewExecPermissionIdentifier> {

	private long id;
	
	public DemoViewExecPermissionIdentifier(long id) {
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
		DemoViewExecPermissionIdentifier other = (DemoViewExecPermissionIdentifier) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(DemoViewExecPermissionIdentifier o) {
		return Long.valueOf(getId()).compareTo(Long.valueOf(o.getId()));
	}
	
	@Override
	public String toString() {
		return "DemoViewExecPermissionIdentifier [id=" + id + "]";
	}

}
