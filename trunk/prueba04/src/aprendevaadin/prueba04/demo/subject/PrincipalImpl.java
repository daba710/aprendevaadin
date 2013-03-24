package aprendevaadin.prueba04.demo.subject;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import aprendevaadin.prueba04.demo.navigator.IViewNavigatorEvent;
import aprendevaadin.prueba04.demo.navigator.IViewNavigatorListener;

class PrincipalImpl implements IPrincipalService {
	
	private List<IPrincipalListener> listeners = new ArrayList<IPrincipalListener>();

	public Subject getSubject() {
		return null;
	}
	
	public void login(String user, String password) {
		
	}
	
	public void logout() {
		
	}

	@Override
	public void addListener(IPrincipalListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(IPrincipalListener listener) {
		listeners.remove(listener);
	}
	
	private void firePrincipalUpdated(Principal principal) {
		for (IPrincipalListener listener : listeners.toArray(new IPrincipalListener[] {})) {
			listener.principalUpdated(new ViewNavigatorEventImpl(viewKey));
		}
	}
	
	private class PrincipalEventImpl implements IPrincipalEvent {
		
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
