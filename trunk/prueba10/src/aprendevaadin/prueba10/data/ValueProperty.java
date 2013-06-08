package aprendevaadin.prueba10.data;

import aprendevaadin.prueba10.model.IMyData;

import com.vaadin.data.Property;

public class ValueProperty implements Property<Long> {
	
	private static final long serialVersionUID = -495716644912702429L;

	private Long value;
	
	static public ValueProperty instantiate(IMyData data) {
		ValueProperty valueProperty = new ValueProperty();
		valueProperty.value = data.getValue();
		valueProperty.setReadOnly(true);
		return valueProperty;
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
		return ValueProperty.getTypeStatic();
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
