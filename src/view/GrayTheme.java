package view;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class GrayTheme extends DefaultMetalTheme {

	public String getName() {
		return "Cool Gray";
	}
	
	private final ColorUIResource primary1 = new ColorUIResource(225, 225, 225);
	private final ColorUIResource primary2 = new ColorUIResource(165, 165, 165);
	private final ColorUIResource primary3 = new ColorUIResource(175, 175, 175);

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
	
}
