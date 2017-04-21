package model;


import dao.Factory;

public class BankData {
	
	public static int total = 0;
	public static String records[][] = new String [500][6];
	
	public synchronized static boolean populateArray() {
		boolean success = false;
		try {
			Factory.readData();
			records = Factory.getRecords();
			total = Factory.getTotal();
			success = true;
		} finally {
			return success;
		}
	}
	
	public synchronized static boolean saveFile(String saves[][], int count) {
		boolean success = false;
		try {
			Factory.saveData(saves, count);
			success = true;
		} finally {
			return success;
		}
	}
	
	public synchronized static boolean deleteFile(String saves[][], int count) {
		boolean success = false;
		try {
			Factory.updateData(saves, count);
			success = true;
		} finally {
			return success;
		}
	}
	
	public synchronized static boolean updateFile(String saves[][], int count) {
		boolean success = false;
		try {
			Factory.updateData(saves, count);
			success = true;
		} finally {
			return success;
		}
	}

}
