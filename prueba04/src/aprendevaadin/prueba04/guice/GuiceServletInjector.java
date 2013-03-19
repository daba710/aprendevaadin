package aprendevaadin.prueba04.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import aprendevaadin.prueba04.demo.jaas.DemoConfiguration;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletInjector extends GuiceServletContextListener {
	
	private Injector injector;

	private final ThreadLocal<ServletContext> ctx = new ThreadLocal<ServletContext>();

	@Override
	protected Injector getInjector() {
		injector = Guice.createInjector(new AppModule());
		return injector;
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		DemoConfiguration.install();
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
