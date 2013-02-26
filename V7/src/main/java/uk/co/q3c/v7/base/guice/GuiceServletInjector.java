package uk.co.q3c.v7.base.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.guice.aop.ShiroAopModule;
import org.apache.shiro.mgt.SecurityManager;

import uk.co.q3c.v7.base.config.IniModule;
import uk.co.q3c.v7.base.config.V7Ini;
import uk.co.q3c.v7.base.guice.uiscope.UIScopeModule;
import uk.co.q3c.v7.base.shiro.DefaultShiroWebModule;
import uk.co.q3c.v7.base.shiro.V7ShiroVaadinModule;
import uk.co.q3c.v7.demo.dao.DemoDAOModule;
import uk.co.q3c.v7.demo.ui.DemoUIModule;
import uk.co.q3c.v7.demo.view.DemoViewModule;
import uk.co.q3c.v7.persist.orient.db.OrientDbModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletInjector extends GuiceServletContextListener {
	
	private Injector injector;

	private final ThreadLocal<ServletContext> ctx = new ThreadLocal<ServletContext>();

	@Override
	protected Injector getInjector() {
		
		V7Ini ini = new V7Ini();
		ini.load(); // exceptions are all handled in the load method
		
		injector = Guice.createInjector(
				new DefaultShiroWebModule(ctx.get()), 
				new V7ShiroVaadinModule(),
				new ShiroAopModule(), 
				new BaseModule(), 
				new DemoViewModule(), 
				new UIScopeModule(), 
				new DemoUIModule(), 
				new IniModule(), 
				new DemoDAOModule(), 
				new OrientDbModule(ini)
			);

		// The SecurityManager binding is in ShiroWebModule, and therefore DemoWebShiroModule. By default the binding is to DefaultWebSecurityManager
		SecurityManager securityManager = injector.getInstance(SecurityManager.class);
		SecurityUtils.setSecurityManager(securityManager);

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
