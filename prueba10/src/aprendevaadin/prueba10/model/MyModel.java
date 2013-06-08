package aprendevaadin.prueba10.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import aprendevaadin.prueba10.model.internal.MyData;
import aprendevaadin.prueba10.model.internal.MyIdentifier;

public class MyModel implements IMyModel {
	
	static final int REFRESH_ROW_TIMEOUT = 2000;
	
	static final int REFRESH_ROW_COLLECTION_COUNT = 4;
	
	private TreeMap<IMyIdentifier, MyData> map = new TreeMap<>();
	private List<IModelTracker> trackers = new LinkedList<>();
	volatile private Thread thread;
	
	public MyModel() {
			
		// Se crea el modelo con los valores iniciales.
		initModel();
		
		// Se lanza una tarea que actualizara cada cierto tiempo los valores del modelo.
		startTask();
	}
	
	private void initModel() {
		for (int i = 0; i < 10; i++) {
			MyIdentifier myDataIdentifier = new MyIdentifier(i);
			MyData myData = new MyData(myDataIdentifier.getId());
			map.put(myDataIdentifier, myData);
		}
	}
	
	private void startTask() {
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int count = 0;
				while(thread != null) {
					// Un pausa
					try {
						Thread.sleep(REFRESH_ROW_TIMEOUT);
					} catch (InterruptedException e) {
					}
					// Se actualizan los valores de las filas
					updateRows();
					// Cada ciertas actualizaciones se elimina la fila mas antigua y se incorpora una nueva.
					count++;
					if ((count % REFRESH_ROW_COLLECTION_COUNT) == 0) {
						updateRowCollection();
					}
				}
				
			}
		});
		thread.start();
	}
	
	private void updateRows() {
		synchronized (map) {
			for (IMyIdentifier myIdentifier : map.keySet()) {
				MyData myData = map.get(myIdentifier);
				myData.add(myIdentifier.getId());
				// Se notifican los cambios.
				fireChangedRow(myIdentifier);
			}
		}
	}
	
	private void updateRowCollection() {
		synchronized (map) {
			IMyIdentifier firstIdentifier = map.firstKey();
			IMyIdentifier lastIdentifier = map.lastKey();
			map.remove(firstIdentifier);
			MyIdentifier newIdentifier = new MyIdentifier(lastIdentifier.getId() + 1);
			MyData newData = new MyData(newIdentifier.getId());
			map.put(newIdentifier, newData);
			fireChangedRowCollection();
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
	
	private void fireLoadinitialModel() {
		synchronized (trackers) {
			for (IModelTracker modelTracker : trackers) {
				modelTracker.loadInitialModel();
			}
		}
	}

	private void fireChangedRow(IMyIdentifier myIdentifier) {
		synchronized (trackers) {
			for (IModelTracker modelTracker : trackers) {
				modelTracker.changedRow(myIdentifier);
			}
		}
	}

	private void fireChangedRowCollection() {
		synchronized (trackers) {
			for (IModelTracker modelTracker : trackers) {
				modelTracker.changedRowCollection();
			}
		}
	}

	public void addModelTracker(IModelTracker tracker) {
		synchronized (trackers) {
			trackers.add(tracker);
			fireLoadinitialModel();
		}
	}

	public void removeModelTracker(IModelTracker tracker) {
		synchronized (trackers) {
			trackers.remove(tracker);
		}
	}
	
	
}
