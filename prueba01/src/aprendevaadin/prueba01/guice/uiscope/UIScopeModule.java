package aprendevaadin.prueba01.guice.uiscope;

import com.google.inject.AbstractModule;

public class UIScopeModule extends AbstractModule {
	
	private final UIScope uiScope;

	public UIScopeModule() {
		uiScope = new UIScope();
	}

	@Override
	public void configure() {
		bindScope();
	}

	protected void bindScope() {
		bindScope(UIScoped.class, uiScope);
	}

}