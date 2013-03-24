package aprendevaadin.prueba04.demo.subject;

import aprendevaadin.prueba04.guice.uiscope.UIScoped;

import com.google.inject.AbstractModule;

public class PrincipalModule extends AbstractModule {

	@Override
	protected void configure() {
		bindPrincipal();
	}

	protected void bindPrincipal() {
		bind(IPrincipalService.class).to(PrincipalImpl.class).in(UIScoped.class);
	}

}
