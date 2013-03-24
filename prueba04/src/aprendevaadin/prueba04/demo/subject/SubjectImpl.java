package aprendevaadin.prueba04.demo.subject;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import aprendevaadin.prueba04.demo.navigator.IViewNavigatorEvent;
import aprendevaadin.prueba04.demo.navigator.IViewNavigatorListener;

class SubjectImpl implements ISubjectService {
	
	private List<ISubjectListener> listeners = new ArrayList<ISubjectListener>();

	public Subject getSubject() {
		return null;
	}
	
	public void login(String user, String password) {
		
	}
	
	public void logout() {
		
	}

	@Override
	public void addListener(ISubjectListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(ISubjectListener listener) {
		listeners.remove(listener);
	}
	
	private void firePrincipalUpdated(Principal principal) {
		for (ISubjectListener listener : listeners.toArray(new ISubjectListener[] {})) {
			listener.principalUpdated(new ViewNavigatorEventImpl(viewKey));
		}
	}
	
	private class PrincipalEventImpl implements ISubjectEvent {
		
		final private Principal principal;
		
		private PrincipalEventImpl(Principal principal) {
			this.principal = principal;
		}

		@Override
		public Principal getPrincipal() {
			return principal;
		}
		
	}

}
