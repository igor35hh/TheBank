package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.FileInputStream;

import javax.swing.JOptionPane;

import view.ViewOne;

public class ControllerViewOne implements ActionListener {
	
	private ViewOne viewOne;
	
	private int recCount = 0;
	private int rows = 0;
	private	int total = 0;

	//String Type Array use to Load Records From File.
	private String records[][] = new String [500][6];

	private FileInputStream fis;
	private DataInputStream dis;
	
	public ControllerViewOne(ViewOne viewOne) {
		super();
		this.viewOne = viewOne;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();

		if (obj == viewOne.btnFirst) {
			recCount = 0;
			showRec (recCount);
		}
		else if (obj == viewOne.btnBack) {
			recCount = recCount - 1;
			if (recCount < 0) {
				recCount = 0;
				showRec (recCount);
				JOptionPane.showMessageDialog (null, "You are on First Record.",
						"BankSystem - 1st Record", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				showRec (recCount);
			}
		}
		else if (obj == viewOne.btnNext) {
			recCount = recCount + 1;
			if (recCount == total) {
				recCount = total - 1;
				showRec (recCount);
				JOptionPane.showMessageDialog (null, "You are on Last Record.",
						"BankSystem - End of Records", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				showRec (recCount);
			}
		}
		else if (obj == viewOne.btnLast) {
			recCount = total - 1;
			showRec (recCount);
		}
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
		viewOne.btnFirst.setEnabled (false);
		viewOne.btnBack.setEnabled (false);
		viewOne.btnNext.setEnabled (false);
		viewOne.btnLast.setEnabled (false);
	}

	public void showRec(int intRec) {
		viewOne.txtNo.setText (records[intRec][0]);
		viewOne.txtName.setText (records[intRec][1]);
		viewOne.txtDate.setText (records[intRec][2] + ", " + records[intRec][3] + ", " + records[intRec][4]);
		viewOne.txtBal.setText (records[intRec][5]);
		if (total == 0) { 
			viewOne.txtRec.setText (intRec + "/" + total); //Showing Record No. and Total Records.
			viewOne.txtDate.setText ("");
		}
		else {
			viewOne.txtRec.setText ((intRec + 1) + "/" + total); //Showing Record No. and Total Records.
		}
	}

}
