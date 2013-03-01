package aprendevaadin.prueba01.demo.ui;

import aprendevaadin.prueba01.conf.A;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class DemoContent extends VerticalLayout {
	
	private static final long serialVersionUID = -2625127587927278368L;

	@Inject
	public DemoContent(@Named(A.button) String buttonText, @Named(A.text) final String displayText) {
		setMargin(true);
		
		Button button = new Button(buttonText);
		button.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 5651060653659567801L;

			public void buttonClick(ClickEvent event) {
				String msg = String.format("%s: %s", displayText, UI.getCurrent().getPage().getUriFragment());
				addComponent(new Label(msg));
			}
		});
		addComponent(button);
	}
	
}
