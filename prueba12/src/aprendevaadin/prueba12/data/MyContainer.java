package aprendevaadin.prueba12.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aprendevaadin.prueba12.model.IModelTracker;
import aprendevaadin.prueba12.model.IMyData;
import aprendevaadin.prueba12.model.IMyIdentifier;
import aprendevaadin.prueba12.model.IMyModel;
import aprendevaadin.prueba12.model.impl.MyIdentifier;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.UI;

public class MyContainer implements Container, Container.ItemSetChangeNotifier {
	
	static public Logger logger = LoggerFactory.getLogger(MyContainer.class);
	
	private static final long serialVersionUID = -7259357335150148384L;
	
	private class ModelTracker implements IModelTracker {

		@Override
		public void loadInitialModel() {
			logger.debug(String.format("loadInitialModel()"));
			// Este evento llega demasiado pronto cuando la tabla aun no esta ensamblada asi que no se tiene en cuenta.
		}

		@Override
		public void changedRow(final IMyIdentifier myIdentifier) {
			logger.debug(String.format("changedRow(%s)", myIdentifier.getId()));
			UI.getCurrent().access(new Runnable() {
				@Override
				public void run() {
					
					// DON'T WORK
					// El evento de cambios en los valores del Item no tiene efecto sobre la tabla.
					// FALLO: No se entera la tabla.
					MyItem myItem = (MyItem) getItem(myIdentifier);
					myItem.firePropertySetChangeEvent();
					
					// WORK
					// Asi que lo paso como un cambio en las filas.. 
//					fireItemSetChangeEvent();
//					UI.getCurrent().push();
				}
			});
		}

		@Override
		public void changedRowCollection() {
			logger.debug(String.format("changedRowCollection()"));
			// Cambio en el las filas.
			UI.getCurrent().access(new Runnable() {
				@Override
				public void run() {
					fireItemSetChangeEvent();
					UI.getCurrent().push();
				}
			});
		}
		
	}
	
	private IMyModel myModel;
	
	public MyContainer(IMyModel myModel) {
		this.myModel = myModel;
		this.myModel.addModelTracker(new ModelTracker());
	}

	/////////////////////////////////////////////////////////////
	// Container
	/////////////////////////////////////////////////////////////
	
	@Override
	public Item getItem(Object itemId) {
		if (itemId instanceof IMyIdentifier) {
			IMyIdentifier dataIdentifier = (IMyIdentifier) itemId;
			IMyData myData = myModel.getData(dataIdentifier);
			if (myData != null) {
				return MyItem.instantiate(myData);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public Collection<?> getContainerPropertyIds() {
		return MyItem.getItemPropertyIdsStatic();
	}

	@Override
	public Collection<?> getItemIds() {
		List<MyIdentifier> ids = new ArrayList<>();
		for (IMyIdentifier myIdentifier : myModel.getAllIdentifiers()) {
			ids.add((MyIdentifier) myIdentifier);
		}
		Collections.sort(ids);
		return Collections.unmodifiableList(ids);
	}

	@Override
	public Property<?> getContainerProperty(Object itemId, Object propertyId) {
		Item item = getItem(itemId);
		if (item == null) {
			return null;
		}
		return item.getItemProperty(propertyId);
	}

	@Override
	public Class<?> getType(Object propertyId) {
		return MyItem.getItemPropertyTypeStatic(propertyId);
	}

	@Override
	public int size() {
		return myModel.getAllIdentifiers().size();
	}

	@Override
	public boolean containsId(Object itemId) {
		if (itemId instanceof IMyIdentifier) {
			IMyIdentifier dataIdentifier = (IMyIdentifier) itemId;
			return myModel.getData(dataIdentifier) != null;
		} else {
			return false;
		}
	}

	@Override
	public Item addItem(Object itemId) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object addItem() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeItem(Object itemId) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeContainerProperty(Object propertyId) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAllItems() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/////////////////////////////////////////////////////////////
	// Container.ItemSetChangeNotifier
	/////////////////////////////////////////////////////////////
	
	private List<ItemSetChangeListener> itemSetChangeListeners = new LinkedList<>(); 
	
	void fireItemSetChangeEvent() {
		
		// Se crea el evento.
		logger.debug("Se instancia el evento 'ItemSetChangeEvent'.");
		ItemSetChangeEvent itemSetChangeEvent = new ItemSetChangeEvent() {
			private static final long serialVersionUID = 2380023876174313182L;
			@Override
			public Container getContainer() {
				return MyContainer.this;
			}
		};
		
		// Se envia el evento a cada listener
		logger.debug(String.format("Se envia el evento a %d listeners 'ItemSetChangeListener'", itemSetChangeListeners.size()));
		synchronized (itemSetChangeListeners) {
			for (ItemSetChangeListener itemSetChangeListener : itemSetChangeListeners) {
				itemSetChangeListener.containerItemSetChange(itemSetChangeEvent);
			}
		}
		
	}
	
	@Override
	public void addItemSetChangeListener(ItemSetChangeListener listener) {
		synchronized (itemSetChangeListeners) {
			itemSetChangeListeners.add(listener);
		}
	}

	@Override
	@Deprecated
	public void addListener(ItemSetChangeListener listener) {
		addItemSetChangeListener(listener);
	}

	@Override
	public void removeItemSetChangeListener(ItemSetChangeListener listener) {
		synchronized (itemSetChangeListeners) {
			itemSetChangeListeners.remove(listener);
		}
	}

	@Override
	@Deprecated
	public void removeListener(ItemSetChangeListener listener) {
		removeItemSetChangeListener(listener);	
	}
	
}
