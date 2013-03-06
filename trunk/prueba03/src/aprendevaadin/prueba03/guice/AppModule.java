package aprendevaadin.prueba03.guice;

import aprendevaadin.prueba03.conf.DemoConfModule;
import aprendevaadin.prueba03.demo.navigator.NavigatorModule;
import aprendevaadin.prueba03.demo.ui.DemoUIModule;
import aprendevaadin.prueba03.demo.view.ViewModule;
import aprendevaadin.prueba03.guice.uiscope.UIScopeModule;

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
