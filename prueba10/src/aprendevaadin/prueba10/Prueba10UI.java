package aprendevaadin.prueba10;

import aprendevaadin.prueba10.data.MyContainer;
import aprendevaadin.prueba10.model.MyModel;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
@Push(PushMode.MANUAL)
public class Prueba10UI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Table table = new Table("La tabla", new MyContainer(new MyModel()));
		table.setSizeFull();
		table.setSelectable(true);
		
		layout.addComponent(table);
	}

}