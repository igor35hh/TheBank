package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Label;
import java.awt.Toolkit;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalTheme;

import controller.ControllerBankSystem;
import controller.ControllerBankSystemItemListener;
import controller.ControllerBankSystemWindowAdapter;
import controller.UISwitchListener;


public class BankSystem extends JFrame {
	
	public JDesktopPane desktop = new JDesktopPane();
	
	private JMenuBar bar;
	
	private JMenu mnuFile, mnuEdit, mnuView, mnuOpt, mnuWin, mnuHelp;
	
	public JMenuItem addNew, printRec, end;				//File Menu Options.
	public	JMenuItem deposit, withdraw, delRec, search, searchName;	//Edit Menu Options.
	public	JMenuItem oneByOne, allCustomer;				//View Menu Options.
	public	JMenuItem change, style, theme;					//Option Menu Options.
	
	public JMenuItem close, closeAll;					//Window Menu Options.
	
	public	JMenuItem content, keyHelp, about;				//Help Menu Options.
	
	private JPopupMenu popMenu = new JPopupMenu();
	
	public JMenuItem open, report, dep, with, del, find, all;
	
	private JToolBar toolBar;
	
	public JButton btnNew, btnDep, btnWith, btnRec, btnDel, btnSrch, btnHelp, btnKey;
	
	private JPanel statusBar = new JPanel ();
	
	private JLabel welcome;
	private JLabel author;
	
	private String strings[] = {"1. Metal", "2. Motif", "3. Windows"};
	public UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();
	private ButtonGroup group = new ButtonGroup();
	public JRadioButtonMenuItem radio[] = new JRadioButtonMenuItem[strings.length];
	
	private java.util.Date currDate = new java.util.Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
	private String d = sdf.format(currDate);
	
	public ControllerBankSystem controllerBankSystem;
	
