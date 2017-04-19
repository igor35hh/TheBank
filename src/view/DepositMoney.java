package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.BankConstants;
import controller.ControllerDepositMoney;
import controller.ControllerDepositMoneyFocusListener;
import controller.ControllerKeyAdapter;

public class DepositMoney extends JInternalFrame implements BankConstants {
	
	private JPanel jpDep = new JPanel();
	private JLabel lbNo, lbName, lbDate, lbDeposit;
	
	public JTextField txtNo, txtName, txtDeposit;
	public JComboBox cboMonth, cboDay, cboYear;
	public JButton btnSave, btnCancel;
	
	public ControllerDepositMoney controllerDepositMoney;

	public DepositMoney() {
		super ("Deposit Money", false, true, false, true);
		setSize (335, 235);

		jpDep.setLayout (null);
		
		lbNo = new JLabel ("Account No:");
		lbNo.setForeground (Color.black);
		lbNo.setBounds (15, 20, 80, 25);
		
	    lbName = new JLabel ("Person Name:");
		lbName.setForeground (Color.black);
	    lbName.setBounds (15, 55, 80, 25);
	    
		lbDate = new JLabel ("Deposit Date:");
		lbDate.setForeground (Color.black);
		lbDate.setBounds (15, 90, 80, 25);
		
		lbDeposit = new JLabel ("Dep. Amount:");
		lbDeposit.setForeground (Color.black);
		lbDeposit.setBounds (15, 125, 80, 25);
		
		txtNo = new JTextField ();
		txtNo.setHorizontalAlignment (JTextField.RIGHT);
		txtNo.setBounds (105, 20, 205, 25);
		
		txtName = new JTextField ();
		txtName.setEnabled (false);
		txtName.setBounds (105, 55, 205, 25);
		
		txtDeposit = new JTextField ();
		txtDeposit.setHorizontalAlignment (JTextField.RIGHT);
		txtDeposit.setBounds (105, 125, 205, 25);
		
		cboMonth = new JComboBox (Months);
		cboDay = new JComboBox ();
		cboYear = new JComboBox ();
		
		for (int i = 1; i <= DaysPerMonth; i++) {
			String days = "" + i;
			cboDay.addItem (days);
		}
		for (int i = YearFrom; i <= YearTo; i++) {
			String years = "" + i;
			cboYear.addItem (years);
		}
		
		cboMonth.setBounds (105, 90, 92, 25);
		cboDay.setBounds (202, 90, 43, 25);
		cboYear.setBounds (250, 90, 60, 25);
		
		controllerDepositMoney = new ControllerDepositMoney(this);
		
		btnSave = new JButton ("Save");
		btnSave.setBounds (20, 165, 120, 25);
		btnSave.addActionListener (controllerDepositMoney);
		
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (185, 165, 120, 25);
		btnCancel.addActionListener (controllerDepositMoney);
		
		ControllerKeyAdapter controllerKeyAdapter = new ControllerKeyAdapter(this);
		
		txtNo.addKeyListener (controllerKeyAdapter);
		txtDeposit.addKeyListener (controllerKeyAdapter);
		
		ControllerDepositMoneyFocusListener controllerDepositMoneyFocusListener = new ControllerDepositMoneyFocusListener(this);
		txtNo.addFocusListener (controllerDepositMoneyFocusListener);
		
		jpDep.add (lbNo);
		jpDep.add (txtNo);
		jpDep.add (lbName);
		jpDep.add (txtName);
		jpDep.add (lbDate);
		jpDep.add (cboMonth);
		jpDep.add (cboDay);
		jpDep.add (cboYear);
		jpDep.add (lbDeposit);
		jpDep.add (txtDeposit);
		jpDep.add (btnSave);
		jpDep.add (btnCancel);
		
		getContentPane().add (jpDep);

		controllerDepositMoney.populateArray ();	

		setVisible (true);
		
	}

}
