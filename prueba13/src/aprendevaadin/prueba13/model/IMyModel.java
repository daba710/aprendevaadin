package aprendevaadin.prueba13.model;

import java.util.Set;

public interface IMyModel {
	public Set<IMyIdentifier> getAllIdentifiers();
	public IMyData getData(IMyIdentifier identifier);
	public IMyIdentifier buildIdentifier(long identifier);
}
