package aprendevaadin.prueba08.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import aprendevaadin.prueba08.model.internal.MyData;
import aprendevaadin.prueba08.model.internal.MyIdentifier;

abstract public class MyModel {
	
	static public IMyModel INSTANCE;
	
	static {
		synchronized (MyModel.class) {
			MyModel.INSTANCE = new MyModelImpl();
		}
	}

	static private class MyModelImpl implements IMyModel {

		private HashMap<IMyIdentifier, MyData> map = new HashMap<>();
		
		private MyModelImpl() {
			for (int i = 0; i < 10; i++) {
				MyIdentifier myDataIdentifier = new MyIdentifier(i);
				MyData myData = new MyData(String.format("Descripcion %d", i));
				map.put(myDataIdentifier, myData);
			}
		}

		@Override
		public Set<IMyIdentifier> getAllIdentifiers() {
			return Collections.unmodifiableSet(map.keySet());
		}

		@Override
		public IMyData getData(IMyIdentifier identifier) {
			return map.get(new MyIdentifier(identifier));
		}
		
		@Override
		public IMyIdentifier buildIdentifier(long id) {
			return new MyIdentifier(id);
		}
		
	}
	
}
