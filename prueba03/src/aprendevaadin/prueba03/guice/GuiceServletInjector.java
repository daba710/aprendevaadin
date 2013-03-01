package aprendevaadin.prueba03.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;


import aprendevaadin.prueba03.conf.DemoConfModule;
import aprendevaadin.prueba03.demo.navigator.NavigatorModule;
import aprendevaadin.prueba03.demo.ui.DemoUIModule;
import aprendevaadin.prueba03.demo.view.ViewModule;
import aprendevaadin.prueba03.guice.uiscope.UIScopeModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletInjector extends GuiceServletContextListener {
	
	private Injector injector;

	private final ThreadLocal<ServletContext> ctx = new ThreadLocal<ServletContext>();

	@Override
	protected Injector getInjector() {
		injector = Guice.createInjector(
				new BaseModule(),
				new UIScopeModule(),
				new NavigatorModule(),
				new ViewModule(),
				new DemoUIModule(),
				new DemoConfModule()
			);
		return injector;
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		final ServletContext servletContext = servletContextEvent.getServletContext();
		ctx.set(servletContext);
		super.contextInitialized(servletContextEvent);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		super.contextDestroyed(servletContextEvent);
		ctx.remove();
	}
	
}
