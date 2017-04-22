package view;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class ReportPage extends JScrollPane {

	public JEditorPane page;
	
	public ReportPage(String text) {
		
		page = new JEditorPane(VIEWPORT, text); 
		page.setEditable(false);
		
		JViewport vp = getViewport();
		vp.add(page);
	
	}
	
}
