package aprendevaadin.prueba13.model;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;

import aprendevaadin.prueba13.model.internal.MyData;
import aprendevaadin.prueba13.model.internal.MyIdentifier;

public class MyModel implements IMyModel {
	
	static final int ROWS = 1000;
	
	private TreeMap<IMyIdentifier, MyData> map = new TreeMap<>();
	
	public MyModel() {
			
		// Se crea el modelo con los valores iniciales.
		initModel();
	}
	
	private void initModel() {
		for (int i = 0; i < ROWS; i++) {
			MyIdentifier myDataIdentifier = new MyIdentifier(i);
			MyData myData = new MyData(myDataIdentifier.getId());
			map.put(myDataIdentifier, myData);
		}
	}

	//////////////////////////////////////////////////////////////////
	// Container
	//////////////////////////////////////////////////////////////////

	public Set<IMyIdentifier> getAllIdentifiers() {
		return Collections.unmodifiableSet(map.keySet());
	}

	public IMyData getData(IMyIdentifier identifier) {
		return map.get(new MyIdentifier(identifier));
	}

	public int getSize() {
		return map.size();
	}
	
	
	//////////////////////////////////////////////////////////////////
	// Container.Ordered
	//////////////////////////////////////////////////////////////////
	
	public IMyIdentifier getFirstIdentifier() {
		return map.firstKey();
	}
	
	public IMyIdentifier getLastIdentifier() {
		return map.lastKey();
	}
	
	public IMyIdentifier getNextIdentifier(IMyIdentifier myIdentifier) {
		// Obtenemos un identificador ascendente
		Iterator<IMyIdentifier> iterator = map.navigableKeySet().iterator();
		
		// Se busca el identificador del parametro
		while(iterator.hasNext()) {
			IMyIdentifier currIdentifier = iterator.next();
			if (currIdentifier != null && currIdentifier.equals(myIdentifier)) {
				// Si lo encontramos, se retorna el siguiente (aunque sea null).
				return iterator.next();
			}
		}
		// No se ha encontrado
		return null;
	}
	
	public IMyIdentifier getPreviousIdentifier(IMyIdentifier myIdentifier) {
		// Obtenemos un identificador descendente
		Iterator<IMyIdentifier> iterator = map.navigableKeySet().descendingIterator();
		
		// Se busca el identificador del parametro
		while(iterator.hasNext()) {
			IMyIdentifier currIdentifier = iterator.next();
			if (currIdentifier != null && currIdentifier.equals(myIdentifier)) {
				// Si lo encontramos, se retorna el siguiente (aunque sea null).
				return iterator.next();
			}
		}
		// No se ha encontrado
		return null;
	}
	
	public boolean isFirstIdentifier(IMyIdentifier myIdentifier) {
		return myIdentifier.equals(getFirstIdentifier());
	}
	
	public boolean isLastIdentifier(IMyIdentifier myIdentifier) {
		return myIdentifier.equals(getLastIdentifier());
	}

	
	//////////////////////////////////////////////////////////////////
	// Container.Indexed
	//////////////////////////////////////////////////////////////////
	
	public int indexOfIdentifier(Object itemId) {
	}
	
	public IMyIdentifier getIdentifierByIndex(int index) {
		
	}
	
	public List<IMyIdentifier> getIdentifiers(int startIndex, int numberOfItems) {
		
	}

}
