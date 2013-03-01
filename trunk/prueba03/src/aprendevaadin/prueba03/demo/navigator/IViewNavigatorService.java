package aprendevaadin.prueba03.demo.navigator;

public interface IViewNavigatorService {
	
	public static final String MAINVIEW = "main";

	public void navigateTo(String view);
	
	public void addListener(IViewNavigatorListener listener);
	public void removeListener(IViewNavigatorListener listener);
	
}
