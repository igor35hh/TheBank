package view;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.plaf.metal.MetalTheme;

import controller.ControllerMetalThemeMenu;

public class MetalThemeMenu extends JMenu {
	
	public MetalTheme[] themes;
	
	public MetalThemeMenu(String name, MetalTheme[] themeArray) {
		super(name);
		
		themes = themeArray;
		ButtonGroup group = new ButtonGroup();
		
		for (int i = 0; i < themeArray.length; i++) {
			JRadioButtonMenuItem item = new JRadioButtonMenuItem(themes[i].getName());
			group.add(item);
			add(item);
			item.setActionCommand(i+"");
			ControllerMetalThemeMenu controllerMetalThemeMenu = new ControllerMetalThemeMenu(this);
			item.addActionListener(controllerMetalThemeMenu);
			if(i == 0) {
				item.setSelected(true);
			}
		}
	}

}
