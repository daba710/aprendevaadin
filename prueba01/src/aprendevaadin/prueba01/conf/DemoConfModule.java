package aprendevaadin.prueba01.conf;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class DemoConfModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(String.class).annotatedWith(Names.named(A.button)).toInstance("Pulsar aqui");
		bind(String.class).annotatedWith(Names.named(A.text)).toInstance("Texto a mostrar");
	}

}
