package aprendevaadin.prueba10.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aprendevaadin.prueba10.model.IMyData;

import com.vaadin.data.Item;
import com.vaadin.data.Property;

public class MyItem implements Item, Item.PropertySetChangeNotifier {
	
	private static final long serialVersionUID = -2835207556910271369L;
	
	static public Logger logger = LoggerFactory.getLogger(MyItem.class);

	public final static String VALUE_ID = "VALUE";
	public final static String DESCRIPTION_ID = "DESCRIPTION";
	
	final Map<Object,Property<?>> properties = new HashMap<>() ;
	
	static public MyItem instantiate(IMyData data) {
		MyItem pluginSet = new MyItem();
		pluginSet.properties.put(VALUE_ID, ValueProperty.instantiate(data));
		pluginSet.properties.put(DESCRIPTION_ID, DescriptionProperty.instantiate(data));
		return pluginSet;
	}

	static public Collection<?> getItemPropertyIdsStatic() {
		ArrayList<Object> ids = new ArrayList<>();
		ids.add(VALUE_ID);
		ids.add(DESCRIPTION_ID);
		return Collections.unmodifiableList(ids);
	}
	
	static Class<?> getItemPropertyTypeStatic(Object propertyId) {
		switch (propertyId.toString()) {
		case VALUE_ID:
			return ValueProperty.getTypeStatic();
		case DESCRIPTION_ID:
			return DescriptionProperty.getTypeStatic();
		default:
			return null;
		}
	}

	/////////////////////////////////////////////////////////////
	// Item
	/////////////////////////////////////////////////////////////
	
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

	/////////////////////////////////////////////////////////////
	// Item.PropertySetChangeNotifier
	/////////////////////////////////////////////////////////////
	
	private List<PropertySetChangeListener> propertySetChangeListeners = new LinkedList<>(); 
	
	void firePropertySetChangeEvent() {
		
		// Se crea el evento.
		logger.debug("Se instancia el evento 'PropertySetChangeEvent'.");
		PropertySetChangeEvent propertySetChangeEvent = new PropertySetChangeEvent() {

			private static final long serialVersionUID = 1L;

			@Override
			public Item getItem() {
				return MyItem.this;
			}
		};
		
		// Se envia el evento a cada listener
		logger.debug(String.format("Se envia el evento a %d listeners 'PropertySetChangeListener'", propertySetChangeListeners.size()));
		synchronized (propertySetChangeListeners) {
			for (PropertySetChangeListener propertySetChangeListener : propertySetChangeListeners) {
				propertySetChangeListener.itemPropertySetChange(propertySetChangeEvent);
			}
		}
		
	}
	
	@Override
	public void addPropertySetChangeListener(PropertySetChangeListener listener) {
		synchronized (propertySetChangeListeners) {
			propertySetChangeListeners.add(listener);
		}
	}

	@Override
	@Deprecated
	public void addListener(PropertySetChangeListener listener) {
		addPropertySetChangeListener(listener);
	}

	@Override
	public void removePropertySetChangeListener(PropertySetChangeListener listener) {
		synchronized (propertySetChangeListeners) {
			propertySetChangeListeners.remove(listener);
		}
	}

	@Override
	@Deprecated
	public void removeListener(PropertySetChangeListener listener) {
		removePropertySetChangeListener(listener);
	}

}
