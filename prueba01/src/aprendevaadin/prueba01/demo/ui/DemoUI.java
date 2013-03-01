package aprendevaadin.prueba01.demo.ui;

import javax.inject.Inject;

import aprendevaadin.prueba01.ui.ScopedUI;

import com.vaadin.server.VaadinRequest;

@SuppressWarnings("serial")
public class DemoUI extends ScopedUI {
	
	@Inject
	protected DemoContent demoContent;

	@Override
	protected void init(VaadinRequest request) {
		setContent(demoContent);
	}

}