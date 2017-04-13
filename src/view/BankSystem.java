package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BankSystem extends JFrame implements ActionListener, ItemListener {
	
	private JDesktopPane desktop = new JDesktopPane();
	private JMenuBar bar;
	
	private JMenu mnuFile, mnuEdit, mnuView, mnuOpt, mnuWin, mnuHelp;
	
	private JMenuItem addNew, printRec, end;				//File Menu Options.
	private	JMenuItem  deposit, withdraw, delRec, search, searchName;	//Edit Menu Options.
	private	JMenuItem oneByOne, allCustomer;				//View Menu Options.
	private	JMenuItem change, style, theme;					//Option Menu Options.
	private JMenuItem close, closeAll;					//Window Menu Options.
	private	JMenuItem content, keyHelp, about;				//Help Menu Options.
	
	public BankSystem() {
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
