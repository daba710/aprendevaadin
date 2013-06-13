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

public class MyContainer implements Container, Container.Hierarchical, Container.ItemSetChangeNotifier {
	
	static public Logger logger = LoggerFactory.getLogger(MyContainer.class);
	
	private static final long serialVersionUID = -7259357335150148384L;
	
	private class ModelTracker implements IModelTracker {

		@Override
		public void loadInitialModel() {
			logger.debug(String.format("loadInitialModel()"));
			// Este evento llega demasiado pronto cuando la tabla aun no esta ensamblada asi que no se tiene en cuenta.
		}

		@Override
		public void changedModel() {
			logger.debug(String.format("changedModel()"));
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
		return new ArrayList<IMyIdentifier>();
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
		return 0;
	}

	@Override
	public boolean containsId(Object itemId) {
		if (itemId instanceof IMyIdentifier) {
			IMyIdentifier myIdentifier = (IMyIdentifier) itemId;
			return myModel.getData(myIdentifier) != null;
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
	// Container.Hierarchical
	/////////////////////////////////////////////////////////////
	
	@Override
	public Collection<?> getChildren(Object itemId) {
		if (itemId instanceof IMyIdentifier) {
			IMyIdentifier myIdentifier = (IMyIdentifier) itemId;
			return myModel.getChilds(myIdentifier);
		} else {
			return null;
		}
	}

	@Override
	public Object getParent(Object itemId) {
		if (itemId instanceof IMyIdentifier) {
			IMyIdentifier myIdentifier = (IMyIdentifier) itemId;
			return myModel.getParent(myIdentifier);
		} else {
			return null;
		}
	}

	@Override
	public Collection<?> rootItemIds() {
		List<IMyIdentifier> ids = new ArrayList<>();
			IMyIdentifier root = myModel.getRoot();
			if (root != null) {
				ids.add(root);
			}
//		Collections.sort(ids);
		return Collections.unmodifiableList(ids);
	}

	@Override
	public boolean setParent(Object itemId, Object newParentId) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean areChildrenAllowed(Object itemId) {
		return true;
	}

	@Override
	public boolean setChildrenAllowed(Object itemId, boolean areChildrenAllowed) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isRoot(Object itemId) {
		if (itemId instanceof IMyIdentifier) {
			IMyIdentifier myIdentifier = (IMyIdentifier) itemId;
			IMyIdentifier root = myModel.getRoot();
			if (root != null) {
				return myIdentifier.equals(root);
			}
		}
		return false;
	}

	@Override
	public boolean hasChildren(Object itemId) {
		if (itemId instanceof IMyIdentifier) {
			IMyIdentifier groupIdentifier = (IMyIdentifier) itemId;
			return myModel.getChilds(groupIdentifier).size() > 0;
		}
		return false;
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
