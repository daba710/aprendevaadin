package aprendevaadin.prueba09.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import aprendevaadin.prueba09.model.IMyData;
import aprendevaadin.prueba09.model.IMyIdentifier;
import aprendevaadin.prueba09.model.MyModel;
import aprendevaadin.prueba09.model.internal.MyIdentifier;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

public class MyContainer implements Container {
	
	private static final long serialVersionUID = -7259357335150148384L;

	@Override
	public Item getItem(Object itemId) {
		if (itemId instanceof IMyIdentifier) {
			IMyIdentifier dataIdentifier = (IMyIdentifier) itemId;
			IMyData myData = MyModel.INSTANCE.getData(dataIdentifier);
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
		for (IMyIdentifier myIdentifier : MyModel.INSTANCE.getAllIdentifiers()) {
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
		return MyModel.INSTANCE.getAllIdentifiers().size();
	}

	@Override
	public boolean containsId(Object itemId) {
		if (itemId instanceof IMyIdentifier) {
			IMyIdentifier dataIdentifier = (IMyIdentifier) itemId;
			return MyModel.INSTANCE.getData(dataIdentifier) != null;
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

}
