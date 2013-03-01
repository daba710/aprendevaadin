package aprendevaadin.prueba03.demo.ui;

import aprendevaadin.prueba03.conf.A;
import aprendevaadin.prueba03.demo.view.MainView;
import aprendevaadin.prueba03.demo.view.StartView;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class DemoUI extends UI {
	
	private static final long serialVersionUID = 8760773662009662550L;

	@Inject
	@Named(A.title)
	protected String title;
	
	@Override
	protected void init(VaadinRequest request) {
		
		getPage().setTitle(title);

		// Se crea un Content Root Layout para el UI
		Layout content = new VerticalLayout();
		content.setSizeFull();
		setContent(content);

		// Se crea una instancia de un ViewDisplay que utilizara el contenerdor anterior
		ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(content);

		// Se crea un control de navegacion sobre un visor de vistas.
		navigator = new Navigator(UI.getCurrent(), viewDisplay);

		// Create and register the views
		navigator.addView("", startView);
		navigator.addView(IViewNavigator.MAINVIEW, mainView);
		
	}
	
}