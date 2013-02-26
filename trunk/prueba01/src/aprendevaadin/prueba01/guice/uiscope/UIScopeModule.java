package aprendevaadin.prueba01.guice.uiscope;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class UIScopeModule extends AbstractModule {
	
	private final UIScope uiScope;

	public UIScopeModule() {
		uiScope = new UIScope();
	}

	@Override
	public void configure() {
		bindScope();
		bindUIKeyProvider();
	}

	protected void bindScope() {
		// registra el scope
		bindScope(UIScoped.class, uiScope);
		
		// hace que el Scope sea inyectable
		bind(UIScope.class).annotatedWith(Names.named("UIScope")).toInstance(uiScope);
	}

	protected void bindUIKeyProvider() {
		bind(UIKeyProvider.class);
	}

}