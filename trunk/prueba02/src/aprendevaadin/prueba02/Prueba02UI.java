package aprendevaadin.prueba02;

import aprendevaadin.prueba02.view.MainView;
import aprendevaadin.prueba02.view.StartView;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class Prueba02UI extends UI {
	
	private static final long serialVersionUID = 8760773662009662550L;

	private Navigator navigator;

	public static final String MAINVIEW = "main";
	
	static public Prueba02UI getInstance() {
		return (Prueba02UI) UI.getCurrent();
	}
	
	public Navigator getNavigator() {
		return navigator;
	}
	
	@Override
	protected void init(VaadinRequest request) {
		
		getPage().setTitle("Navigation Example");

		// Se crea un Content Root Layout para el UI
		Layout content = new VerticalLayout();
		content.setSizeFull();
		setContent(content);

		// Se crea una instancia de un ViewDisplay que utilizara el contenerdor anterior
		ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(content);

		// Se crea un control de navegacion sobre un visor de vistas.
		navigator = new Navigator(UI.getCurrent(), viewDisplay);

		// Create and register the views
		navigator.addView("", new StartView());
		navigator.addView(MAINVIEW, new MainView());
	}
	
}