package aprendevaadin.prueba11;

import java.io.Serializable;

public class Body implements Serializable {
	
    private static final long serialVersionUID = -312738461368736290L;

    String name;
    Body parent; // Represent hierarchy with a simple parent ref

    /** Constructor for root nodes. */
    public Body(String name) {
        this.name       = name;
        this.parent     = null;
    }
    
    /** Constructor for branch or leaf nodes. */
    public Body(String name, Body parent) {
        this.name       = name;
        this.parent     = parent;
    }
    
    public String getName() {
        return name;
    }

    /** The property to get the hierarchy information */
    public Body getParent() {
        return parent;
    }
}
