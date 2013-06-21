package aprendevaadin.prueba13;

import aprendevaadin.prueba13.data.MyContainer;
import aprendevaadin.prueba13.model.MyModel;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class Prueba13UI extends UI {

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