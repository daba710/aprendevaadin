package aprendevaadin.prueba05.guice;

import aprendevaadin.prueba05.conf.DemoConfModule;
import aprendevaadin.prueba05.demo.navigator.NavigatorModule;
import aprendevaadin.prueba05.demo.subject.SubjectModule;
import aprendevaadin.prueba05.demo.ui.DemoUIModule;
import aprendevaadin.prueba05.demo.view.ViewModule;
import aprendevaadin.prueba05.guice.uiscope.UIScopeModule;

import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new BaseModule());
		install(new UIScopeModule());
		install(new NavigatorModule());
		install(new ViewModule());
		install(new SubjectModule());
		install(new DemoUIModule());
		install(new DemoConfModule());
	}

}
