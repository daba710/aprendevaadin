package aprendevaadin.prueba12.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import aprendevaadin.prueba12.model.impl.MyData;
import aprendevaadin.prueba12.model.impl.MyIdentifier;

public class MyModel implements IMyModel {
	
	static final int REFRESH_ROW_TIMEOUT = 2000;
	
	static final int REFRESH_ROW_COLLECTION_COUNT = 4;
	
	private TreeMap<IMyIdentifier, MyData> map = new TreeMap<>();
	private MyIdentifier root;
	private long counter = 0;

	volatile private Thread thread;

	private List<IModelTracker> trackers = new LinkedList<>();
	
	public MyModel() {
		// Se crea el modelo con los valores iniciales.
		initModel();
		// Se lanza la tarea que realizara los cambios.
		startTask();
	}
	
	private void initModel() {
		addRoot();
		MyData myRootData = (MyData) getData(root);
		for (int i = 0; i < 10; i++) {
			MyIdentifier myParentIdentifier = addNode(root);
			myRootData.addChild(myParentIdentifier);
			MyData myParentData = (MyData) getData(myParentIdentifier);
			for (int j = 0; j < 3; j++) {
				myParentData.addChild(addNode(myParentIdentifier));
			}
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
				myData.update();
				// Se notifican los cambios.
				fireChangedNode(myIdentifier);
			}
		}
	}
	
	private void updateRowCollection() {
		
		synchronized (map) {
			// Primero se borra la mas antigua
			
			
			// Segundo se incorpora un nodo nuevo
			MyData myRootData = (MyData) getData(root);
			MyIdentifier myParentIdentifier = addNode(root);
			myRootData.addChild(myParentIdentifier);
			MyData myParentData = (MyData) getData(myParentIdentifier);
			for (int j = 0; j < 3; j++) {
				myParentData.addChild(addNode(myParentIdentifier));
			}
			
			// Se envia el evento
			fireChangedModel();
		}
		
	}
	
	private void removeNode(MyIdentifier myIdentifier) {
	}
	
	private void addRoot() {
		MyIdentifier myIdentifier = new MyIdentifier(counter++);
		MyData myData = new MyData(null, myIdentifier.getId());
		map.put(myIdentifier, myData);
		root = myIdentifier;
	}
	
	private MyIdentifier addNode(MyIdentifier parent) {
		MyIdentifier myIdentifier = new MyIdentifier(counter++);
		MyData myData = new MyData(parent, myIdentifier.getId());
		map.put(myIdentifier, myData);
		return myIdentifier;
	}
	
	public IMyIdentifier getRoot() {
		return root;
	}
	
	public Set<IMyIdentifier> getChilds(IMyIdentifier myIdentifier) {
		MyData data = map.get(myIdentifier);
		if (data != null) {
			Set<IMyIdentifier> ret = new TreeSet<>();
			for (MyIdentifier tmpIdentifier : data.getChilds()) {
				ret.add(tmpIdentifier);
			}
			return Collections.unmodifiableSet(ret);
		}
		return null;
	}
	
	public IMyData getData(IMyIdentifier myIdentifier) {
		return map.get(myIdentifier);
	}
	
	public IMyIdentifier getParent(IMyIdentifier myIdentifier) {
		MyData data = map.get(myIdentifier);
		if (data != null) {
			return data.getParent();
		}
		return null;
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
	
	private void fireLoadinitialModel() {
		synchronized (trackers) {
			for (IModelTracker modelTracker : trackers) {
				modelTracker.loadInitialModel();
			}
		}
	}

	private void fireChangedNode(IMyIdentifier myIdentifier) {
		synchronized (trackers) {
			for (IModelTracker modelTracker : trackers) {
				modelTracker.changedNode(myIdentifier);
			}
		}
	}

	private void fireChangedModel() {
		synchronized (trackers) {
			for (IModelTracker modelTracker : trackers) {
				modelTracker.changedModel();
			}
		}
	}

}
