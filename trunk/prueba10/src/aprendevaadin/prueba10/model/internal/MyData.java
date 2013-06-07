package aprendevaadin.prueba10.model.internal;

import aprendevaadin.prueba10.model.IMyData;

public class MyData implements IMyData {

	private long value;
	private long counter;
	private String description;
	
	public MyData(long initialValue) {
		this.value = initialValue;
		this.description = String.format("Numero de veces que se actualiza esta valor: %d", counter);
	}
	
	@Override
	public long getValue() {
		return value;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	public void add(long addValue) {
		this.description = String.format("Numero de veces que se actualiza esta valor: %d", ++counter);
	}

}
