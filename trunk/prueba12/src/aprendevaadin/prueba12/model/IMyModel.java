package aprendevaadin.prueba12.model;

import java.util.Set;

public interface IMyModel {
	public IMyIdentifier getRoot();
	public Set<IMyIdentifier> getChilds(IMyIdentifier myIdentifier);
	public IMyData getData(IMyIdentifier myIdentifier);
	public IMyIdentifier getParent(IMyIdentifier myIdentifier);
	public void addModelTracker(IModelTracker tracker);
	public void removeModelTracker(IModelTracker tracker);
}
