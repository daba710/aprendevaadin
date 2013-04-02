package aprendevaadin.prueba05.demo.navigator;

import aprendevaadin.prueba05.guice.uiscope.UIScoped;

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
