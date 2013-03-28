package aprendevaadin.prueba04.demo.navigator;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.security.auth.Subject;

import aprendevaadin.prueba04.demo.jaas.DemoViewExecPermission;
import aprendevaadin.prueba04.demo.subject.ISubjectService;


class ViewNavigatorImpl implements IViewNavigatorService {
	
	private List<IViewNavigatorListener> listeners = new ArrayList<IViewNavigatorListener>();
	
	private ISubjectService subjectService;
	
	@Inject
	public ViewNavigatorImpl(ISubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@Override
	public void navigateTo(String viewKey) {
		fireNewViewSelected(viewKey);
	}

	@Override
	public void addListener(IViewNavigatorListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(IViewNavigatorListener listener) {
		listeners.remove(listener);
	}
	
	private void fireNewViewSelected(String viewKey) {
		
		for (IViewNavigatorListener listener : listeners.toArray(new IViewNavigatorListener[] {})) {
			Subject.doAsPrivileged(subjectService.getSubject(), new ViewActionGo(listener, viewKey), null);
		}
//		for (IViewNavigatorListener listener : listeners.toArray(new IViewNavigatorListener[] {})) {
//			listener.newViewSelected(new ViewNavigatorEventImpl(viewKey));
//		}
	}
	
	private class ViewActionGo implements PrivilegedAction {
		
		private IViewNavigatorListener listener;
		private String viewKey;
		
		public ViewActionGo(IViewNavigatorListener listener, String viewKey) {
			this.listener = listener;
			this.viewKey = viewKey;
		}

		@Override
		public Object run() {
			DemoViewExecPermission perm = new DemoViewExecPermission(viewKey);

			SecurityManager sm = System.getSecurityManager();
			if (sm != null) {
				sm.checkPermission(perm);
				listener.newViewSelected(new ViewNavigatorEventImpl(viewKey));
				return null;
			}
			
			return null;
		}
		
	}
	
	private class ViewNavigatorEventImpl implements IViewNavigatorEvent {
		
		final private String navigatorEventKey;
		
		private ViewNavigatorEventImpl(String navigatorEventKey) {
			this.navigatorEventKey = navigatorEventKey;
		}

		@Override
		public String getViewKey() {
			return navigatorEventKey;
		}
		
	}

}
