package aprendevaadin.prueba01.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;


import aprendevaadin.prueba01.conf.DemoConfModule;
import aprendevaadin.prueba01.demo.ui.DemoUIModule;
import aprendevaadin.prueba01.guice.uiscope.UIScopeModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletInjector extends GuiceServletContextListener {
	
	private Injector injector;

	private final ThreadLocal<ServletContext> ctx = new ThreadLocal<ServletContext>();

	@Override
	protected Injector getInjector() {
		injector = Guice.createInjector(
				new DemoConfModule(),
				new BaseModule(),
				new UIScopeModule(),
				new DemoUIModule()
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
