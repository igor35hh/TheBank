package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class UISwitchListener implements PropertyChangeListener {
	
	JComponent componentToSwitch;
	
	public UISwitchListener(JComponent c) {
		this.componentToSwitch = c;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String name = evt.getPropertyName();
		if(name.equals("lookAndFeel")) {
			SwingUtilities.updateComponentTreeUI(componentToSwitch);
			componentToSwitch.invalidate();
			componentToSwitch.validate();
			componentToSwitch.repaint();
		}
		
	}

}
