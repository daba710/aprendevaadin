package aprendevaadin.prueba08;

import aprendevaadin.prueba08.data.MyContainer;
import aprendevaadin.prueba08.model.MyModel;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class Prueba08UI extends UI {
	
	@Override
	protected void init(VaadinRequest request) {
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		ComboBox comboBox = new ComboBox("Coption");
		comboBox.setImmediate(true);
		comboBox.setTextInputAllowed(false);
		comboBox.setNewItemsAllowed(false);
		comboBox.setNullSelectionAllowed(false);
		
		MyContainer myContainer = MyContainer.instantiate(MyModel.INSTANCE);
		comboBox.setContainerDataSource(myContainer);
		
		comboBox.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
			}
		});
		
		layout.addComponent(comboBox);
	}

}