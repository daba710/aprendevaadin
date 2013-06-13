package aprendevaadin.prueba12.data;

import aprendevaadin.prueba12.model.IMyData;

import com.vaadin.data.Property;

public class NameProperty implements Property<String> {
	
	private static final long serialVersionUID = -495716644912702429L;

	private String value;
	
	static public NameProperty instantiate(IMyData data) {
		NameProperty nameProperty = new NameProperty();
		nameProperty.value = data.getDescription();
		nameProperty.setReadOnly(true);
		return nameProperty;
	}
	
	static public Class<String> getTypeStatic() {
		return String.class;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String data) throws ReadOnlyException {
		 throw new ReadOnlyException();
	}

	@Override
	public Class<String> getType() {
		return NameProperty.getTypeStatic();
	}

	@Override
	public boolean isReadOnly() {
		return true;
	}

	@Override
	public void setReadOnly(boolean newStatus) {
		if (!newStatus) {
			throw new IllegalStateException("Required ReadOnly");
		}
	}

}
