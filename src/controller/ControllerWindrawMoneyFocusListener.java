package controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import view.WithdrawMoney;

public class ControllerWindrawMoneyFocusListener implements FocusListener {
	
	private WithdrawMoney newFrame;
	
	public ControllerWindrawMoneyFocusListener(WithdrawMoney newFrame) {
		this.newFrame = newFrame;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent fe) {
		if (newFrame.txtNo.getText().equals ("")) { }
		else {
			newFrame.controllerWithdrawMoney.populateArray ();
			newFrame.controllerWithdrawMoney.findRec ();		
		}
	}

}
