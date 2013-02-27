package aprendevaadin.prueba03.demo.ui;

import com.google.inject.AbstractModule;
import com.vaadin.server.UIProvider;

public class DemoUIModule extends AbstractModule {

	@Override
	protected void configure() {
		bindUIProvider();
	}

	protected void bindUIProvider() {
		bind(UIProvider.class).to(DemoUIProvider.class);
	}

}
