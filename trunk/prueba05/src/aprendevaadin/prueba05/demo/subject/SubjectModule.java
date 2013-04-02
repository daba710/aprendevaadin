package aprendevaadin.prueba05.demo.subject;

import aprendevaadin.prueba05.guice.uiscope.UIScoped;

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
