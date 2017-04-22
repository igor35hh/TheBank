package view;

import javax.swing.JInternalFrame;

public class BankReport extends JInternalFrame {
	
	public BankReport(String title, String text) {
		super (title, false, true, false, true);
		setSize (500, 350);
		
		ReportPage page = new ReportPage(text);
		setContentPane (page);

		setVisible (true);
	}	

}
