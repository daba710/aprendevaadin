package aprendevaadin.prueba09.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import aprendevaadin.prueba09.model.internal.MyData;
import aprendevaadin.prueba09.model.internal.MyIdentifier;

abstract public class MyModel {
	
	static final int REFRESH_TIMEOUT = 2000;
	
	static public IMyModel INSTANCE;
	
	static {
		synchronized (MyModel.class) {
			MyModel.INSTANCE = new MyModelImpl();
		}
	}

	static private class MyModelImpl implements IMyModel {

		private HashMap<IMyIdentifier, MyData> map = new HashMap<>();
		private List<IModelTracker> trackers = new LinkedList<>();
		volatile private Thread thread;
		
		private MyModelImpl() {
			
			// Se crea el modelo con los valores iniciales.
			for (int i = 0; i < 10; i++) {
				MyIdentifier myDataIdentifier = new MyIdentifier(i);
				MyData myData = new MyData(myDataIdentifier.getId());
				map.put(myDataIdentifier, myData);
			}
			
			// Se lanza una tarea que actualizara cvada cierto tiempo los valores del modelo.
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while(thread != null) {
						// Un pausa
						try {
							Thread.sleep(REFRESH_TIMEOUT);
						} catch (InterruptedException e) {
						}
						// Se actualizan los datos del modelo
						synchronized (map) {
							for (IMyIdentifier myIdentifier : map.keySet()) {
								MyData myData = map.get(myIdentifier);
								myData.add(myIdentifier.getId());
							}
						}
						// Se notifican los cambios.
						fireUpdatedModel();
					}
					
				}
			});
			thread.start();
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
		
		private void fireLoadinitialModel() {
			synchronized (trackers) {
				for (IModelTracker modelTracker : trackers) {
					modelTracker.loadInitialModel();
				}
			}
		}

		private void fireUpdatedModel() {
			synchronized (trackers) {
				for (IModelTracker modelTracker : trackers) {
					modelTracker.updatedModel();
				}
			}
		}

		@Override
		public void addModelTracker(IModelTracker tracker) {
			synchronized (trackers) {
				trackers.add(tracker);
				fireLoadinitialModel();
			}
		}

		@Override
		public void removeModelTracker(IModelTracker tracker) {
			synchronized (trackers) {
				trackers.remove(tracker);
			}
		}
		
	}
	
}
