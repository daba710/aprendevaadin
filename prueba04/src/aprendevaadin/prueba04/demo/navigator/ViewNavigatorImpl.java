package aprendevaadin.prueba04.demo.navigator;

import java.util.ArrayList;
import java.util.List;


class ViewNavigatorImpl implements IViewNavigatorService {
	
	private List<IViewNavigatorListener> listeners = new ArrayList<IViewNavigatorListener>();
	
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
			listener.newViewSelected(new ViewNavigatorEventImpl(viewKey));
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
