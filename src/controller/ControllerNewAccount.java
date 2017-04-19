package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import view.NewAccount;

public class ControllerNewAccount implements ActionListener {
	
	private NewAccount newAccount;
	
	private int count = 0;
	private int rows = 0;
	private	int total = 0;

	//String Type Array use to Load Records From File.
	private String records[][] = new String [500][6];

	//String Type Array use to Save Records into File.
	private String saves[][] = new String [500][6];

	private FileInputStream fis;
	private DataInputStream dis;
	
	public ControllerNewAccount(NewAccount newAccount) {
		super();
		this.newAccount = newAccount;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();

		if (obj == newAccount.btnSave) {
			if (newAccount.txtNo.getText().equals("")) {
				JOptionPane.showMessageDialog (null, "Please! Provide Id of Customer.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				newAccount.txtNo.requestFocus();
			}
			else if (newAccount.txtName.getText().equals("")) {
				JOptionPane.showMessageDialog (null, "Please! Provide Name of Customer.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				newAccount.txtName.requestFocus ();
			}
			else if (newAccount.txtDeposit.getText().equals("")) {
				JOptionPane.showMessageDialog (null, "Please! Provide Deposit Amount.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				newAccount.txtDeposit.requestFocus ();
			}
			else {
				populateArray ();	//Load All Existing Records in Memory.
				findRec ();		//Finding if Account No. Already Exist or Not.
			}
		}
		if (obj == newAccount.btnCancel) {
			txtClear ();
			newAccount.setVisible (false);
			newAccount.dispose();
		}
	}
	
	private void findRec() {
		boolean found = false;
		for (int x = 0; x < total; x++) {
			if (records[x][0].equals (newAccount.txtNo.getText())) {
				found = true;
				JOptionPane.showMessageDialog (null, "Account No. " + newAccount.txtNo.getText () + " is Already Exist.",
							"BankSystem - WrongNo", JOptionPane.PLAIN_MESSAGE);
				txtClear ();
				break;
			}
		}
		if (found == false) {
			saveArray ();
		}
	}

	private void txtClear() {
		newAccount.txtNo.setText ("");
		newAccount.txtName.setText ("");
		newAccount.txtDeposit.setText ("");
		newAccount.txtNo.requestFocus ();
	}
	
	private void saveFile() {
		try {
			FileOutputStream fos = new FileOutputStream ("Bank.dat", true);
			DataOutputStream dos = new DataOutputStream (fos);
			dos.writeUTF (saves[count][0]);
			dos.writeUTF (saves[count][1]);
			dos.writeUTF (saves[count][2]);
			dos.writeUTF (saves[count][3]);
			dos.writeUTF (saves[count][4]);
			dos.writeUTF (saves[count][5]);
			JOptionPane.showMessageDialog (null, "The Record has been Saved Successfully",
						"BankSystem - Record Saved", JOptionPane.PLAIN_MESSAGE);
			txtClear ();
			dos.close();
			fos.close();
		}
		catch (IOException ioe) {
			JOptionPane.showMessageDialog (null, "There are Some Problem with File",
						"BankSystem - Problem", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void populateArray() {
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
			if (total == 0) { }
			else {
				try {
					dis.close();
					fis.close();
				}
				catch (Exception exp) { }
			}
		}
	}
	
	private void saveArray() {
		saves[count][0] = newAccount.txtNo.getText ();
		saves[count][1] = newAccount.txtName.getText ();
		saves[count][2] = "" + newAccount.cboMonth.getSelectedItem ();
		saves[count][3] = "" + newAccount.cboDay.getSelectedItem ();
		saves[count][4] = "" + newAccount.cboYear.getSelectedItem ();
		saves[count][5] = newAccount.txtDeposit.getText ();
		saveFile ();	//Save This Array to File.
		count++;
	}

}
