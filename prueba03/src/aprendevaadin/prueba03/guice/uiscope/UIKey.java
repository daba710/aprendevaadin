package aprendevaadin.prueba03.guice.uiscope;

import java.io.Serializable;

/**
 * Esta clase es completamemnte pasiva - es una sustitucion de la propia UI durante el proceso de IoC de {@link UIScoped}.
 * <br>
 * La instancia de UI deberia de utilizarse como clave en {@link UIScope}, pero esto provoca un problema con el 
 * contructor de inyeccion de la instancia del UI. Esto es porque los parámetros de constructor que también son UIScoped 
 * se crean antes que el UI. Para superar esto, la interfaz de usuario está representado por un {@link UIKey}, 
 * que está disponible desde el inicio de la construcción del UI.
 * El propio UI y las inyecciones UIScoped luego son vinculados por la instancia {@link UIKey}.
 * <br>
 * El contador del valor es proporcinado por {@link UIKeyProvider}
 */
public class UIKey implements Comparable<UIKey>, Serializable {
	
	private static final long serialVersionUID = 2562364350206874239L;

	private final int counter;

	public UIKey(int counter) {
		super();
		this.counter = counter;
	}

	public int getCounter() {
		return counter;
	}

	@Override
	public String toString() {
		return "UIKey:" + counter;
	}

	@Override
	public int compareTo(UIKey other) {
		return this.getCounter() - other.getCounter();
	}

}
