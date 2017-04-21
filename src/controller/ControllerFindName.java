package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.BankData;
import view.FindName;

public class ControllerFindName implements ActionListener {
	
	private FindName findName;
	
	private	int total = 0;

	//String Type Array use to Load Records From File.
	private String records[][] = new String [500][6];
	
	public ControllerFindName(FindName findName) {
		super();
		this.findName = findName;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();

		if (obj == findName.btnFind) {
			if (findName.txtName.getText().equals ("")) {
				JOptionPane.showMessageDialog (null, "Please! Provide Name of Customer to Search.",
							"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				findName.txtName.requestFocus();
			}
			else {
				populateArray ();	//Load All Existing Records in Memory.
				findRec ();		//Finding if Account No. Exist or Not.
			}
		}
		if (obj == findName.btnCancel) {
			txtClear ();
			findName.setVisible (false);
			findName.dispose();
		}
	}
	
	private void findRec() {
		boolean found = false;
		for (int x = 0; x < total; x++) {
			if (records[x][1].equalsIgnoreCase (findName.txtName.getText())) {
				found = true;
				showRec (x);
				break;
			}
		}
		if (found == false) {
			JOptionPane.showMessageDialog (null, "Customer " + findName.txtName.getText () + " doesn't Exist.",
							"BankSystem - WrongNo", JOptionPane.PLAIN_MESSAGE);
			txtClear ();
		}
	}

	private void txtClear() {
		findName.txtNo.setText ("");
		findName.txtName.setText ("");
		findName.txtDate.setText ("");
		findName.txtBal.setText ("");
		findName.txtName.requestFocus ();
	}

	private void showRec(int intRec) {
		findName.txtNo.setText (records[intRec][0]);
		findName.txtName.setText (records[intRec][1]);
		findName.txtDate.setText (records[intRec][2] + ", " + records[intRec][3] + ", " + records[intRec][4]);
		findName.txtBal.setText (records[intRec][5]);
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
		findName.txtName.setEnabled (false);
		findName.btnFind.setEnabled (false);
	}

}
