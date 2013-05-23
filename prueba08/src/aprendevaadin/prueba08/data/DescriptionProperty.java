package aprendevaadin.prueba08.data;

import aprendevaadin.prueba08.model.IMyData;

import com.vaadin.data.util.AbstractProperty;

public class DescriptionProperty extends AbstractProperty<IMyData> {
	
	private static final long serialVersionUID = -495716644912702429L;

	private IMyData value;
	
	static public DescriptionProperty instantiate(IMyData data) {
		DescriptionProperty descriptionProperty = new DescriptionProperty();
		descriptionProperty.value = data;
		descriptionProperty.setReadOnly(true);
		return descriptionProperty;
	}
	
	static public Class<IMyData> getTypeStatic() {
		return IMyData.class;
	}

	@Override
	public IMyData getValue() {
		return value;
	}

	@Override
	public void setValue(IMyData data) throws com.vaadin.data.Property.ReadOnlyException {
		 throw new ReadOnlyException();
	}

	@Override
	public Class<IMyData> getType() {
		return DescriptionProperty.getTypeStatic();
	}

}
