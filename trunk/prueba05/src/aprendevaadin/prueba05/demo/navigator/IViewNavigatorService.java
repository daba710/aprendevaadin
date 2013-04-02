package aprendevaadin.prueba05.demo.navigator;

public interface IViewNavigatorService {
	
	public void navigateTo(String view);
	
	public void addListener(IViewNavigatorListener listener);
	public void removeListener(IViewNavigatorListener listener);
	
}
