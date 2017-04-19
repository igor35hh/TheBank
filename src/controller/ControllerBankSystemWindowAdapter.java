package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.BankSystem;

public class ControllerBankSystemWindowAdapter extends WindowAdapter {
	
	private BankSystem bankSystem;
	
	public ControllerBankSystemWindowAdapter(BankSystem bankSystem) {
		super();
		this.bankSystem = bankSystem;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		bankSystem.controllerBankSystem.quitApp();
	}

}
