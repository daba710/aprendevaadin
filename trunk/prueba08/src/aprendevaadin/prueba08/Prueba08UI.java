package aprendevaadin.prueba08;

import aprendevaadin.prueba08.data.MyContainer;
import aprendevaadin.prueba08.data.MyItem;
import aprendevaadin.prueba08.model.IMyIdentifier;
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

		final ComboBox comboBox = new ComboBox("Coption");
		comboBox.setImmediate(true);
		comboBox.setTextInputAllowed(false);
		comboBox.setNewItemsAllowed(false);
		comboBox.setNullSelectionAllowed(false);
		
		MyContainer myContainer = MyContainer.instantiate(MyModel.INSTANCE);
		comboBox.setContainerDataSource(myContainer);
		comboBox.setItemCaptionPropertyId(MyItem.DESCRIPTION_ID);
		comboBox.setValue(myContainer.getDefaultIdentifier());
		
		comboBox.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				IMyIdentifier identifier = (IMyIdentifier) event.getProperty().getValue();
				System.out.println(String.format("Id=%d, Description=%s", identifier.getId(), MyModel.INSTANCE.getData(identifier).getDescription()));
			}
		});
		
		layout.addComponent(comboBox);
	}

}