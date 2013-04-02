package aprendevaadin.prueba05.ui;


import aprendevaadin.prueba05.guice.uiscope.UIKey;
import aprendevaadin.prueba05.guice.uiscope.UIScope;

import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

public abstract class ScopedUI extends UI {

	private static final long serialVersionUID = 819553329140514713L;

	private UIKey instanceKey;
	private UIScope uiScope;
	
	private final Panel viewDisplayPanel;

	protected ScopedUI() {
		super();
		viewDisplayPanel = new Panel();
		viewDisplayPanel.setSizeFull();
	}

	public void setInstanceKey(UIKey instanceKey) {
		this.instanceKey = instanceKey;
	}

	public UIKey getInstanceKey() {
		return instanceKey;
	}

	public void setScope(UIScope uiScope) {
		this.uiScope = uiScope;
	}

	@Override
	public void detach() {
		if (uiScope != null) {
			uiScope.releaseScope(this.getInstanceKey());
		}
		super.detach();
	}

}