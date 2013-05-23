package aprendevaadin.prueba07;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class Prueba07UI extends UI {
	
	static String[] descriptions = new String[] { "Uno", "Dos", "Tres" };
	static String[] identifiers = new String[] { "1", "2", "3" };

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
		
		for (int i = 0; i < identifiers.length; i++) {
			String identifier = identifiers[i];
			comboBox.addItem(identifier);
			comboBox.setItemCaption(identifier, descriptions[i]);
		}
		
		comboBox.setValue(identifiers[2]);
		
		comboBox.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				String identifier = (String) event.getProperty().getValue();
				for (int i = 0; i < identifiers.length; i++) {
					if (identifier.equals(identifiers[i])) {
						System.out.println(String.format("Identifier=%s -> Desccription=%s", identifier, descriptions[i]));
					}
				}
			}
		});
		
		layout.addComponent(comboBox);
	}

}