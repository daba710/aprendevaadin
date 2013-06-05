package aprendevaadin.prueba09.model.internal;

import aprendevaadin.prueba09.model.IMyIdentifier;

public class MyIdentifier implements IMyIdentifier, Comparable<MyIdentifier> {

	private Long id;
	
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

	@Override
	public int compareTo(MyIdentifier other) {
		if (other instanceof MyIdentifier) {
			return this.id.compareTo(other.id);
		} else {
			return 0;
		}
	}
	
}
