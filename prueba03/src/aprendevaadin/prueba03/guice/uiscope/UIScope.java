package aprendevaadin.prueba03.guice.uiscope;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import aprendevaadin.prueba03.ui.ScopedUI;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.vaadin.ui.UI;
import com.vaadin.util.CurrentInstance;

public class UIScope implements Scope {
	
	public static UIScope DEFAULT = new UIScope();

	private static final Logger log = LoggerFactory.getLogger(UIScope.class);
	private static final String MSG = "Este error no deberia de haber aparecido.";

	private final Map<UIKey, Map<Key<?>, Object>> cache = new TreeMap<UIKey, Map<Key<?>, Object>>();

	@Override
	public <T> Provider<T> scope(final Key<T> key, final Provider<T> unscoped) {
		return new Provider<T>() {
			@Override
			public T get() {
				// 
				// get the scope cache for the current UI
				log.debug("looking for a UIScoped instance of {}", key.getClass().getName());

				// Obtiene la UIKey actual. Deberia estar siempre disponible ya que se creo antes que el UI.
				UIKey uiKey = CurrentInstance.get(UIKey.class);
				// El UI puede estar a null si estamos en el proceso de construccion.
				ScopedUI currentUI = (ScopedUI) UI.getCurrent();
				if (uiKey == null) {
					if (currentUI == null) {
						throw new UIScopeException(String.format("UI and uiKey are null (%s).",  MSG));
					} else {
						// Esto puede ocurrir cuando el framework conmuta UIs
						uiKey = currentUI.getInstanceKey();
						if (uiKey == null) {
							throw new UIScopeException(String.format("uiKey is null and cannot be obtained from the UI (%s).", MSG));
						}
					}
				}

				// 'currentUI' puede ser null si estamos en el proceso de contruccion del UI,
				// si no es null simplemente nos aseguramos de que no ha perdido la sincronizacion con su 'uiKey'
				if (currentUI != null) {
					if (!uiKey.equals(currentUI.getInstanceKey())) {
						throw new UIScopeException(String.format("The UI and its UIKey have got out of sync (Results are unpredictable - %s).", MSG));
					}
				}

				log.debug("looking for cache for key: " + uiKey);
				Map<Key<?>, Object> scopedObjects = getScopedObjectMap(uiKey);

				// Se recupera la instancia existente si es posible,
				@SuppressWarnings("unchecked")
				T current = (T) scopedObjects.get(key);
				if (current != null) {
					// Se retorna la instancia existente
					log.debug("returning existing instance of " + current.getClass().getSimpleName());
					return current;
				} else {
					// o se create, se almacena en la cache y se retorna la primera instancia.
					current = unscoped.get();
					scopedObjects.put(key, current);
					log.debug("new instance of " + current.getClass().getSimpleName() + " created, as none in cache");
					return current;
				}
			}
		};
	}

	private <T> Map<Key<?>, Object> getScopedObjectMap(UIKey uiKey) {
		// return an existing cache instance
		if (cache.containsKey(uiKey)) {
			Map<Key<?>, Object> scopedObjects = cache.get(uiKey);
			log.debug("scope cache retrieved for UI key: " + uiKey);
			return scopedObjects;
		} else {
			return createCacheEntry(uiKey);
		}
	}

	public void startScope(UIKey uiKey) {
		if (!cacheHasEntryFor(uiKey)) {
			createCacheEntry(uiKey);
		}
	}

	private boolean cacheHasEntryFor(UIKey uiKey) {
		return cache.containsKey(uiKey);
	}

	private HashMap<Key<?>, Object> createCacheEntry(UIKey uiKey) {
		HashMap<Key<?>, Object> uiEntry = new HashMap<Key<?>, Object>();
		cache.put(uiKey, uiEntry);
		log.debug("created a scope cache for UIScope with key: " + uiKey);
		return uiEntry;
	}

	public void releaseScope(UIKey uiKey) {
		cache.remove(uiKey);
	}

}