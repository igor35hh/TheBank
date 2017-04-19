package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import view.BankSystem;

public class ControllerBankSystemItemListener implements ItemListener {
	
	private BankSystem bankSystem;
	
	public ControllerBankSystemItemListener(BankSystem bankSystem) {
		this.bankSystem = bankSystem;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		for (int i = 0; i < bankSystem.radio.length; i++) {
			if(bankSystem.radio[i].isSelected()) {
				changelLookAndFeel(i);
			}
		}
	}
	
	private void changelLookAndFeel(int i) {
		try{
			UIManager.setLookAndFeel(bankSystem.looks[i].getClassName());
			SwingUtilities.updateComponentTreeUI(bankSystem);
		} catch (Exception e) {
			
		}
	}

}
