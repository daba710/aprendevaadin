package aprendevaadin.prueba01.ui;


import aprendevaadin.prueba01.guice.uiscope.UIKey;
import aprendevaadin.prueba01.guice.uiscope.UIKeyProvider;
import aprendevaadin.prueba01.guice.uiscope.UIScope;

import com.google.inject.Injector;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;
import com.vaadin.util.CurrentInstance;

/**
 * Subclases deberan de implementar {@link #getUIClass(com.vaadin.server.UIClassSelectionEvent)} para proporcinar 
 * la logica de selección del UI.
 */
public abstract class ScopedUIProvider extends UIProvider {
	
	private static final long serialVersionUID = 3256725362295628948L;

	private final UIKeyProvider uiKeyProvider;
	private final Injector injector;

	protected ScopedUIProvider(UIKeyProvider uiKeyProvider, Injector injector) {
		super();
		this.uiKeyProvider = uiKeyProvider;
		this.injector = injector;
	}

	@Override
	public UI createInstance(UICreateEvent event) {
		
		// Se obtiene una nueva Key para para el nuevo UI que se va a generar.
		UIKey uiKey = uiKeyProvider.get();
		
		// Almacena la key mientras el UI es creado.
		CurrentInstance.set(UIKey.class, uiKey);
		
		// Y instala un nuevo scope
		UIScope.DEFAULT.startScope(uiKey);
		
		// Se obtiene la clase correspondiente al UI
		Class<? extends UI> uiClass = event.getUIClass();

		// Se inyecta la instancia correspondiente al UI
		UI ui = injector.getInstance(uiClass);
		
		// Se verifica que el nuevo UI tenga scope
		if (!(ui instanceof ScopedUI)) {
			throw new UIProviderException("No scope for " + event.getUIClass().getName());
		}
		
		// Se adapta al tipo con Scope
		ScopedUI scopeUI = (ScopedUI) ui;
		
		// Se asigna la nueva clave al nuevo scope
		scopeUI.setInstanceKey(uiKey);
		
		// Se retorna el nuevo UI
		return ui;
	}

}
