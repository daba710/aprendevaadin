package aprendevaadin.prueba13.model;

import java.util.List;
import java.util.Set;

public interface IMyModel {
	
	// Container
	public Set<IMyIdentifier> getAllIdentifiers();
	public IMyData getData(IMyIdentifier identifier);
	public int getSize();
	
	public IMyIdentifier buildIdentifier(long identifier);
	
	// Container.Ordered
	public IMyIdentifier getFirstIdentifier();
	public IMyIdentifier getLastIdentifier();
	public IMyIdentifier getNextIdentifier(IMyIdentifier myIdentifier);
	public IMyIdentifier getPreviousIdentifier(IMyIdentifier myIdentifier);
	public boolean isFirstIdentifier(IMyIdentifier myIdentifier);
	public boolean isLastIdentifier(IMyIdentifier myIdentifier);
	
	// Container.Indexed
	public int indexOfIdentifier(Object itemId);
	public IMyIdentifier getIdentifierByIndex(int index);
	public List<IMyIdentifier> getIdentifiers(int startIndex, int numberOfItems);
	
}
