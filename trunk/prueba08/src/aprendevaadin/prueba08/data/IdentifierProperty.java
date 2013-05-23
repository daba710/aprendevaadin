package aprendevaadin.prueba08.data;

import aprendevaadin.prueba08.model.IMyIdentifier;

import com.vaadin.data.util.AbstractProperty;

public class IdentifierProperty extends AbstractProperty<Long> {
	
	private static final long serialVersionUID = -495716644912702429L;

	private Long value;
	
	static public IdentifierProperty instantiate(IMyIdentifier identifier) {
		IdentifierProperty identifierProperty = new IdentifierProperty();
		identifierProperty.value = identifier.getId();
		identifierProperty.setReadOnly(true);
		return identifierProperty;
	}
	
	static public Class<Long> getTypeStatic() {
		return Long.class;
	}

	@Override
	public Long getValue() {
		return value;
	}
	
	@Override
	public void setValue(Long newValue) throws ReadOnlyException {
		 throw new ReadOnlyException();
	}

	@Override
	public Class<Long> getType() {
		return IdentifierProperty.getTypeStatic();
	}

}
