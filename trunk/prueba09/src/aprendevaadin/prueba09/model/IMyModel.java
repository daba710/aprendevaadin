package aprendevaadin.prueba09.model;

import java.util.Set;

public interface IMyModel {
	public Set<IMyIdentifier> getAllIdentifiers();
	public IMyData getData(IMyIdentifier identifier);
	public IMyIdentifier buildIdentifier(long identifier);
	public void addModelTracker(IModelTracker tracker);
	public void removeModelTracker(IModelTracker tracker);
}
