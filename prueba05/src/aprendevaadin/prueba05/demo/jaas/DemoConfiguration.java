package aprendevaadin.prueba05.demo.jaas;

import java.security.Policy;
import java.util.ArrayList;
import java.util.HashMap;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

public class DemoConfiguration extends Configuration {
	
	static final public String APP_NAME = "demo";
	
	static public void install() {
		
		// Se instala la nueva configuracion de los modulos de Login.
		Configuration.setConfiguration(new DemoConfiguration());
		
		// Se instala la politica de permisos personalizada
		Policy defaultPolicy = Policy.getPolicy();
		ArrayList<Policy> policies = new ArrayList<>();
		if (defaultPolicy != null) {
			policies.add(defaultPolicy);
		}
		policies.add(new DemoPolicy());
		Policy.setPolicy(new CompositePolicy(policies));
		
		// Se instala el SecurityManager
		System.setSecurityManager(new SecurityManager());
	}
	
	private AppConfigurationEntry[] demoEntries;

	@Override
	public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
		if (name.equals(APP_NAME))
			return demoEntries;
		else
			return null;
	}
	
	private DemoConfiguration() {
		demoEntries = new AppConfigurationEntry[] {
			new AppConfigurationEntry(DemoLogin.class.getName(), AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, new HashMap<String,String>())	
		};
	}

}
