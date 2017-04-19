package view;

import javax.swing.JInternalFrame;

public class BankHelp extends JInternalFrame {
	
	public BankHelp(String title, String filename) {
		super (title, false, true, false, true);
		setSize (500, 350);

		HtmlPane html = new HtmlPane (filename);
		setContentPane (html);

		setVisible (true);
	}

}
