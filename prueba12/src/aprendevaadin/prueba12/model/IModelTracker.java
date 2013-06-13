package aprendevaadin.prueba12.model;

public interface IModelTracker {
	public void loadInitialModel();
	public void changedNode(IMyIdentifier myIdentifier);
	public void changedModel();
}
