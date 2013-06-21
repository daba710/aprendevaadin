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
	// Container.Ordered
	/////////////////////////////////////////////////////////////

	@Override
	public Object nextItemId(Object itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object prevItemId(Object itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object firstItemId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object lastItemId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFirstId(Object itemId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLastId(Object itemId) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getIdByIndex(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getItemIds(int startIndex, int numberOfItems) {
		// TODO Auto-generated method stub
		return null;
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
