package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControllerDeleteCustomer;
import controller.ControllerDeleteCustomerFocusListener;
import controller.ControllerKeyAdapter;

public class DeleteCustomer extends JInternalFrame {

	private JPanel jpDel = new JPanel();
	private JLabel lbNo, lbName, lbDate, lbBal;
	
	public JTextField txtNo, txtName, txtDate, txtBal;
	public JButton btnDel, btnCancel;
	
	public ControllerDeleteCustomer controllerDeleteCustomer;
	
	public DeleteCustomer() {
		super ("Delete Account Holder", false, true, false, true);
		setSize (350, 235);

		jpDel.setLayout (null);

		lbNo = new JLabel ("Account No:");
		lbNo.setForeground (Color.black);
		lbNo.setBounds (15, 20, 80, 25);
	    lbName = new JLabel ("Person Name:");
		lbName.setForeground (Color.black);
	    lbName.setBounds (15, 55, 90, 25);
		lbDate = new JLabel ("Last Transaction:");
		lbDate.setForeground (Color.black);
		lbDate.setBounds (15, 90, 100, 25);
		lbBal = new JLabel ("Balance:");
		lbBal.setForeground (Color.black);
		lbBal.setBounds (15, 125, 80, 25);
		
		txtNo = new JTextField ();
		txtNo.setHorizontalAlignment (JTextField.RIGHT);
		txtNo.setBounds (125, 20, 200, 25);
		txtName = new JTextField ();
		txtName.setEnabled (false);
		txtName.setBounds (125, 55, 200, 25);
		txtDate = new JTextField ();
		txtDate.setEnabled (false);
		txtDate.setBounds (125, 90, 200, 25);
		txtBal = new JTextField ();
		txtBal.setEnabled (false);
		txtBal.setHorizontalAlignment (JTextField.RIGHT);
		txtBal.setBounds (125, 125, 200, 25);
		
		controllerDeleteCustomer = new ControllerDeleteCustomer(this);
		
		btnDel = new JButton ("Delete");
		btnDel.setBounds (20, 165, 120, 25);
		btnDel.addActionListener (controllerDeleteCustomer);
		
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (200, 165, 120, 25);
		btnCancel.addActionListener (controllerDeleteCustomer);
		
		jpDel.add (lbNo);
		jpDel.add (txtNo);
		jpDel.add (lbName);
		jpDel.add (txtName);
		jpDel.add (lbDate);
		jpDel.add (txtDate);
		jpDel.add (lbBal);
		jpDel.add (txtBal);
		jpDel.add (btnDel);
		jpDel.add (btnCancel);
		
		ControllerKeyAdapter controllerKeyAdapter = new ControllerKeyAdapter(this);
		txtNo.addKeyListener (controllerKeyAdapter);
		
		ControllerDeleteCustomerFocusListener controllerDeleteCustomerFocusListener = new ControllerDeleteCustomerFocusListener(this);
		txtNo.addFocusListener (controllerDeleteCustomerFocusListener);
		
		getContentPane().add (jpDel);
		
		controllerDeleteCustomer.populateArray ();
		
		setVisible (true);
	}

}
