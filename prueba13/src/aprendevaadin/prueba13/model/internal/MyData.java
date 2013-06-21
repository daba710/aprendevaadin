package aprendevaadin.prueba13.model.internal;

import aprendevaadin.prueba13.model.IMyData;

public class MyData implements IMyData {

	private String description;
	
	public MyData(long id) {
		this.description = String.format("Descripcion del %d", id);
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
}
