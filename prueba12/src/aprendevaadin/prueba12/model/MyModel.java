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
	
	private List<IModelTracker> trackers = new LinkedList<>();
	
	public MyModel() {
		// Se crea el modelo con los valores iniciales.
		initModel();
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

	private void fireChangedModel() {
		synchronized (trackers) {
			for (IModelTracker modelTracker : trackers) {
				modelTracker.changedModel();
			}
		}
	}

}
