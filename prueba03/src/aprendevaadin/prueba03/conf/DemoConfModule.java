package aprendevaadin.prueba03.conf;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class DemoConfModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(String.class).annotatedWith(Names.named(A.title)).toInstance("Navigation Example");
	}

}
