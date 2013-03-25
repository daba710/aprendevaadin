package aprendevaadin.prueba04.demo.view;

import javax.inject.Inject;

import aprendevaadin.prueba04.demo.jaas.DemoLogin;
import aprendevaadin.prueba04.demo.navigator.IViewNavigatorService;
import aprendevaadin.prueba04.demo.subject.ISubjectService;
import aprendevaadin.prueba04.demo.subject.SubjectSeriviceException;
import aprendevaadin.prueba04.guice.uiscope.UIScoped;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@UIScoped
public class MainView extends VerticalLayout implements View {
	
	private static final long serialVersionUID = -1120551521269904569L;

	public static final String VIEW_KEY = "MAIN";

	private Panel panel;
	
	final private IViewNavigatorService viewNavigator;
	final private ISubjectService subjectService;

	class ButtonListener implements Button.ClickListener {
		
		private static final long serialVersionUID = 2714879925049012696L;

		private String menuitem;

		public ButtonListener(String menuitem) {
			this.menuitem = menuitem;
		}

		@Override
		public void buttonClick(ClickEvent event) {
			// Se navega a un estado especifico.
			MainView.this.viewNavigator.navigateTo(VIEW_KEY + "/" + menuitem);
		}
		
	}

	@Inject
	public MainView(IViewNavigatorService viewNavigation, ISubjectService subjectService) {
		
		this.viewNavigator = viewNavigation;
		this.subjectService = subjectService;
		
		setSizeFull();

		// La disposicion es con el menu a la izquierda y el area de la vista a la derecha.
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSizeFull();

		// El crea el menu 
		Panel menu = new Panel("List of Equals");
		menu.setHeight("100%");
		menu.setWidth(null);
		VerticalLayout menuContent = new VerticalLayout();
		menuContent.addComponent(new Button("Pig", new ButtonListener("pig")));
		menuContent.addComponent(new Button("Cat", new ButtonListener("cat")));
		menuContent.addComponent(new Button("Dog", new ButtonListener("dog")));
		menuContent.addComponent(new Button("Reindeer", new ButtonListener( "reindeer")));
		menuContent.addComponent(new Button("Penguin", new ButtonListener( "penguin")));
		menuContent.addComponent(new Button("Sheep", new ButtonListener( "sheep")));
		menuContent.setWidth(null);
		menuContent.setMargin(true);
		menu.setContent(menuContent);
		// Se instala en el lado irquierdo.
		hLayout.addComponent(menu);

		// Se crea el panel
		panel = new Panel("An Equal");
		panel.setSizeFull();
		// Se instala en el espacio que que queda a la derecha
		hLayout.addComponent(panel);
		hLayout.setExpandRatio(panel, 1.0f);

		// El layout horizontal donde ensamblamos el meni y el panel se incluye
		// en el layout vertical de la vista.
		addComponent(hLayout);
		setExpandRatio(hLayout, 1.0f);

		// Se incluye un boton que nos permite pasar a la vista de entrada
		Button logout = new Button("Logout", new Button.ClickListener() {

			private static final long serialVersionUID = 5762055829904017025L;

			@Override
			
			public void buttonClick(ClickEvent event) {
                // Logout del sistema
                try {
					MainView.this.subjectService.logout();
                    DemoLogin.dump(MainView.this.subjectService.getSubject());
					MainView.this.viewNavigator.navigateTo(StartView.VIEW_KEY);
				} catch (SubjectSeriviceException e) {
					String msg = e.getMessage();
					Notification.show(msg, Notification.Type.ERROR_MESSAGE);
				}
			}
		});
		addComponent(logout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
		// Crea y configura un nuevo contenido para el panel.
		VerticalLayout panelContent = new VerticalLayout();
		panelContent.setSizeFull();
		panelContent.setMargin(true);
		
		// Asigna el nuevo contenido al panel
		panel.setContent(panelContent);

		// Tiene que haber algun parametro en el fragmento
		if (event.getParameters() == null || event.getParameters().isEmpty()) {
			panelContent.addComponent(new Label("Nothing to see here, just pass along."));
			return;
		}

		// Muestra cabecera con los parametros del fragmento
		Label watching = new Label("You are currently watching a " + event.getParameters());
		watching.setSizeUndefined();
		panelContent.addComponent(watching);
		panelContent.setComponentAlignment(watching, Alignment.MIDDLE_CENTER);

		// Muestra el cuerpo del fragmento.
		Label label = new Label(event.getParameters());
		panelContent.addComponent(label);
		panelContent.setExpandRatio(label, 1.0f);
		panelContent.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
		
		// Muestra el pie del framgmento.
		Label back = new Label("And the " + event.getParameters() + " is watching you");
		back.setSizeUndefined();
		panelContent.addComponent(back);
		panelContent.setComponentAlignment(back, Alignment.MIDDLE_CENTER);
	}
	
}

