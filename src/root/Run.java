package root;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import view.BankSystem;

public class Run extends JWindow {
	
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Run() {
		JLabel lbImage = new JLabel(new ImageIcon("Run.jpg"));
		Color cl = new Color(0,0,0);
		lbImage.setBorder(new LineBorder(cl, 1));
		
		getContentPane().add(lbImage, BorderLayout.CENTER);
		pack();
		
		setSize(getSize().width, getSize().height);
		setLocation(d.width/2-getWidth()/2, d.height/2-getHeight()/2);
		
		show();
		
		new BankSystem();
		
		toFront();
		dispose();
		setVisible(false);
	}

	public static void main(String[] args) {
		new Run();
	}

}
