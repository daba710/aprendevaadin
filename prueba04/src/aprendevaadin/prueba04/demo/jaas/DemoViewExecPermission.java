package aprendevaadin.prueba04.demo.jaas;

import java.security.BasicPermission;

public class DemoViewExecPermission extends BasicPermission {

	private static final long serialVersionUID = 8874457001236773079L;

	public DemoViewExecPermission(String name) {
		super(name);
	}

}
