package aprendevaadin.prueba04.demo.ui;

import java.util.Map;

import aprendevaadin.prueba04.conf.A;
import aprendevaadin.prueba04.demo.navigator.IViewNavigatorEvent;
import aprendevaadin.prueba04.demo.navigator.IViewNavigatorListener;
import aprendevaadin.prueba04.demo.navigator.IViewNavigatorService;
import aprendevaadin.prueba04.demo.view.StartView;
import aprendevaadin.prueba04.ui.ScopedUI;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class DemoUI extends ScopedUI implements IViewNavigatorListener {
	
	private static final long serialVersionUID = 8760773662009662550L;

	@Inject
	@Named(A.title)
	protected String title;
	
	@Inject
	protected IViewNavigatorService viewNavigatorService;
	
	@Inject
	protected Map<String, View> views;
	
	private Navigator navigator;

	@Override
	protected void init(VaadinRequest request) {
		
		// Se le asigna un titulo a la pagina
		getPage().setTitle(title);

		// Se crea un Content Root Layout para el UI
		Layout content = new VerticalLayout();
		content.setSizeFull();
		setContent(content);

		// Se crea una instancia de un ViewDisplay que utilizara el contenerdor anterior
		ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(content);

		// Se crea el navegador
		navigator = new Navigator(this, viewDisplay);

		// Se carga en el navegador las vistas inyectadas
		for (Map.Entry<String,View> entry : views.entrySet()) {
			navigator.addView(entry.getKey(), entry.getValue());
		}
		
		// Se selecciona la vista inicial.
		navigator.navigateTo(StartView.VIEW_KEY);
		
		// Se instala el listener del navegador
		viewNavigatorService.addListener(this);
	}

	@Override
	public void newViewSelected(IViewNavigatorEvent event) {
		navigator.navigateTo(event.getViewKey());
	}
	
}