package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.BankConstants;
import controller.ControllerKeyAdapter;
import controller.ControllerWindrawMoneyFocusListener;
import controller.ControllerWithdrawMoney;

public class WithdrawMoney extends JInternalFrame implements BankConstants {
	
	private JPanel jpWith = new JPanel();
	private JLabel lbNo, lbName, lbDate, lbWithdraw;
	
	public JTextField txtNo, txtName, txtWithdraw;
	public JComboBox cboMonth, cboDay, cboYear;
	public JButton btnSave, btnCancel;

	public ControllerWithdrawMoney controllerWithdrawMoney;
	
	public WithdrawMoney() {
		super ("Withdraw Money", false, true, false, true);
		setSize (335, 235);

		jpWith.setLayout (null);
		
		lbNo = new JLabel ("Account No:");
		lbNo.setForeground (Color.black);
		lbNo.setBounds (15, 20, 80, 25);
	    lbName = new JLabel ("Person Name:");
		lbName.setForeground (Color.black);
	    lbName.setBounds (15, 55, 80, 25);
		lbDate = new JLabel ("With. Date:");
		lbDate.setForeground (Color.black);
		lbDate.setBounds (15, 90, 80, 25);
		lbWithdraw = new JLabel ("With. Amount:");
		lbWithdraw.setForeground (Color.black);
		lbWithdraw.setBounds (15, 125, 80, 25);
		
		txtNo = new JTextField ();
		txtNo.setHorizontalAlignment (JTextField.RIGHT);
		
		ControllerWindrawMoneyFocusListener controllerWindrawMoneyFocusListener = new ControllerWindrawMoneyFocusListener(this);
		txtNo.addFocusListener (controllerWindrawMoneyFocusListener);
		txtNo.setBounds (105, 20, 205, 25);
		
		txtName = new JTextField ();
		txtName.setEnabled (false);
		txtName.setBounds (105, 55, 205, 25);
		txtWithdraw = new JTextField ();
		txtWithdraw.setHorizontalAlignment (JTextField.RIGHT);
		txtWithdraw.setBounds (105, 125, 205, 25);
		
		ControllerKeyAdapter controllerKeyAdapter = new ControllerKeyAdapter(this);
		txtNo.addKeyListener (controllerKeyAdapter);
		txtWithdraw.addKeyListener (controllerKeyAdapter);
		
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
		
		controllerWithdrawMoney = new ControllerWithdrawMoney(this);
		
		btnSave = new JButton ("Save");
		btnSave.setBounds (20, 165, 120, 25);
		btnSave.addActionListener (controllerWithdrawMoney);
		
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (185, 165, 120, 25);
		btnCancel.addActionListener (controllerWithdrawMoney);
		
		jpWith.add (lbNo);
		jpWith.add (txtNo);
		jpWith.add (lbName);
		jpWith.add (txtName);
		jpWith.add (lbDate);
		jpWith.add (cboMonth);
		jpWith.add (cboDay);
		jpWith.add (cboYear);
		jpWith.add (lbWithdraw);
		jpWith.add (txtWithdraw);
		jpWith.add (btnSave);
		jpWith.add (btnCancel);
		
		getContentPane().add (jpWith);
		
		controllerWithdrawMoney.populateArray ();
		
		setVisible (true);
	}

}
