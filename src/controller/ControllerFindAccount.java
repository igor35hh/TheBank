package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.FileInputStream;

import javax.swing.JOptionPane;

import view.FindAccount;

public class ControllerFindAccount implements ActionListener {
	
	private FindAccount findAccount;
	
	private int rows = 0;
	private	int total = 0;

	//String Type Array use to Load Records From File.
	private String records[][] = new String [500][6];

	private FileInputStream fis;
	private DataInputStream dis;
	
	public ControllerFindAccount(FindAccount findAccount) {
		super();
		this.findAccount = findAccount;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();

		if (obj == findAccount.btnFind) {
			if (findAccount.txtNo.getText().equals("")) {
				JOptionPane.showMessageDialog (null, "Please! Provide Id of Customer to Search.",
							"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				findAccount.txtNo.requestFocus();
			}
			else {
				rows = 0;
				populateArray ();	
				findRec ();	
			}
		}
		if (obj == findAccount.btnCancel) {
			txtClear ();
			findAccount.setVisible (false);
			findAccount.dispose();
		}
	}
	
	private void findRec() {
		boolean found = false;
		for (int x = 0; x < total; x++) {
			if (records[x][0].equals (findAccount.txtNo.getText())) {
				found = true;
				showRec (x);
				break;
			}
		}
		if (found == false) {
			JOptionPane.showMessageDialog (null, "Account No. " + findAccount.txtNo.getText () + " doesn't Exist.",
							"BankSystem - WrongNo", JOptionPane.PLAIN_MESSAGE);
			txtClear ();
		}
	}

	private void showRec(int intRec) {
		findAccount.txtNo.setText (records[intRec][0]);
		findAccount.txtName.setText (records[intRec][1]);
		findAccount.txtDate.setText (records[intRec][2] + ", " + records[intRec][3] + ", " + records[intRec][4]);
		findAccount.txtBal.setText (records[intRec][5]);
	}

	private void txtClear() {
		findAccount.txtNo.setText ("");
		findAccount.txtName.setText ("");
		findAccount.txtDate.setText ("");
		findAccount.txtBal.setText ("");
		findAccount.txtNo.requestFocus ();
	}

	public void populateArray() {
		try {
			fis = new FileInputStream ("Bank.dat");
			dis = new DataInputStream (fis);
			//Loop to Populate the Array.
			while (true) {
				for (int i = 0; i < 6; i++) {
					records[rows][i] = dis.readUTF ();
				}
				rows++;
			}
		}
		catch (Exception ex) {
			total = rows;
			if (total == 0) {
				JOptionPane.showMessageDialog (null, "Records File is Empty.\nEnter Records First to Display.",
							"BankSystem - EmptyFile", JOptionPane.PLAIN_MESSAGE);
				btnEnable ();
			}
			else {
				try {
					dis.close();
					fis.close();
				}
				catch (Exception exp) { }
			}
		}
	}

	private void btnEnable() {
		findAccount.txtNo.setEnabled (false);
		findAccount.btnFind.setEnabled (false);
	}

}
