package aprendevaadin.prueba05.demo.jaas.dao.impl;

import aprendevaadin.prueba05.demo.jaas.dao.IDemoViewExecPermissionData;

public class DemoViewExecPermissionData implements IDemoViewExecPermissionData {

	private String view;
	
	public DemoViewExecPermissionData(String view) {
		this.view = view;
	}

	@Override
	public String getView() {
		return view;
	}


	@Override
	public String toString() {
		return "DemoViewExecPermissionData [view=" + view + "]";
	}
	
}
