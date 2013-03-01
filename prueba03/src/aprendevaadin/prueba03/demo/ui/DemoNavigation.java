package aprendevaadin.prueba03.demo.ui;

import aprendevaadin.prueba03.demo.view.MainView;
import aprendevaadin.prueba03.demo.view.StartView;

import com.google.inject.Inject;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

public class DemoNavigation implements IViewNavigator {

	private Navigator navigator;
	
	@Inject
	public DemoNavigation(ComponentContainer container, StartView startView, MainView mainView) {
		ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(container);
		navigator = new Navigator(UI.getCurrent(), viewDisplay);
	}

	@Override
	public void navigateTo(String view) {
	}

}
