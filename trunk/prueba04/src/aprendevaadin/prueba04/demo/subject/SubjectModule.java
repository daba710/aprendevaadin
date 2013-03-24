package aprendevaadin.prueba04.demo.subject;

import aprendevaadin.prueba04.guice.uiscope.UIScoped;

import com.google.inject.AbstractModule;

public class SubjectModule extends AbstractModule {

	@Override
	protected void configure() {
		bindPrincipal();
	}

	protected void bindPrincipal() {
		bind(ISubjectService.class).to(SubjectImpl.class).in(UIScoped.class);
	}

}
