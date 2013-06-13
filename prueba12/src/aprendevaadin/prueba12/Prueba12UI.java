package aprendevaadin.prueba12;

import aprendevaadin.prueba12.data.MyContainer;
import aprendevaadin.prueba12.model.MyModel;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class Prueba12UI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Tree tree = new Tree();
		tree.setContainerDataSource(new MyContainer(new MyModel()));
		layout.addComponent(tree);
	}

}