package aprendevaadin.prueba03.demo.ui;

import javax.inject.Inject;


import aprendevaadin.prueba03.guice.uiscope.UIKeyProvider;
import aprendevaadin.prueba03.ui.ScopedUIProvider;

import com.google.inject.Injector;
import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.ui.UI;

public class DemoUIProvider extends ScopedUIProvider {

	private static final long serialVersionUID = -8996081483974304448L;
	
	@Inject
	protected Class<? extends UI> uiClass;
	
	@Inject
	protected DemoUIProvider(UIKeyProvider uiKeyProvider, Injector injector) {
		super(uiKeyProvider, injector);
	}

	@Override
	public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
		return uiClass;
	}

}