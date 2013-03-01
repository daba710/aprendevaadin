package aprendevaadin.prueba03.demo.navigator;

import aprendevaadin.prueba03.guice.uiscope.UIScoped;

import com.google.inject.AbstractModule;

public class NavigatorModule extends AbstractModule {

	@Override
	protected void configure() {
		bindNavigator();
	}

	protected void bindNavigator() {
		bind(IViewNavigatorService.class).to(ViewNavigatorImpl.class).in(UIScoped.class);
	}

}
