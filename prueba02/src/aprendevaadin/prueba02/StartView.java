package aprendevaadin.prueba02;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

/**
 * Esta vista sera la utilizada para mostrarse al inicio de entrar en la aplicación.
 * 
 * Preenta un unico boton que al pulsarse nos da acceso a la siguiente vista (vista principal).
 */
public class StartView extends VerticalLayout implements View {

	private static final long serialVersionUID = -546686842331920059L;

	public StartView() {
		setSizeFull();

		Button button = new Button("Go to Main View",
				new Button.ClickListener() {

					private static final long serialVersionUID = 551692033844237260L;

					@Override
					public void buttonClick(ClickEvent event) {
						Prueba02UI.getInstance().getNavigator().navigateTo(Prueba02UI.MAINVIEW);
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

