package view;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class AquaTheme extends DefaultMetalTheme {

	public String getName() {
		return "Pure Aqua";
	}
	
	private final ColorUIResource primary1 = new ColorUIResource(102, 153, 153);
	private final ColorUIResource primary2 = new ColorUIResource(128, 192, 192);
	private final ColorUIResource primary3 = new ColorUIResource(159, 235, 235);
	
	private final ColorUIResource secondary2 = new ColorUIResource(204, 204, 204);
	private final ColorUIResource secondary3 = new ColorUIResource(160, 225, 225);

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
