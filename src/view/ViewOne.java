package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControllerViewOne;

public class ViewOne extends JInternalFrame {
	
	private JPanel jpRec = new JPanel();
	private JLabel lbNo, lbName, lbDate, lbBal;
	
	public JTextField txtNo, txtName, txtDate, txtBal, txtRec;
	public JButton btnFirst, btnBack, btnNext, btnLast;
	
	public ViewOne() {
		super ("View Account Holders", false, true, false, true);
		setSize (350, 235);

		jpRec.setLayout (null);

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
		txtName.setEnabled (false);
		txtName.setBounds (125, 55, 200, 25);
		txtDate = new JTextField ();
		txtDate.setEnabled (false);
		txtDate.setBounds (125, 90, 200, 25);
		txtBal = new JTextField ();
		txtBal.setHorizontalAlignment (JTextField.RIGHT);
		txtBal.setEnabled (false);
		txtBal.setBounds (125, 125, 200, 25);
		
		ControllerViewOne controllerViewOne = new ControllerViewOne(this);
		
		btnFirst = new JButton ("<<");
		btnFirst.setBounds (15, 165, 50, 25);
		btnFirst.addActionListener (controllerViewOne);
		
		btnBack = new JButton ("<");
		btnBack.setBounds (65, 165, 50, 25);
		btnBack.addActionListener (controllerViewOne);
		
		btnNext = new JButton (">");
		btnNext.setBounds (225, 165, 50, 25);
		btnNext.addActionListener (controllerViewOne);
		
		btnLast = new JButton (">>");
		btnLast.setBounds (275, 165, 50, 25);
		btnLast.addActionListener (controllerViewOne);
		
		txtRec = new JTextField ();
		txtRec.setEnabled (false);
		txtRec.setHorizontalAlignment (JTextField.CENTER);
		txtRec.setBounds (115, 165, 109, 25);
		
		jpRec.add (lbNo);
		jpRec.add (txtNo);
		jpRec.add (lbName);
		jpRec.add (txtName);
		jpRec.add (lbDate);
		jpRec.add (txtDate);
		jpRec.add (lbBal);
		jpRec.add (txtBal);
		jpRec.add (btnFirst);
		jpRec.add (btnBack);
		jpRec.add (btnNext);
		jpRec.add (btnLast);
		jpRec.add (txtRec);
		
		getContentPane().add (jpRec);

		controllerViewOne.populateArray ();
		controllerViewOne.showRec (0);

		setVisible (true);
	}

}
