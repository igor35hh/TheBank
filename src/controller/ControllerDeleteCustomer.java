package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.BankData;
import view.DeleteCustomer;

public class ControllerDeleteCustomer implements ActionListener {
	
	private DeleteCustomer deleteCustomer;
	
	private int recCount = 0;
	private	int total = 0;

	//String Type Array use to Load Records From File.
	private String records[][] = new String [500][6];
	
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
		
		if(BankData.populateArray()) {
			total   = BankData.total;
			records = BankData.records;
		} else {
			btnEnable ();
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
		
		if(BankData.deleteFile(records, total)) {
			JOptionPane.showMessageDialog (null, "Record has been Deleted Successfuly.",
					"BankSystem - Record Deleted", JOptionPane.PLAIN_MESSAGE);
			txtClear ();
		} else {
			JOptionPane.showMessageDialog (null, "There are Some Problem with File",
					"BankSystem - Problem", JOptionPane.PLAIN_MESSAGE);
		}
		
	}

}
