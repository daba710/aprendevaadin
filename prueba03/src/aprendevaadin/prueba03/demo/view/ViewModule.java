package aprendevaadin.prueba03.demo.view;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.vaadin.navigator.View;

public class ViewModule extends AbstractModule {

	@Override
	protected void configure() {
		MapBinder<String, View> mapbinder = MapBinder.newMapBinder(binder(), String.class, View.class);

		addUIBindings(mapbinder);
	}

	protected void addUIBindings(MapBinder<String, View> mapbinder) {
		mapbinder.addBinding(MainView.VIEW_KEY).to(MainView.class);
		mapbinder.addBinding(StartView.VIEW_KEY).to(StartView.class);
	}

}
