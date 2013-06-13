package aprendevaadin.prueba12.model.impl;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import aprendevaadin.prueba12.model.IMyData;

public class MyData implements IMyData {

	private MyIdentifier parent;
	private TreeSet<MyIdentifier> childs = new TreeSet<>();
	
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
	
	public SortedSet<MyIdentifier> getChilds() {
		return Collections.unmodifiableSortedSet(childs);
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
