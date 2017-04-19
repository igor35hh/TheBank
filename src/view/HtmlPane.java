package view;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import controller.ControllerHtmlPane;

public class HtmlPane extends JScrollPane {
	
	public JEditorPane html;
	
	public HtmlPane(String filename) {
		try {
			File f = new File (filename);
			String s = f.getAbsolutePath();
			s = "file:"+s;
			URL url = new URL(s);
			html = new JEditorPane(s);
			html.setEditable(false);
			
			ControllerHtmlPane controllerHtmlPane = new ControllerHtmlPane(this);
			html.addHyperlinkListener(controllerHtmlPane);
			
			JViewport vp = getViewport();
			vp.add(html);
		}
		catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e);
		}
		catch (IOException e) {
			System.out.println("IOException: " + e);
		}
	}

}
