package aprendevaadin.prueba08.data;

import aprendevaadin.prueba08.model.IMyIdentifier;

import com.vaadin.data.util.AbstractProperty;

public class IdentifierProperty extends AbstractProperty<IMyIdentifier> {
	
	private static final long serialVersionUID = -495716644912702429L;

	private IMyIdentifier value;
	
	static public IdentifierProperty instantiate(IMyIdentifier identifier) {
		IdentifierProperty identifierProperty = new IdentifierProperty();
		identifierProperty.value = identifier;
		identifierProperty.setReadOnly(true);
		return identifierProperty;
	}
	
	static public Class<IMyIdentifier> getTypeStatic() {
		return IMyIdentifier.class;
	}

	@Override
	public IMyIdentifier getValue() {
		return value;
	}
	
	@Override
	public void setValue(IMyIdentifier newValue) throws com.vaadin.data.Property.ReadOnlyException {
		 throw new ReadOnlyException();
	}

	@Override
	public Class<IMyIdentifier> getType() {
		return IdentifierProperty.getTypeStatic();
	}

}
