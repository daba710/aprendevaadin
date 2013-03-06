package aprendevaadin.prueba04.guice;

import com.google.inject.servlet.ServletModule;

public class BaseModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/*").with(GuideVaadinServlet.class);
	}

}