package aprendevaadin.prueba04.demo.ui;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

public class DemoUIModule extends AbstractModule {

	@Override
	protected void configure() {
		bindUIProvider();
	}

	protected void bindUIProvider() {
		bind(UIProvider.class).to(DemoUIProvider.class);
	}

	@Provides
	Class<? extends UI> getUI() {
		return DemoUI.class;
	}
	
}
