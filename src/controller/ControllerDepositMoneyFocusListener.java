package controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import view.DepositMoney;

public class ControllerDepositMoneyFocusListener implements FocusListener {
	
	private DepositMoney newFrame;
	
	public ControllerDepositMoneyFocusListener(DepositMoney newFrame) {
		this.newFrame = newFrame;
	}

	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent fe) {
		if (newFrame.txtNo.getText().equals ("")) { }
		else {
			newFrame.controllerDepositMoney.populateArray ();
			newFrame.controllerDepositMoney.findRec ();		
		}
	}

}
