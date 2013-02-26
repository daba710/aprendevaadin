package uk.co.q3c.v7.demo.view;

import javax.inject.Inject;

import uk.co.q3c.v7.base.view.components.FooterBar;
import uk.co.q3c.v7.base.view.components.HeaderBar;

public class SecureHomeView extends DemoViewBase {

	@Inject
	protected SecureHomeView(FooterBar footerBar, HeaderBar headerBar) {
		super(footerBar, headerBar);
		addNavButton("view 1", view1);
		addNavButton("view 1 with parameters", view1 + "/id=22");
		addNavButton("view 2", view2);
		addNavButton("view 2 with parameters", view2 + "/id=22");
		addNavButton("invalid uri", "view3/id=22");
	}

	@Override
	public int getColourIndex() {
		return 0;
	}

}
