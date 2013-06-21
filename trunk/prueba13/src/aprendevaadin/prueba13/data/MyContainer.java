package aprendevaadin.prueba13.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aprendevaadin.prueba13.model.IMyData;
import aprendevaadin.prueba13.model.IMyIdentifier;
import aprendevaadin.prueba13.model.IMyModel;
import aprendevaadin.prueba13.model.internal.MyIdentifier;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

public class MyContainer implements Container, Container.Ordered, Container.Indexed {
	
	static public Logger logger = LoggerFactory.getLogger(MyContainer.class);
	
	private static final long serialVersionUID = -7259357335150148384L;
	
	private IMyModel myModel;
	
	public MyContainer(IMyModel myModel) {
		this.myModel = myModel;
	}

	/////////////////////////////////////////////////////////////
	// Container
	/////////////////////////////////////////////////////////////
	
	@Override
	/*
	 * SI SE UTILIZA
	 */
	public Item getItem(Object itemId) {
		// Espiamos la interaccion del modelo.
		logger.debug(String.format("Container.getItem(%s): %s", itemId.toString(), "???"));
		// Implementacion del modelo
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
		// Espiamos la interaccion del modelo.
		logger.debug(String.format("Container.getItemIds(): %s", "???"));
		// Implementacion del modelo
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
	/*
	 * SI SE UTILIZA
	 */
	public int size() {
		// Espiamos la interaccion del modelo.
		logger.debug(String.format("Container.size(): %d", myModel.getAllIdentifiers().size()));
		// Implementacion del modelo
		return myModel.getSize();
	}

	@Override
	public boolean containsId(Object itemId) {
		// Espiamos la interaccion del modelo.
		logger.debug(String.format("Container.containsId(%s): %s", itemId.toString(), "???"));
		// Implementacion del modelo
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
	// Container.Ordered
	/////////////////////////////////////////////////////////////

	@Override
	public Object firstItemId() {
		// Espiamos la interaccion del modelo.
		logger.debug(String.format("Container.Ordered.firstItemId(): %s", "???"));
		// Implementacion del modelo
		return myModel.getFirstIdentifier();
	}

	@Override
	public Object lastItemId() {
		// Espiamos la interaccion del modelo.
		logger.debug(String.format("Container.Ordered.lastItemId(): %s", "???"));
		// Implementacion del modelo
		return myModel.getLastIdentifier();
	}

	@Override
	public Object nextItemId(Object itemId) {
		// Espiamos la interaccion del modelo.
		logger.debug(String.format("Container.Ordered.nextItemId(%s): %s", itemId.toString(), "???"));
		// Implementacion del modelo
		return myModel.getNextIdentifier((MyIdentifier) itemId);
	}

	@Override
	public Object prevItemId(Object itemId) {
		// Espiamos la interaccion del modelo.
		logger.debug(String.format("Container.Ordered.prevItemId(%s): %s", itemId.toString(), "???"));
		// Implementacion del modelo
		return myModel.getPreviousIdentifier((MyIdentifier) itemId);
	}

	@Override
	public boolean isFirstId(Object itemId) {
		// Espiamos la interaccion del modelo.
		logger.debug(String.format("Container.Ordered.isFirstId(%s): %s", itemId.toString(), "???"));
		// Implementacion del modelo
		return myModel.isFirstIdentifier((MyIdentifier) itemId);
	}

	@Override
	public boolean isLastId(Object itemId) {
		// Espiamos la interaccion del modelo.
		logger.debug(String.format("Container.Ordered.prevItemId(%s): %s", itemId.toString(), "???"));
		// Implementacion del modelo
		return myModel.isLastIdentifier((MyIdentifier) itemId);
	}

	@Override
	public Object addItemAfter(Object previousItemId) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Item addItemAfter(Object previousItemId, Object newItemId) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/////////////////////////////////////////////////////////////
	// Container.Indexed
	/////////////////////////////////////////////////////////////

	@Override
	public int indexOfId(Object itemId) {
		// Espiamos la interaccion del modelo.
		logger.debug(String.format("Container.Indexed.indexOfId(%s): %s", itemId.toString(), "???"));
		// Implementacion del modelo
		return myModel.indexOfIdentifier((MyIdentifier) itemId);
	}

	@Override
	/*
	 * SI SE UTILIZA
	 */
	public Object getIdByIndex(int index) {
		// Espiamos la interaccion del modelo.
		logger.debug(String.format("Container.Indexed.getIdByIndex(%d): %s", index, "???"));
		// Implementacion del modelo
		return myModel.getIdentifierByIndex(index);
	}

	@Override
	/*
	 * SI SE UTILIZA
	 */
	public List<?> getItemIds(int startIndex, int numberOfItems) {
		// Espiamos la interaccion del modelo.
		logger.debug(String.format("Container.Indexed.getItemIds(%d, %d): %s", startIndex, numberOfItems, "???"));
		// Implementacion del modelo
		return myModel.getIdentifiers(startIndex, numberOfItems);
	}

	@Override
	public Object addItemAt(int index) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Item addItemAt(int index, Object newItemId) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

}
