package aprendevaadin.prueba13.model;

import java.util.Collections;
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
	
	public Set<IMyIdentifier> getAllIdentifiers() {
		return Collections.unmodifiableSet(map.keySet());
	}

	public IMyData getData(IMyIdentifier identifier) {
		return map.get(new MyIdentifier(identifier));
	}

	public IMyIdentifier buildIdentifier(long id) {
		return new MyIdentifier(id);
	}
	
}
