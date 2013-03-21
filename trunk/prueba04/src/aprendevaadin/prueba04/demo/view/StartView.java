package aprendevaadin.prueba04.demo.view;

import javax.inject.Inject;

import aprendevaadin.prueba04.demo.navigator.IViewNavigatorService;
import aprendevaadin.prueba04.guice.uiscope.UIScoped;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
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

		Layout loginLayout = buildLoginForm();
		addComponent(loginLayout);
		setComponentAlignment(loginLayout, Alignment.MIDDLE_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome to the Animal Farm");
	}
	
	private Layout buildLoginForm() {
	    FormLayout layout = new FormLayout();
	    // Create a label which we can use to give feedback to the user
	    final Label feedbackLabel = new Label();

	    // Create input fields for username and password
	    final TextField usernameField = new TextField("Username");
	    final PasswordField passwordField = new PasswordField("Password");

	    // Add the login button
	    Button login = new Button("Login",
            new ClickListener() {
                private static final long serialVersionUID = -5577423546946890721L;
                public void buttonClick(ClickEvent event) {
                    // Try to log in the user when the button is clicked
                    String username = (String) usernameField.getValue();
                    String password = (String) passwordField.getValue();
                    StartView.this.viewNavigator.navigateTo(MainView.VIEW_KEY);
                }
            });

	    layout.addComponent(feedbackLabel);
	    layout.addComponent(usernameField);
	    layout.addComponent(passwordField);
	    layout.addComponent(login);

	    return layout;
	}
	
}
