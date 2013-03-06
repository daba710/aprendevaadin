package aprendevaadin.prueba04.guice.uiscope;

import java.io.Serializable;

import javax.inject.Provider;

public class UIKeyProvider implements Provider<UIKey>, Serializable {
	
	private static final long serialVersionUID = 4915031375290043157L;

	private static int counter = 0;

	@Override
	public UIKey get() {
		counter++;
		return new UIKey(counter);
	}

}
