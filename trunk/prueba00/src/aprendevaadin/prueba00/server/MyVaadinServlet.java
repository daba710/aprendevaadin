package aprendevaadin.prueba00.server;

import javax.servlet.ServletException;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinServlet;

@SuppressWarnings("serial")
public class MyVaadinServlet extends VaadinServlet implements SessionInitListener {
	
	private UIProvider uiProvider;
	
	public MyVaadinServlet() {
		this.uiProvider = new MyUIProvider();
	}

	@Override
	protected void servletInitialized() throws ServletException {
		getService().addSessionInitListener(this);
	}

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		event.getSession().addUIProvider(uiProvider);
	}
	
}
