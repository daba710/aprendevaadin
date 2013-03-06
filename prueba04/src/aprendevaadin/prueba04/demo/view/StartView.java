package aprendevaadin.prueba04.demo.view;

import javax.inject.Inject;

import aprendevaadin.prueba04.demo.navigator.IViewNavigatorService;
import aprendevaadin.prueba04.guice.uiscope.UIScoped;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@UIScoped
public class StartView extends VerticalLayout implements View {

	private static final long serialVersionUID = -546686842331920059L;

	public static final String VIEW_KEY = "";

	final private IViewNavigatorService viewNavigator;

	@Inject
	public StartView(IViewNavigatorService viewNavigation) {
		
		this.viewNavigator = viewNavigation;
		
		setSizeFull();

		Button button = new Button("Go to Main View",
				new Button.ClickListener() {

					private static final long serialVersionUID = 551692033844237260L;

					@Override
					public void buttonClick(ClickEvent event) {
						StartView.this.viewNavigator.navigateTo(MainView.VIEW_KEY);
					}
				});
		addComponent(button);
		setComponentAlignment(button, Alignment.MIDDLE_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome to the Animal Farm");
	}
	
}

