package dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveOrRead {
	
	private static int count = 0;
	private static int rows = 0;
	private	static int total = 0;
	
	private static String records[][] = new String [500][6];

	private static FileInputStream fis;
	private static DataInputStream dis;

	public synchronized static int readData() {
		
		try {
			fis = new FileInputStream ("Bank.dat");
			dis = new DataInputStream (fis);
			//Loop to Populate the Array.
			while (true) {
				for (int i = 0; i < 6; i++) {
					records[rows][i] = dis.readUTF ();
				}
				rows++;
			}
		}
		catch (Exception ex) {
			total = rows;
			if (total == 0) { }
			else {
				try {
					dis.close();
					fis.close();
				}
				catch (Exception exp) { }
			}
			
		} finally {
			return total;
		}
	}
	
	public synchronized static  void saveData(String saves[][]) throws IOException {
		
		FileOutputStream fos = new FileOutputStream ("Bank.dat", true);
		DataOutputStream dos = new DataOutputStream (fos);
		
		dos.writeUTF (saves[count][0]);
		dos.writeUTF (saves[count][1]);
		dos.writeUTF (saves[count][2]);
		dos.writeUTF (saves[count][3]);
		dos.writeUTF (saves[count][4]);
		dos.writeUTF (saves[count][5]);
		
		dos.close();
		fos.close();

	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		SaveOrRead.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		SaveOrRead.total = total;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		SaveOrRead.rows = rows;
	}
	
	public static String[][] getRecords() {
		return records;
	}

	public static void setRecords(String[][] records) {
		SaveOrRead.records = records;
	}

}
