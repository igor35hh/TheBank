package controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import view.DeleteCustomer;

public class ControllerDeleteCustomerFocusListener implements FocusListener {
	
	private DeleteCustomer deleteCustomer;
	
	public ControllerDeleteCustomerFocusListener(DeleteCustomer deleteCustomer) {
		this.deleteCustomer = deleteCustomer;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (deleteCustomer.txtNo.getText().equals ("")) { }
		else {
			deleteCustomer.controllerDeleteCustomer.rows = 0;
			deleteCustomer.controllerDeleteCustomer.populateArray ();	//Load All Existing Records in Memory.
			deleteCustomer.controllerDeleteCustomer.findRec ();		//Finding if Account No. Already Exist or Not.
		}
	}

}
