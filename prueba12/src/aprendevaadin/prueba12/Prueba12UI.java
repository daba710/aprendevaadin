package aprendevaadin.prueba12;

import aprendevaadin.prueba12.data.MyContainer;
import aprendevaadin.prueba12.data.MyItem;
import aprendevaadin.prueba12.model.IMyIdentifier;
import aprendevaadin.prueba12.model.IMyModel;
import aprendevaadin.prueba12.model.MyModel;

import com.vaadin.annotations.Push;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
@Push(PushMode.MANUAL)
public class Prueba12UI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		
		IMyModel myModel = new MyModel();

		Tree tree = new Tree();
		tree.setImmediate(true);
		tree.setContainerDataSource(new MyContainer(myModel));
		tree.setItemCaptionPropertyId(MyItem.NAME_ID);
		layout.addComponent(tree);
		
		tree.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				IMyIdentifier myIdentifier = (IMyIdentifier) event.getProperty().getValue();
				System.out.println("===================> " + myIdentifier.getId());
			}
		});
		
		tree.expandItemsRecursively(myModel.getRoot());
		tree.setValue(myModel.getRoot());
	}

}