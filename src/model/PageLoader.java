package model;

import java.awt.Container;
import java.awt.Cursor;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;

public class PageLoader implements Runnable {
	
	URL url;
	Cursor cursor;
	JEditorPane html;

	public PageLoader(JEditorPane html, URL u, Cursor c) {
		super();
		this.html = html;
		this.url = u;
		this.cursor = c;
	}

	@Override
	public void run() {
		if (url == null) {
			html.setCursor(cursor);
			Container parent = html.getParent();
			parent.repaint();
		}
		else {
			Document doc = html.getDocument();
			try {
				html.setPage(url);
			}
			catch (IOException ioe) {
				html.setDocument(doc);
				html.getToolkit().beep();
			}
			finally {
				url = null;
				SwingUtilities.invokeLater(this);
			}
		}
	}
	
}
