package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControllerFindName;

public class FindName extends JInternalFrame {
	
	private JPanel jpFind = new JPanel();
	private JLabel lbNo, lbName, lbDate, lbBal;
	
	public JTextField txtNo, txtName, txtDate, txtBal;
	public JButton btnFind, btnCancel;
	
	public FindName() {
		super ("Search Customer [By Name]", false, true, false, true);
		setSize (350, 235);

		jpFind.setLayout (null);

		lbNo = new JLabel ("Account No:");
		lbNo.setForeground (Color.black);
		lbNo.setBounds (15, 20, 80, 25);
	    lbName = new JLabel ("Person Name:");
		lbName.setForeground (Color.black);
	    lbName.setBounds (15, 55, 80, 25);
		lbDate = new JLabel ("Last Transaction:");
		lbDate.setForeground (Color.black);
		lbDate.setBounds (15, 90, 100, 25);
		lbBal = new JLabel ("Balance:");
		lbBal.setForeground (Color.black);
		lbBal.setBounds (15, 125, 80, 25);

		txtNo = new JTextField ();
		txtNo.setHorizontalAlignment (JTextField.RIGHT);
		txtNo.setEnabled (false);
		txtNo.setBounds (125, 20, 200, 25);
		txtName = new JTextField ();
		txtName.setBounds (125, 55, 200, 25);
		txtDate = new JTextField ();
		txtDate.setEnabled (false);
		txtDate.setBounds (125, 90, 200, 25);
		txtBal = new JTextField ();
		txtBal.setHorizontalAlignment (JTextField.RIGHT);
		txtBal.setEnabled (false);
		txtBal.setBounds (125, 125, 200, 25);
		
		ControllerFindName controllerFindName = new ControllerFindName(this);

		btnFind = new JButton ("Search");
		btnFind.setBounds (20, 165, 120, 25);
		btnFind.addActionListener (controllerFindName);
		
		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (200, 165, 120, 25);
		btnCancel.addActionListener (controllerFindName);
		
		jpFind.add (lbNo);
		jpFind.add (txtNo);
		jpFind.add (lbName);
		jpFind.add (txtName);
		jpFind.add (lbDate);
		jpFind.add (txtDate);
		jpFind.add (lbBal);
		jpFind.add (txtBal);
		jpFind.add (btnFind);
		jpFind.add (btnCancel);
		
		getContentPane().add (jpFind);
		controllerFindName.populateArray ();
		setVisible (true);
	}

}
