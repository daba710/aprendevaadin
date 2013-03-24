package aprendevaadin.prueba04.demo.subject;

import javax.security.auth.Subject;

public interface ISubjectService {
	
	public Subject getSubject();
	public void login(String user, String password) throws SubjectSeriviceException;
	public void logout() throws SubjectSeriviceException;
	
	public void addListener(ISubjectListener listener);
	public void removeListener(ISubjectListener listener);
	
}
