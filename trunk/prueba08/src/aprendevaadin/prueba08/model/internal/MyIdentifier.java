package aprendevaadin.prueba08.model.internal;

import aprendevaadin.prueba08.model.IMyIdentifier;

public class MyIdentifier implements IMyIdentifier {

	private long id;
	
	public MyIdentifier(long id) {
		this.id = id;
	}
	
	public MyIdentifier(IMyIdentifier dataIdentifier) {
		this(dataIdentifier.getId());
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
		MyIdentifier other = (MyIdentifier) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.valueOf(id); // SI es null, lo indicara.
	}
	
}
