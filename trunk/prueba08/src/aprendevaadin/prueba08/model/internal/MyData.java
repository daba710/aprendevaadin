package aprendevaadin.prueba08.model.internal;

import aprendevaadin.prueba08.model.IMyData;

public class MyData implements IMyData {
	
	private String description;
	
	public MyData(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
