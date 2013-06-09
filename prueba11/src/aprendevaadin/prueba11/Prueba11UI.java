package aprendevaadin.prueba11;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class Prueba11UI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

        // Create some beans
        Body sun     = new Body("The Sun");
        Body mercury = new Body("Mercury", sun);
        Body venus   = new Body("Venus", sun); 
        Body earth   = new Body("Earth", sun);
        Body moon    = new Body("The Moon", earth);
        Body mars    = new Body("Mars", sun); 
        Body phobos  = new Body("Phobos", mars); 
        Body daimos  = new Body("Daimos", mars); 
        Body jupiter = new Body("Jove", sun); 
        Body io      = new Body("Io", jupiter); 
        Body europa  = new Body("Europa", jupiter); 
        Body ganymede= new Body("Ganymede", jupiter); 
        Body callisto= new Body("Callisto", jupiter); 
        Body saturn  = new Body("Saturn", sun); 
        Body titan   = new Body("Titan", saturn); 
        Body rhea    = new Body("Rhea", saturn); 
        Body iapetus = new Body("Iapetus", saturn); 
        Body dione   = new Body("Dione", saturn); 
        Body tethys  = new Body("Tethys", saturn); 
        Body uranus  = new Body("Uranus", sun); 
        Body titania = new Body("Titania", uranus); 
        Body oberon  = new Body("Oberon", uranus); 
        Body umbriel = new Body("Umbriel", uranus); 
        Body ariel   = new Body("Ariel", uranus); 
        Body neptune = new Body("Neptune", sun);
        Body triton  = new Body("Triton", neptune);
        Body proteus = new Body("Proteus", neptune);
        Body nereid  = new Body("Nereid", neptune);
        Body larissa = new Body("Larissa", neptune);

        // Create a container for the beans
        final HierarchicalBeanItemContainer<Body> bodyContainer =
            new HierarchicalBeanItemContainer<Body>(
                    Body.class, "parent");

        // Add the beans to the container
        Body bodyBeans[] = new Body[] {
            sun, mercury, venus, earth, moon, mars, phobos,
            daimos, jupiter, io, europa, ganymede, callisto,
            saturn, titan, rhea, iapetus, dione, tethys, uranus,
            titania, oberon, umbriel, ariel, neptune, triton,
            proteus, nereid, larissa
        };
        for (Body body: bodyBeans)
            bodyContainer.addBean(body);
                
        // Put them in a tree
        Tree tree = new Tree("Planetary Bodies", bodyContainer);
        tree.setItemCaptionMode(Tree.ITEM_CAPTION_MODE_PROPERTY);
        tree.setItemCaptionPropertyId("name");
        
        // Expand the tree
        for (Object rootItemId: bodyContainer.rootItemIds())
            tree.expandItemsRecursively(rootItemId);
        
        layout.addComponent(tree);
 
	}

}