package aprendevaadin.prueba04.guice;

import aprendevaadin.prueba04.conf.DemoConfModule;
import aprendevaadin.prueba04.demo.navigator.NavigatorModule;
import aprendevaadin.prueba04.demo.ui.DemoUIModule;
import aprendevaadin.prueba04.demo.view.ViewModule;
import aprendevaadin.prueba04.guice.uiscope.UIScopeModule;

import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new BaseModule());
		install(new UIScopeModule());
		install(new NavigatorModule());
		install(new ViewModule());
		install(new DemoUIModule());
		install(new DemoConfModule());
	}

}
