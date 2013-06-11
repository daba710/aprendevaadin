package aprendevaadin.prueba11;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@Theme("mytheme")
@SuppressWarnings("serial")
public class Prueba11UI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		
		// Se crear el layout principal y se configura que tenga mnargen y 
		// que ocupe todo el espacio disponible,
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSizeFull();
		
		// Se crea la etiqueta con la que queremos hacer pruebas
		Label label = new Label("Hola mundo.");
		label.addStyleName("status-notification");
		
		layout.addComponent(label);
		layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

		// Se asigna el layout principal al UI
		setContent(layout);
	}

}