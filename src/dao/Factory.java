package dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Factory {
	
	private	static int total = 0;
	
	private static String records[][] = new String [500][6];

	private static FileInputStream fis;
	private static DataInputStream dis;
	
	private static FileOutputStream fos;
	private static DataOutputStream dos;

	public static void readData() {
		
		int rows = 0;
		
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
			
		} finally {
			total = rows;
			try {
				dis.close();
				fis.close();
			}
			catch (Exception exp) { }
		}
	}
	
	public static void saveData(String saves[][], int count) {
		
		try {
		
			FileOutputStream fos = new FileOutputStream ("Bank.dat", true);
			DataOutputStream dos = new DataOutputStream (fos);
			
			dos.writeUTF (saves[count][0]);
			dos.writeUTF (saves[count][1]);
			dos.writeUTF (saves[count][2]);
			dos.writeUTF (saves[count][3]);
			dos.writeUTF (saves[count][4]);
			dos.writeUTF (saves[count][5]);
		
		} catch (Exception ex) {
			
		} finally {
			try {
				dos.close();
				fos.close();
			}
			catch (Exception exp) { }
		}

	}
	
	public static void updateData(String saves[][], int count) {
		
		try {
		
			FileOutputStream fos = new FileOutputStream ("Bank.dat", false);
			DataOutputStream dos = new DataOutputStream (fos);
			
			if (saves != null) {
				
				for (int i = 0; i < count; i++) {
					for (int r = 0; r < 6; r++) {
						dos.writeUTF (saves[i][r]);
						if (saves[i][r] == null) break;
					}
				}
				
			}
			
			dos.close();
			fos.close();
			
		} catch (Exception ex) {
			
		} finally {
			try {
				dos.close();
				fos.close();
			}
			catch (Exception exp) { }
		}	

	}

	public static int getTotal() {
		return total;
	}

	public static String[][] getRecords() {
		return records;
	}

}
