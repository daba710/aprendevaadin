package aprendevaadin.prueba01.demo.ui;

import aprendevaadin.prueba01.conf.A;
import aprendevaadin.prueba01.ui.ScopedUI;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class DemoUI extends ScopedUI {

	@Inject
	@Named(A.button)
	protected String buttonText;

	@Inject
	@Named(A.text)
	protected String displayText;
	
	@Override
	protected void init(VaadinRequest request) {
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Button button = new Button(buttonText);
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				String msg = String.format("%s: %s", displayText, getPage().getUriFragment());
				layout.addComponent(new Label(msg));
			}
		});
		layout.addComponent(button);
	}

}