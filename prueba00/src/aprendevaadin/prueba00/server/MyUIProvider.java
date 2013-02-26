package aprendevaadin.prueba00.server;


import aprendevaadin.prueba00.ui.MyUI;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class MyUIProvider extends UIProvider {

	@Override
	public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
		return MyUI.class;
	}

}
