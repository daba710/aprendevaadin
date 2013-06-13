package aprendevaadin.prueba12.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aprendevaadin.prueba12.model.IMyData;

public class MyData implements IMyData {

	private MyIdentifier parent;
	private ArrayList<MyIdentifier> childs = new ArrayList<>();
	
	private long value;
	private int counter = 0;
	
	public MyData(MyIdentifier parent, long initialValue) {
		this.parent = parent;
		this.value = initialValue;
	}
	
	@Override
	public String getName() {
		return String.format("%d (updated %d)", value, counter);
	}
	
	public MyIdentifier getParent() {
		return parent;
	}
	
	public List<MyIdentifier> getChilds() {
		return Collections.unmodifiableList(childs);
	}
	
	public void addChild(MyIdentifier child) {
		childs.add(child);
	}
	
	public void remove(MyIdentifier child) {
		childs.remove(child);
	}
	
	public void update() {
		counter++;
	}
	
}
