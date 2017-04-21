package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.BankData;
import view.DepositMoney;

public class ControllerDepositMoney implements ActionListener {

	private DepositMoney depositMoney;
	
	private int recCount = 0;
	private	int total = 0;
	private	int curr;
	private	int deposit;
	
	private String records[][] = new String [500][6];
	
	public ControllerDepositMoney(DepositMoney depositMoney) {
		super();
		this.depositMoney = depositMoney;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();

		if (obj == depositMoney.btnSave) {
			if (depositMoney.txtNo.getText().equals("")) {
				JOptionPane.showMessageDialog (null, "Please! Provide Id of Customer.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				depositMoney.txtNo.requestFocus();
			}
			else if (depositMoney.txtDeposit.getText().equals("")) {
				JOptionPane.showMessageDialog (null, "Please! Provide Deposit Amount.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				depositMoney.txtDeposit.requestFocus ();
			}
			else {
				editRec ();	
			}
		}
		if (obj == depositMoney.btnCancel) {
			txtClear ();
			depositMoney.setVisible (false);
			depositMoney.dispose();
		}
	}
	
	protected void findRec() {
		boolean found = false;
		for (int x = 0; x < total; x++) {
			if (records[x][0].equals (depositMoney.txtNo.getText())) {
				found = true;
				showRec (x);
				break;
			}
		}
		if (found == false) {
			String str = depositMoney.txtNo.getText ();
			txtClear ();
			JOptionPane.showMessageDialog (null, "Account No. " + str + " doesn't Exist.",
						"BankSystem - WrongNo", JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void showRec(int intRec) {
		depositMoney.txtNo.setText (records[intRec][0]);
		depositMoney.txtName.setText (records[intRec][1]);
		curr = Integer.parseInt (records[intRec][5]);
		recCount = intRec;
	}

	public void txtClear() {
		depositMoney.txtNo.setText ("");
		depositMoney.txtName.setText ("");
		depositMoney.txtDeposit.setText ("");
		depositMoney.txtNo.requestFocus ();
	}

	public void populateArray() {
		
		if(BankData.populateArray()) {
			total   = BankData.total;
			records = BankData.records;
		} 
		
		if (total == 0) {
			JOptionPane.showMessageDialog (null, "Records File is Empty.\nEnter Records First to Display.",
						"BankSystem - EmptyFile", JOptionPane.PLAIN_MESSAGE);
			btnEnable ();
		}
		
	}

	private void btnEnable() {
		depositMoney.txtNo.setEnabled (false);
		depositMoney.cboMonth.setEnabled (false);
		depositMoney.cboDay.setEnabled (false);
		depositMoney.cboYear.setEnabled (false);
		depositMoney.txtDeposit.setEnabled (false);
		depositMoney.btnSave.setEnabled (false);
	}
	
	private void editRec() {
		deposit = Integer.parseInt (depositMoney.txtDeposit.getText ());
		records[recCount][0] = depositMoney.txtNo.getText ();
		records[recCount][1] = depositMoney.txtName.getText ();
		records[recCount][2] = "" + depositMoney.cboMonth.getSelectedItem ();
		records[recCount][3] = "" + depositMoney.cboDay.getSelectedItem ();
		records[recCount][4] = "" + depositMoney.cboYear.getSelectedItem ();
		records[recCount][5] = "" + (curr + deposit);
		editFile ();
	}

	private void editFile() {
		
		if(BankData.updateFile(records, total)) {
			JOptionPane.showMessageDialog (null, "The File is Updated Successfully",
					"BankSystem - Record Saved", JOptionPane.PLAIN_MESSAGE);
			txtClear ();
		} else {
			JOptionPane.showMessageDialog (null, "There are Some Problem with File",
					"BankSystem - Problem", JOptionPane.PLAIN_MESSAGE);
		}
		
	}

}
