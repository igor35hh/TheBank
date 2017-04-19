package view;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class SandTheme extends DefaultMetalTheme {

	public String getName() {
		return "True Sand";
	}
	
	private final ColorUIResource primary1 = new ColorUIResource(87, 87, 47);
	private final ColorUIResource primary2 = new ColorUIResource(159, 151, 111);
	private final ColorUIResource primary3 = new ColorUIResource(199, 183, 143);
	
	private final ColorUIResource secondary1 = new ColorUIResource(111, 111, 111);
	private final ColorUIResource secondary2 = new ColorUIResource(159, 159, 159);
	private final ColorUIResource secondary3 = new ColorUIResource(231, 215, 183);

	@Override
	protected ColorUIResource getPrimary1() {
		// TODO Auto-generated method stub
		return primary1;
	}

	@Override
	protected ColorUIResource getPrimary2() {
		// TODO Auto-generated method stub
		return primary2;
	}

	@Override
	protected ColorUIResource getPrimary3() {
		// TODO Auto-generated method stub
		return primary3;
	}
	
	@Override
	protected ColorUIResource getSecondary1() {
		// TODO Auto-generated method stub
		return secondary1;
	}

	@Override
	protected ColorUIResource getSecondary2() {
		// TODO Auto-generated method stub
		return secondary2;
	}

	@Override
	protected ColorUIResource getSecondary3() {
		// TODO Auto-generated method stub
		return secondary3;
	}
	
}
