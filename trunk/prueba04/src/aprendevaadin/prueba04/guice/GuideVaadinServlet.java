package aprendevaadin.prueba04.guice;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;


import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinServlet;

@SuppressWarnings("serial")
@Singleton
public class GuideVaadinServlet extends VaadinServlet implements SessionInitListener {
	
	@Inject
	private UIProvider uiProvider;
	
	@Override
	protected void servletInitialized() throws ServletException {
		getService().addSessionInitListener(this);
	}

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		event.getSession().addUIProvider(uiProvider);
	}
	
}
