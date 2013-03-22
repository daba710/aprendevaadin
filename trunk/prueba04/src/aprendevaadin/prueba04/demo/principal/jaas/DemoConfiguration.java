package aprendevaadin.prueba04.demo.principal.jaas;

import java.util.HashMap;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

public class DemoConfiguration extends Configuration {
	
	static final public String APP_NAME = "demo";
	
	static public void install() {
		Configuration.setConfiguration(new DemoConfiguration());
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
