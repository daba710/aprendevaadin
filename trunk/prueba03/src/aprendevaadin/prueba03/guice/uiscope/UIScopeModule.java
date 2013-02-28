package aprendevaadin.prueba03.guice.uiscope;

import com.google.inject.AbstractModule;

public class UIScopeModule extends AbstractModule {
	
	@Override
	public void configure() {
		bindScope();
	}

	protected void bindScope() {
		bindScope(UIScoped.class, UIScope.DEFAULT);
	}

}