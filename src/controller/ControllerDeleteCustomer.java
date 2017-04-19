package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import view.DeleteCustomer;

public class ControllerDeleteCustomer implements ActionListener {
	
	private DeleteCustomer deleteCustomer;
	
	private int recCount = 0;
	public int rows = 0;
	private	int total = 0;

	//String Type Array use to Load Records From File.
	private String records[][] = new String [500][6];

	private FileInputStream fis;
	private DataInputStream dis;
	
	public ControllerDeleteCustomer(DeleteCustomer deleteCustomer) {
		super();
		this.deleteCustomer = deleteCustomer;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();

		if (obj == deleteCustomer.btnDel) {
			if (deleteCustomer.txtNo.getText().equals("")) {
				JOptionPane.showMessageDialog (null, "Please! Provide Id of Customer.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				deleteCustomer.txtNo.requestFocus();
			}
			else {
				deletion ();	
			}
		}
		if (obj == deleteCustomer.btnCancel) {
			txtClear ();
			deleteCustomer.setVisible (false);
			deleteCustomer.dispose();
		}
	}
	
	protected void findRec() {
		boolean found = false;
		for (int x = 0; x < total; x++) {
			if (records[x][0].equals (deleteCustomer.txtNo.getText())) {
				found = true;
				showRec (x);
				break;
			}
		}
		if (found == false) {
			String str = deleteCustomer.txtNo.getText ();
			txtClear ();
			JOptionPane.showMessageDialog (null, "Account No. " + str + " doesn't Exist.",
					"BankSystem - WrongNo", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void showRec(int intRec) {
		deleteCustomer.txtNo.setText (records[intRec][0]);
		deleteCustomer.txtName.setText (records[intRec][1]);
		deleteCustomer.txtDate.setText (records[intRec][2] + ", " + records[intRec][3] + ", " + records[intRec][4]);
		deleteCustomer.txtBal.setText (records[intRec][5]);
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
		deleteCustomer.txtNo.setEnabled (false);
		deleteCustomer.btnDel.setEnabled (false);
	}

	private void txtClear() {
		deleteCustomer.txtNo.setText ("");
		deleteCustomer.txtName.setText ("");
		deleteCustomer.txtDate.setText ("");
		deleteCustomer.txtBal.setText ("");
		deleteCustomer.txtNo.requestFocus ();
	}

	private void deletion() {
		try {
		    int reply = JOptionPane.showConfirmDialog (null,
					"Are you Sure you want to Delete\n" + deleteCustomer.txtName.getText () + " Record From BankSystem?",
					"Bank System - Delete", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (reply == JOptionPane.YES_OPTION) {
				delRec ();
			}
			else if (reply == JOptionPane.NO_OPTION) { }
		} 

		catch (Exception e) {}
	}

	private void delRec() {
		try {
			if (records != null) {
				for(int i = recCount; i < total; i++) {
					for (int r = 0; r < 6; r++) {
						records[i][r] = records[i+1][r];				
						if (records[i][r] == null) break;
					}
				}
				total = total - 1;
				deleteFile ();
			}
		}
		catch (ArrayIndexOutOfBoundsException ex) { }
	}

	private void deleteFile() {
		try {
			FileOutputStream fos = new FileOutputStream ("Bank.dat");
			DataOutputStream dos = new DataOutputStream (fos);
			if (records != null) {
				for (int i = 0; i < total; i++) {
					for (int r = 0; r < 6; r++) {
						dos.writeUTF (records[i][r]);
						if (records[i][r] == null) break;
					}
				}
				JOptionPane.showMessageDialog (null, "Record has been Deleted Successfuly.",
					"BankSystem - Record Deleted", JOptionPane.PLAIN_MESSAGE);
				txtClear ();
			}
			else { }
			dos.close();
			fos.close();
		}
		catch (IOException ioe) {
			JOptionPane.showMessageDialog (null, "There are Some Problem with File",
						"BankSystem - Problem", JOptionPane.PLAIN_MESSAGE);
		}
	}

}
