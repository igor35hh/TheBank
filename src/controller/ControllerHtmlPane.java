package controller;

import java.awt.Cursor;
import java.net.URL;

import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import model.PageLoader;
import view.HtmlPane;

public class ControllerHtmlPane implements HyperlinkListener {
	
	private HtmlPane htmlPane;
	
	public ControllerHtmlPane(HtmlPane htmlPane) {
		super();
		this.htmlPane = htmlPane;
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			linkActivated(e.getURL());
		}
	}
	
	private void linkActivated(URL u) {
		Cursor c = htmlPane.html.getCursor();
		Cursor waitCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
		htmlPane.html.setCursor(waitCursor);
		SwingUtilities.invokeLater(new PageLoader(htmlPane.html, u, c));
	}

}
