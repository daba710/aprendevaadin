package aprendevaadin.prueba09.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import aprendevaadin.prueba09.model.IMyData;
import aprendevaadin.prueba09.model.IMyIdentifier;

import com.vaadin.data.Item;
import com.vaadin.data.Property;

public class MyItem implements Item {
	
	private static final long serialVersionUID = -2835207556910271369L;

	public final static String IDENTIFIER_ID = "IDENTIFIER";
	public final static String DESCRIPTION_ID = "DESCRIPTION";
	
	final Map<Object,Property<?>> properties = new HashMap<>() ;
	
	static public MyItem instantiate(IMyIdentifier identifier, IMyData data) {
		MyItem pluginSet = new MyItem();
		pluginSet.properties.put(IDENTIFIER_ID, IdentifierProperty.instantiate(identifier));
		pluginSet.properties.put(DESCRIPTION_ID, DescriptionProperty.instantiate(data));
		return pluginSet;
	}

	static public Collection<?> getItemPropertyIdsStatic() {
		ArrayList<Object> ids = new ArrayList<>();
		ids.add(IDENTIFIER_ID);
		ids.add(DESCRIPTION_ID);
		return Collections.unmodifiableList(ids);
	}
	
	static Class<?> getItemPropertyTypeStatic(Object propertyId) {
		switch (propertyId.toString()) {
		case IDENTIFIER_ID:
			return IdentifierProperty.getTypeStatic();
		case DESCRIPTION_ID:
			return DescriptionProperty.getTypeStatic();
		default:
			return null;
		}
	}

	@Override
	public Collection<?> getItemPropertyIds() {
		return MyItem.getItemPropertyIdsStatic();
	}

	@Override
	public Property<?> getItemProperty(Object id) {
		return properties.get(id.toString());
	}

	@Override
	public boolean addItemProperty(Object id, Property property) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeItemProperty(Object id) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString() {
		Property<?> descriptionProperty = properties.get(DESCRIPTION_ID);
		return descriptionProperty.getValue().toString();
	}

}
