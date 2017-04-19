package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

import view.MetalThemeMenu;

public class ControllerMetalThemeMenu implements ActionListener {
	
	private MetalThemeMenu metalThemeMenu;
	
	public ControllerMetalThemeMenu(MetalThemeMenu metalThemeMenu) {
		super();
		this.metalThemeMenu = metalThemeMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String numStr = e.getActionCommand();
		MetalTheme selectedTheme = metalThemeMenu.themes[Integer.parseInt(numStr)];
		MetalLookAndFeel.setCurrentTheme(selectedTheme);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch(Exception ex) {
			System.out.println("Failed loading Metal");
			System.out.println(ex);
		}
	}

}
