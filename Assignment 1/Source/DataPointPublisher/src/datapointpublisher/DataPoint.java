package datapointpublisher;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class DataPoint {

	public static HashMap<Integer, User> userRecords = new HashMap<Integer, User>();
	public static User usr = null;
	public static String filePath = "/home/nipun/user.csv";

	public DataPoint() {
		try {
			if (userRecords.size() == 0)
				userRecords.put(1, new User(1, "root", "test", "root@test.com", "test123"));
			File obj = new File(filePath);
			if (obj.exists()) {
				Scanner reader = new Scanner(obj);
				while (reader.hasNextLine()) {
					String record = reader.nextLine();
					String[] data = record.split(",");
					if (userRecords.get(data[0]) == null) {
						userRecords.put(Integer.parseInt(data[0]),
								new User(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]));
					}
				}
				reader.close();
			} else {
				FileWriter writer = new FileWriter(filePath);
				writer.write("1,root,test,root@test.com,test123");
				writer.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updatePassword(int id, String password) {
		try {
			File obj = new File(filePath);
			if (obj.exists()) {
				String output = "";
				for (int i : userRecords.keySet()) {
					output += userRecords.get(i).getId() + "," + userRecords.get(i).getFirstName() + ","
							+ userRecords.get(i).getLastName() + "," + userRecords.get(i).getEmail() + ","
							+ userRecords.get(i).getPassword() + "\n";
				}
				FileWriter writer = new FileWriter(DataPoint.filePath);
				writer.write(output);
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteRecord(int id) {
		try {
			File obj = new File(filePath);
			if (obj.exists()) {
				String output = "";
				for (int i : userRecords.keySet()) {
					output += userRecords.get(i).getId() + "," + userRecords.get(i).getFirstName() + ","
							+ userRecords.get(i).getLastName() + "," + userRecords.get(i).getEmail() + ","
							+ userRecords.get(i).getPassword() + "\n";
				}
				FileWriter writer = new FileWriter(DataPoint.filePath);
				writer.write(output);
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
