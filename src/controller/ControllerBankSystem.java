package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.Properties;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import view.BankHelp;
import view.BankSystem;
import view.DeleteCustomer;
import view.DepositMoney;
import view.FindAccount;
import view.FindName;
import view.NewAccount;
import view.ViewCustomer;
import view.ViewOne;
import view.WithdrawMoney;

public class ControllerBankSystem implements ActionListener {
	
	private BankSystem bankSystem;
	
	private int rows = 0;
	private	int total = 0;
	
	private String records[][] = new String [500][6];
	
	private FileInputStream fis;
	private DataInputStream dis;
	
	public ControllerBankSystem(BankSystem bankSystem) {
		this.bankSystem = bankSystem;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		
		if(obj == bankSystem.addNew || obj == bankSystem.open || obj == bankSystem.btnNew) {
			boolean b = openChildWindow("Create New Account");
			if(b == false) {
				NewAccount newAcc = new NewAccount();
				bankSystem.desktop.add(newAcc);
				newAcc.show();
			}
		} else if(obj == bankSystem.printRec || obj == bankSystem.btnRec || obj == bankSystem.report) {
			getAccountNo();
		} else if(obj == bankSystem.end) {
			quitApp();
		} else if(obj == bankSystem.deposit || obj == bankSystem.dep || obj == bankSystem.btnDep) {
			boolean b = openChildWindow ("Deposit Money");
			if (b == false) {
				DepositMoney depMon = new DepositMoney ();
				bankSystem.desktop.add (depMon);
				depMon.show ();
			}
		} else if(obj == bankSystem.withdraw || obj == bankSystem.with || obj == bankSystem.btnWith) {
			boolean b = openChildWindow ("Withdraw Money");
			if (b == false) {
				WithdrawMoney withMon = new WithdrawMoney ();
				bankSystem.desktop.add (withMon);
				withMon.show ();
			}
		} else if(obj == bankSystem.delRec || obj == bankSystem.del || obj == bankSystem.btnDel) {
			boolean b = openChildWindow ("Delete Account Holder");
			if (b == false) {
				DeleteCustomer delCus = new DeleteCustomer ();
				bankSystem.desktop.add (delCus);
				delCus.show ();
			}
		} else if(obj == bankSystem.search || obj == bankSystem.find || obj == bankSystem.btnSrch) {
			boolean b = openChildWindow ("Search Customer [By No.]");
			if (b == false) {
				FindAccount fndAcc = new FindAccount ();
				bankSystem.desktop.add (fndAcc);
				fndAcc.show ();
			}
		} else if(obj == bankSystem.searchName) {
			boolean b = openChildWindow ("Search Customer [By Name]");
			if (b == false) {
				FindName fndNm = new FindName ();
				bankSystem.desktop.add (fndNm);
				fndNm.show ();
			}
		} else if(obj == bankSystem.oneByOne) {
			boolean b = openChildWindow ("View Account Holders");
			if (b == false) {
				ViewOne vwOne = new ViewOne ();
				bankSystem.desktop.add (vwOne);
				vwOne.show ();
			}
		} else if(obj == bankSystem.allCustomer || obj == bankSystem.all) {
			boolean b = openChildWindow ("View All Account Holders");
			if (b == false) {
				ViewCustomer viewCus = new ViewCustomer ();
				bankSystem.desktop.add (viewCus);
				viewCus.show ();
			}
		} else if(obj == bankSystem.change) {
			Color cl = new Color (153, 153, 204);
			cl = JColorChooser.showDialog (null, "Choose Background Color", cl);
			if (cl == null) { }
			else {
				bankSystem.desktop.setBackground (cl);
				bankSystem.desktop.repaint ();
			}
		} else if(obj == bankSystem.close) {
			try {
				bankSystem.desktop.getSelectedFrame().setClosed(true);
			}
			catch (Exception CloseExc) { }
		} else if(obj == bankSystem.closeAll) {
			JInternalFrame Frames[] = bankSystem.desktop.getAllFrames (); 
			for(int getFrameLoop = 0; getFrameLoop < Frames.length; getFrameLoop++) {
				try {
	 				Frames[getFrameLoop].setClosed (true); 
				} 
				catch (Exception CloseExc) { }
			}
		} else if(obj == bankSystem.content || obj == bankSystem.btnHelp) {
			boolean b = openChildWindow ("BankSystem Help");
			if (b == false) {
				BankHelp hlpBank = new BankHelp ("BankSystem Help", "Help/Bank.htm");
				bankSystem.desktop.add (hlpBank);
				hlpBank.show ();
			}
		} else if(obj == bankSystem.keyHelp || obj == bankSystem.btnKey) {
			boolean b = openChildWindow ("BankSystem Keys");
			if (b == false) {
				BankHelp hlpKey = new BankHelp ("BankSystem Keys", "Help/Keys.htm");
				bankSystem.desktop.add (hlpKey);
				hlpKey.show ();
			}
		} else if(obj == bankSystem.about) {
			String msg = "BankSystem [Pvt] Limited.\n\n";
				JOptionPane.showMessageDialog (null, msg, "About BankSystem", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public void quitApp() {
		try {
			int reply = JOptionPane.showConfirmDialog(null, 
					"Are you really want to exit\nFrom BankSystem?",
					"BankSystem - Exit",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			if(reply == JOptionPane.YES_OPTION) {
				bankSystem.setVisible(false);
				bankSystem.dispose();
				System.out.println("Thanks for Using BankSystem");
				System.exit(0);
			} else if(reply == JOptionPane.NO_OPTION) {
				bankSystem.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		} catch(Exception e) {
			
		}
	}
	
	private void getAccountNo() {
		String printing;
		rows = 0;
		boolean b = populateArray();
		if(b == false) {
			
		} else {
			try{
				printing = JOptionPane.showInputDialog (null, 
						"Enter Account No. to Print Customer Balance.\n" + "(Tip: Account No. Contains only Digits)", 
						"BankSystem - PrintRecord", JOptionPane.PLAIN_MESSAGE);
				if(printing.equals("")) {
					JOptionPane.showMessageDialog (null, "Provide Account No. to Print.",
							 "BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
					getAccountNo ();
				} else {
					findRec(printing);
				}
			} catch (Exception e) {
				
			}
		}
	}

	private boolean populateArray() {
		boolean b = false;
		try {
			fis = new FileInputStream("Bank.dat");
			dis = new DataInputStream(fis);
			while(true) {
				for (int i = 0; i < 6; i++) {
					records[rows][i] = dis.readUTF();
				}
				rows++;
			}
		} catch (Exception ex) {
			total = rows;
			if (total == 0) {
				JOptionPane.showMessageDialog (null, "Records File is Empty.\nEnter Records First to Display.",
					 "BankSystem - EmptyFile", JOptionPane.PLAIN_MESSAGE);
				b = false;
			}
			else {
				b = true;
				try {
					dis.close();
					fis.close();
				}
				catch (Exception exp) { }
			}
		}
		return b;
	}

	private void findRec(String rec) {
		boolean found = false;
		for (int i = 0; i < total; i++) {
			if(records[i][0].equals(rec)) {
				found = true;
				printRecord(makeRecordPrint(i));
				break;
			}
		}
		if(found == false) {
			JOptionPane.showMessageDialog (null, "Account No. " + rec + " doesn't Exist.",
					 "BankSystem - WrongNo", JOptionPane.PLAIN_MESSAGE);
			getAccountNo ();
		}
	}

	private void printRecord(String rec) {
		StringReader sr = new StringReader (rec);
		LineNumberReader lnr = new LineNumberReader (sr);
		Font typeface = new Font ("Times New Roman", Font.PLAIN, 12);
		Properties p = new Properties ();
		PrintJob pJob = bankSystem.getToolkit().getPrintJob (bankSystem, "Print Customer Balance Report", p);
		
		if (pJob != null) {
			Graphics gr = pJob.getGraphics ();
			if (gr != null) {
				FontMetrics fm = gr.getFontMetrics (typeface);
				int margin = 20;
				int pageHeight = pJob.getPageDimension().height - margin;
    				int fontHeight = fm.getHeight();
	    			int fontDescent = fm.getDescent();
    				int curHeight = margin;
				String nextLine;
				gr.setFont (typeface);

				try {
					do {
						nextLine = lnr.readLine ();
						if (nextLine != null) {         
							if ((curHeight + fontHeight) > pageHeight) {	//New Page.
								gr.dispose();
								gr = pJob.getGraphics ();
								curHeight = margin;
							}							
							curHeight += fontHeight;
							if (gr != null) {
								gr.setFont (typeface);
								gr.drawString (nextLine, margin, curHeight - fontDescent);
							}
						}
					}
					while (nextLine != null);					
				}
				catch (EOFException eof) { }
				catch (Throwable t) { }
			}
			gr.dispose();
		}
		if (pJob != null) {
			pJob.end ();
		}
	}

	private String makeRecordPrint(int rec) {
		String data;
		String data0 = "               BankSystem [Pvt] Limited.               \n";	//Page Title.
		String data1 = "               Customer Balance Report.              \n\n";	//Page Header.
		String data2 = "  Account No.:       " + records[rec][0] + "\n";
		String data3 = "  Customer Name:     " + records[rec][1] + "\n";
		String data4 = "  Last Transaction:  " + records[rec][2] + ", " + records[rec][3] + ", " + records[rec][4] + "\n";
		String data5 = "  Current Balance:   " + records[rec][5] + "\n\n";
		String data6 = "          Copyright © 2003 Muhammad Wasif Javed.\n";	//Page Footer.
		String sep0 = " -----------------------------------------------------------\n";
		String sep1 = " -----------------------------------------------------------\n";
		String sep2 = " -----------------------------------------------------------\n";
		String sep3 = " -----------------------------------------------------------\n";
		String sep4 = " -----------------------------------------------------------\n\n";

		data = data0 + sep0 + data1 + data2 + sep1 + data3 + sep2 + data4 + sep3 + data5 + sep4 + data6;
		return data;
	}

	private boolean openChildWindow(String title) {
		JInternalFrame[] childs = bankSystem.desktop.getAllFrames();
		for (int i = 0; i < childs.length; i++) {
			if(childs[i].getTitle().equalsIgnoreCase(title)) {
				childs[i].show();
				return true;
			}
		}
		return false;
	}

}
