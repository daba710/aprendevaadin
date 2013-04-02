package aprendevaadin.prueba05.demo.view;

import aprendevaadin.prueba05.demo.subject.ISubjectService;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.multibindings.MapBinder;
import com.vaadin.navigator.View;

public class ViewModule extends AbstractModule {
	
	@Override
	protected void configure() {
		MapBinder<String, View> mapbinder = MapBinder.newMapBinder(binder(), String.class, View.class);

		addUIBindings(mapbinder);
		installInterceptors();
	}

	protected void addUIBindings(MapBinder<String, View> mapbinder) {
		mapbinder.addBinding(MainView.VIEW_KEY).to(MainView.class);
		mapbinder.addBinding(StartView.VIEW_KEY).to(StartView.class);
	}
	
	protected void installInterceptors() {
		// Se instancia el interceptos
		CheckDemoViewExecPermissionInterceptor checkDemoViewExecPermissionInterceptor = 
				new CheckDemoViewExecPermissionInterceptor(getProvider(ISubjectService.class));
		// Se engancha el interceptor con la lclase y metodo a interceptar.
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(CheckDemoViewExecPermission.class), checkDemoViewExecPermissionInterceptor);
	}

}
