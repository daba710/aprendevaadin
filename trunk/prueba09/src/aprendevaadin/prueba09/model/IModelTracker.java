package aprendevaadin.prueba09.model;

public interface IModelTracker {
	public void loadInitialModel();
	public void changedRow(IMyIdentifier myIdentifier);
	public void changedRowCollection();
}
