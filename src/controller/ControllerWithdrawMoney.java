package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import view.WithdrawMoney;

public class ControllerWithdrawMoney implements ActionListener {
	
	private WithdrawMoney withdrawMoney;
	
	private int recCount = 0;
	public int rows = 0;
	private	int total = 0;
	private	int curr;
	private	int withdraw;

	//String Type Array use to Load Records From File.
	private String records[][] = new String [500][6];

	private FileInputStream fis;
	private DataInputStream dis;
	
	public ControllerWithdrawMoney(WithdrawMoney withdrawMoney) {
		this.withdrawMoney = withdrawMoney;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();

		if (obj == withdrawMoney.btnSave) {
			if (withdrawMoney.txtNo.getText().equals("")) {
				JOptionPane.showMessageDialog (null, "Please! Provide Id of Customer.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				withdrawMoney.txtNo.requestFocus();
			}
			else if (withdrawMoney.txtWithdraw.getText().equals("")) {
				JOptionPane.showMessageDialog (null, "Please! Provide Withdraw Amount.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				withdrawMoney.txtWithdraw.requestFocus ();
			}
			else {
				withdraw = Integer.parseInt (withdrawMoney.txtWithdraw.getText ());
				if (curr == 0) {
					JOptionPane.showMessageDialog (null, withdrawMoney.txtName.getText () + " doesn't have any Amount in Balance.",
							"BankSystem - EmptyAccount", JOptionPane.PLAIN_MESSAGE);
					txtClear ();
				}
				else if (withdraw > curr) {
					JOptionPane.showMessageDialog (null, "Withdraw Amount can't greater than Actual Balance.",
							"BankSystem - Large Amount", JOptionPane.PLAIN_MESSAGE);
					withdrawMoney.txtWithdraw.setText ("");
					withdrawMoney.txtWithdraw.requestFocus ();
				}
				else {
					editRec ();	//Update the Contents of Array.
				}
			}
		}
		if (obj == withdrawMoney.btnCancel) {
			txtClear ();
			withdrawMoney.setVisible (false);
			withdrawMoney.dispose();
		}
	}
	
	public void findRec() {
		boolean found = false;
		for (int x = 0; x < total; x++) {
			if (records[x][0].equals (withdrawMoney.txtNo.getText())) {
				found = true;
				showRec (x);
				break;
			}
		}
		if (found == false) {
			String str = withdrawMoney.txtNo.getText ();
			txtClear ();
			JOptionPane.showMessageDialog (null, "Account No. " + str + " doesn't Exist.",
						"BankSystem - WrongNo", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void showRec(int intRec) {
		withdrawMoney.txtNo.setText (records[intRec][0]);
		withdrawMoney.txtName.setText (records[intRec][1]);
		curr = Integer.parseInt (records[intRec][5]);
		recCount = intRec;
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
		withdrawMoney.txtNo.setEnabled (false);
		withdrawMoney.cboMonth.setEnabled (false);
		withdrawMoney.cboDay.setEnabled (false);
		withdrawMoney.cboYear.setEnabled (false);
		withdrawMoney.txtWithdraw.setEnabled (false);
		withdrawMoney.btnSave.setEnabled (false);
	}

	private void editRec() {
		records[recCount][0] = withdrawMoney.txtNo.getText ();
		records[recCount][1] = withdrawMoney.txtName.getText ();
		records[recCount][2] = "" + withdrawMoney.cboMonth.getSelectedItem ();
		records[recCount][3] = "" + withdrawMoney.cboDay.getSelectedItem ();
		records[recCount][4] = "" + withdrawMoney.cboYear.getSelectedItem ();
		records[recCount][5] = "" + (curr - withdraw);
		editFile ();
	}

	private void editFile() {
		try {
			FileOutputStream fos = new FileOutputStream ("Bank.dat");
			DataOutputStream dos = new DataOutputStream (fos);
			if (records != null) {
				for (int i = 0; i < total; i++) {
					for (int c = 0; c < 6; c++) {
						dos.writeUTF (records[i][c]);
						if (records[i][c] == null) break;
					}
				}
				JOptionPane.showMessageDialog (null, "The File is Updated Successfully",
						"BankSystem - Record Saved", JOptionPane.PLAIN_MESSAGE);
				txtClear ();
				dos.close();
				fos.close();
			}
		}
		catch (IOException ioe) {
			JOptionPane.showMessageDialog (null, "There are Some Problem with File",
					"BankSystem - Problem", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void txtClear() {
		withdrawMoney.txtNo.setText ("");
		withdrawMoney.txtName.setText ("");
		withdrawMoney.txtWithdraw.setText ("");
		withdrawMoney.txtNo.requestFocus ();
	}

}
