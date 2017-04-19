package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JInternalFrame;

public class ControllerKeyAdapter extends KeyAdapter {
	
	private JInternalFrame newFrame;
	
	public ControllerKeyAdapter(JInternalFrame newFrame) {
		this.newFrame = newFrame;
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		char c = ke.getKeyChar ();
		if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) {
			newFrame.getToolkit().beep ();
			ke.consume ();
		}
	}

}
