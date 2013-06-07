package aprendevaadin.prueba10.data;

import aprendevaadin.prueba10.model.IMyData;

import com.vaadin.data.util.AbstractProperty;

public class DescriptionProperty extends AbstractProperty<String> {
	
	private static final long serialVersionUID = -495716644912702429L;

	private String value;
	
	static public DescriptionProperty instantiate(IMyData data) {
		DescriptionProperty descriptionProperty = new DescriptionProperty();
		descriptionProperty.value = data.getDescription();
		descriptionProperty.setReadOnly(true);
		return descriptionProperty;
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
		return DescriptionProperty.getTypeStatic();
	}

}
