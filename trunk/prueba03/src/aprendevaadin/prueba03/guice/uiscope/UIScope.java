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

	private final Map<UIKey, Map<Key<?>, Object>> cache = new TreeMap<UIKey, Map<Key<?>, Object>>();

	@Override
	public <T> Provider<T> scope(final Key<T> key, final Provider<T> unscoped) {
		
		return new Provider<T>() {
			@Override
			public T get() {
				
				UIKey uiKey = getUI();
				
				// Damos una pista de que es lo que se esta pidiendo.
				log.debug("{}-{} -> {} SOLICITADO", UIScope.this.toString(), uiKey.toString(), key.toString());

				Map<Key<?>, Object> scopedObjects = getScopedObjectMap(uiKey);

				// Se recupera la instancia existente si es posible,
				@SuppressWarnings("unchecked")
				T current = (T) scopedObjects.get(key);
				if (current != null) {
					// Se retorna la instancia existente
					log.debug("{}-{} -> {} RECUPERADO", UIScope.this.toString(), uiKey.toString(), key.toString());
					return current;
				} else {
					// o se create, se almacena en la cache y se retorna la primera instancia.
					current = unscoped.get();
					scopedObjects.put(key, current);
					log.debug("{}-{} -> {} CREADO", UIScope.this.toString(), uiKey.toString(), key.toString());
					return current;
				}
			}
		};
	}

	private UIKey getUI() {
		
		// Obtiene la UIKey que corresponde via 'current instance'. Esta UIKey fue creada por el ScopedUIProvider justo 
		// antes de instanciar el ScopedUI y esta asociado a el.
		UIKey uiKey = CurrentInstance.get(UIKey.class);
		
		// Se obtiene el UI via 'currant UI'. Puede estar a null si estamos en proceso de construccion.
		ScopedUI currentUI = (ScopedUI) UI.getCurrent();
		
		// Verificamos que 'current instance' este informada.
		if (uiKey == null) {
			
			// Si no esta informada se intenta obtener via 'current UI'.
			if (currentUI == null) {
				// Si ademas de via 'current instance' tampoco se puede obtener via 'current ui' no hay solución.
				throw new UIScopeException(String.format("uiKey y UI son null."));
			} else {
				// Esto puede ocurrir cuando el framework conmuta UIs
				uiKey = currentUI.getInstanceKey();
				if (uiKey == null) {
					throw new UIScopeException(String.format("uiKey es null y no puede ser obtenido desde UI porque tambien es null."));
				}
			}
		}
		
		// Interesado en saber si puede ocurrir que 'current UI' puede ser null.
		if (currentUI == null) {
			log.debug("{}-{} -> UI es null", UIScope.this.toString(), uiKey.toString());
		} else {
			// Nos aseguramos que no se haya perdido la sincronización del 'current instance' con el 'current UI'.
			if (!uiKey.equals(currentUI.getInstanceKey())) {
				throw new UIScopeException(String.format("El uiKey y el UI han perdido la sincronización."));
			}
		}
		
		return uiKey;
	}
	
	private <T> Map<Key<?>, Object> getScopedObjectMap(UIKey uiKey) {
		// return an existing cache instance
		if (cache.containsKey(uiKey)) {
			Map<Key<?>, Object> scopedObjects = cache.get(uiKey);
			return scopedObjects;
		} else {
			return createCacheEntry(uiKey);
		}
	}

	private boolean cacheHasEntryFor(UIKey uiKey) {
		return cache.containsKey(uiKey);
	}

	private HashMap<Key<?>, Object> createCacheEntry(UIKey uiKey) {
		HashMap<Key<?>, Object> uiEntry = new HashMap<Key<?>, Object>();
		cache.put(uiKey, uiEntry);
		log.debug("{}-{} -> Creado mapa de cache.", UIScope.this.toString(), uiKey.toString());
		return uiEntry;
	}

	public void startScope(UIKey uiKey) {
		if (!cacheHasEntryFor(uiKey)) {
			log.debug("{}-{} -> Inicializando ambito.", UIScope.this.toString(), uiKey.toString());
			createCacheEntry(uiKey);
		}
	}

	public void releaseScope(UIKey uiKey) {
		cache.remove(uiKey);
		log.debug("{}-{} -> Reseteando ambito y destruyendo mapa de cache.", UIScope.this.toString(), uiKey.toString());
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + ".DEFAULT";
	}

}