	public BankSystem() {
		super ("BankSystem");
		
		UIManager.addPropertyChangeListener(new UISwitchListener ((JComponent)getRootPane()));
		
		bar = new JMenuBar();
		
		setIconImage(getToolkit().getImage("images/Bank.gif"));
		setSize(700, 550);
		setJMenuBar(bar);
		
		controllerBankSystem = new ControllerBankSystem(this);
		
		ControllerBankSystemItemListener controllerBankSystemItemListener = new ControllerBankSystemItemListener(this);
		
		ControllerBankSystemWindowAdapter controllerBankSystemWindowAdapter = new ControllerBankSystemWindowAdapter(this);
		addWindowListener(controllerBankSystemWindowAdapter);
		
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth())/2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height-getHeight())/2);
		
		mnuFile = new JMenu("File");
		mnuFile.setMnemonic((int)'F');
		mnuEdit = new JMenu ("Edit");
		mnuEdit.setMnemonic ((int)'E');
		mnuView = new JMenu ("View");
		mnuView.setMnemonic ((int)'V');
		mnuOpt = new JMenu ("Options");
		mnuOpt.setMnemonic ((int)'O');
		mnuWin = new JMenu ("Window");
		mnuWin.setMnemonic ((int)'W');
		mnuHelp = new JMenu ("Help");
		mnuHelp.setMnemonic ((int)'H');
		
		addNew = new JMenuItem ("Open New Account", new ImageIcon ("Images/Open.gif"));
		addNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		addNew.setMnemonic((int)'N');
		addNew.addActionListener(controllerBankSystem);
		
		printRec = new JMenuItem ("Print Customer Balance", new ImageIcon ("Images/New.gif"));
		printRec.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));
		printRec.setMnemonic ((int)'R');
		printRec.addActionListener (controllerBankSystem);
		
		end = new JMenuItem ("Quit BankSystem ?", new ImageIcon ("Images/export.gif"));
		end.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		end.setMnemonic ((int)'Q');	
		end.addActionListener (controllerBankSystem);
		
		deposit = new JMenuItem ("Deposit Money");
		deposit.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_T, Event.CTRL_MASK));
		deposit.setMnemonic ((int)'T');
		deposit.addActionListener (controllerBankSystem);
		
		withdraw = new JMenuItem ("Withdraw Money");
		withdraw.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_W, Event.CTRL_MASK));
		withdraw.setMnemonic ((int)'W');	
		withdraw.addActionListener (controllerBankSystem);
		
		delRec = new JMenuItem ("Delete Customer", new ImageIcon ("Images/Delete.gif"));
		delRec.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK));
		delRec.setMnemonic ((int)'D');
		delRec.addActionListener (controllerBankSystem);
		
		search = new JMenuItem ("Search By No.", new ImageIcon ("Images/find.gif"));
		search.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		search.setMnemonic ((int)'S');	
		search.addActionListener (controllerBankSystem);
		
		searchName = new JMenuItem ("Search By Name");
		searchName.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK));
		searchName.setMnemonic ((int)'M');
		searchName.addActionListener (controllerBankSystem);
		
		oneByOne = new JMenuItem ("View One By One");
		oneByOne.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		oneByOne.setMnemonic ((int)'O');	
		oneByOne.addActionListener (controllerBankSystem);
		
		allCustomer = new JMenuItem ("View All Customer", new ImageIcon ("Images/refresh.gif"));
		allCustomer.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
		allCustomer.setMnemonic ((int)'A');
		allCustomer.addActionListener (controllerBankSystem);
		
		change = new JMenuItem ("Change Background Color");
		change.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK));
		change.setMnemonic ((int)'B');
		change.addActionListener (controllerBankSystem);
		
		style = new JMenu ("Change Layout Style");
		style.setMnemonic ((int)'L');
		for(int i = 0; i < radio.length; i++) {
			radio[i] = new JRadioButtonMenuItem(strings[i]);
			radio[i].addItemListener(controllerBankSystemItemListener);
			group.add(radio[i]);
			style.add(radio[i]);
		}
		
		MetalTheme[] themes = { new DefaultMetalTheme(), new GreenTheme(), new AquaTheme(), 
				new SandTheme(), new SolidTheme(), new MilkyTheme(), new GrayTheme() };
		theme = new MetalThemeMenu ("Apply Theme", themes);		//Putting the Themes in ThemeMenu.
		theme.setMnemonic ((int)'M');
		
		close = new JMenuItem ("Close Active Window");
		close.setMnemonic ((int)'C');
		close.addActionListener (controllerBankSystem);
		
		closeAll = new JMenuItem ("Close All Windows...");
		closeAll.setMnemonic ((int)'A');
		closeAll.addActionListener (controllerBankSystem);
		
		content = new JMenuItem ("Help Contents", new ImageIcon ("Images/paste.gif"));
		content.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_H, Event.CTRL_MASK));
		content.setMnemonic ((int)'H');
		content.addActionListener (controllerBankSystem);
		
		keyHelp = new JMenuItem ("Help on Shortcuts...");
		keyHelp.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_K, Event.CTRL_MASK));
		keyHelp.setMnemonic ((int)'K');
		keyHelp.addActionListener (controllerBankSystem);
		
		about = new JMenuItem ("About BankSystem", new ImageIcon ("Images/Save.gif"));
		about.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
		about.setMnemonic ((int)'C');
		about.addActionListener (controllerBankSystem);
		
		mnuFile.add (addNew);
		mnuFile.addSeparator ();
		mnuFile.add (printRec);
		mnuFile.addSeparator ();
		mnuFile.add (end);
		
		mnuEdit.add (deposit);
		mnuEdit.add (withdraw);
		mnuEdit.addSeparator ();
		mnuEdit.add (delRec);
		mnuEdit.addSeparator ();
		mnuEdit.add (search);
		mnuEdit.add (searchName);
		
		mnuView.add (oneByOne);
		mnuView.addSeparator ();
		mnuView.add (allCustomer);
		
		mnuOpt.add (change);
		mnuOpt.addSeparator ();
		mnuOpt.add (style);
		mnuOpt.addSeparator ();
		mnuOpt.add (theme);
		
		mnuWin.add (close);
		mnuWin.add (closeAll);
		
		mnuHelp.add (content);
		mnuHelp.addSeparator ();
		mnuHelp.add (keyHelp);
		mnuHelp.addSeparator ();
		mnuHelp.add (about);
		
		bar.add (mnuFile);
		bar.add (mnuEdit);
		bar.add (mnuView);
		bar.add (mnuOpt);
		bar.add (mnuWin);
		bar.add (mnuHelp);
		
		open = new JMenuItem ("Open New Account", new ImageIcon ("Images/Open.gif"));
		open.addActionListener (controllerBankSystem);
		
		report = new JMenuItem ("Print BankSystem Report", new ImageIcon ("Images/New.gif"));
		report.addActionListener (controllerBankSystem);
		
		dep = new JMenuItem ("Deposit Money");
		dep.addActionListener (controllerBankSystem);
		
		with = new JMenuItem ("Withdraw Money");
		with.addActionListener (controllerBankSystem);
		
		del = new JMenuItem ("Delete Customer", new ImageIcon ("Images/Delete.gif"));
		del.addActionListener (controllerBankSystem);
		
		find = new JMenuItem ("Search Customer", new ImageIcon ("Images/find.gif"));
		find.addActionListener (controllerBankSystem);
		
		all = new JMenuItem ("View All Customer", new ImageIcon ("Images/refresh.gif"));
		all.addActionListener (controllerBankSystem);
		
		popMenu.add (open);
		popMenu.add (report);
		popMenu.add (dep);
		popMenu.add (with);
		popMenu.add (del);
		popMenu.add (find);
		popMenu.add (all);
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {checkMouseTrigger(me);};
			public void mouseReleased(MouseEvent me) {checkMouseTrigger(me);}
			private void checkMouseTrigger(MouseEvent me) {
				if(me.isPopupTrigger()) {
					popMenu.show(me.getComponent(), me.getX(), getY());
				}
			};
		});
		
		btnNew = new JButton (new ImageIcon ("Images/NotePad.gif"));
		btnNew.setToolTipText ("Create New Account");
		btnNew.addActionListener (controllerBankSystem);
		
		btnDep = new JButton (new ImageIcon ("Images/ImationDisk.gif"));
		btnDep.setToolTipText ("Deposit Money");
		btnDep.addActionListener (controllerBankSystem);
		
		btnWith = new JButton (new ImageIcon ("Images/SuperDisk.gif"));
		btnWith.setToolTipText ("Withdraw Money");
		btnWith.addActionListener (controllerBankSystem);
		
		btnRec = new JButton (new ImageIcon ("Images/Paproll.gif"));
		btnRec.setToolTipText ("Print Customer Balance");
		btnRec.addActionListener (controllerBankSystem);
		
		btnDel = new JButton (new ImageIcon ("Images/Toaster.gif"));
		btnDel.setToolTipText ("Delete Customer");
		btnDel.addActionListener (controllerBankSystem);
		
		btnSrch = new JButton (new ImageIcon ("Images/Search.gif"));
		btnSrch.setToolTipText ("Search Customer");
		btnSrch.addActionListener (controllerBankSystem);
		
		btnHelp = new JButton (new ImageIcon ("Images/Help.gif"));
		btnHelp.setToolTipText ("Help on Bank System");
		btnHelp.addActionListener (controllerBankSystem);
		
		btnKey = new JButton (new ImageIcon ("Images/Keys.gif"));
		btnKey.setToolTipText ("Shortcut Keys of BankSystem");
		btnKey.addActionListener (controllerBankSystem);
		
		toolBar = new JToolBar ();
		toolBar.add (btnNew);
		toolBar.addSeparator ();
		toolBar.add (btnDep);
		toolBar.add (btnWith);
		toolBar.addSeparator ();
		toolBar.add (btnRec);
		toolBar.addSeparator ();
		toolBar.add (btnDel);
		toolBar.addSeparator ();
		toolBar.add (btnSrch);
		toolBar.addSeparator ();
		toolBar.add (btnHelp);
		toolBar.add (btnKey);
		
		author = new JLabel (" " + "BankSystem", Label.LEFT);
		author.setForeground (Color.black);
		author.setToolTipText ("Program's Title");
		welcome = new JLabel ("Welcome Today is " + d + " ", JLabel.RIGHT);
		welcome.setForeground (Color.black);
		welcome.setToolTipText ("Welcoming the User & System Current Date");
		statusBar.setLayout (new BorderLayout());
		statusBar.add (author, BorderLayout.WEST);
		statusBar.add (welcome, BorderLayout.EAST);
		
		desktop.putClientProperty ("JDesktopPane.dragMode", "outline");
		
		getContentPane().add (toolBar, BorderLayout.NORTH);
		getContentPane().add (desktop, BorderLayout.CENTER);
		getContentPane().add (statusBar, BorderLayout.SOUTH);
		
		setVisible (true);
		
	}

}
