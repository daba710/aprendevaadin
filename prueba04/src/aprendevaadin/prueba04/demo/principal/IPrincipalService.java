package aprendevaadin.prueba04.demo.principal;

import javax.security.auth.Subject;

public interface IPrincipalService {
	
	public Subject getSubject();
	public void login(String user, String password);
	public void logout();
	
	public void addListener(IPrincipalListener listener);
	public void removeListener(IPrincipalListener listener);
	
}
