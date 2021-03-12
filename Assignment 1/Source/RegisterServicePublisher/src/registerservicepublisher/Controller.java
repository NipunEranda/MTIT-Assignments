package registerservicepublisher;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import datapointpublisher.DataPoint;
import datapointpublisher.DataPointService;
import datapointpublisher.User;

public class Controller {

	public DataPointService dpService;

	public Controller(DataPointService dpService) {
		this.dpService = dpService;
	}

	public boolean register(User login) {
		try {
			ArrayList<String> userList = new ArrayList<String>();
			File obj = new File(DataPoint.filePath);
			if (obj.exists()) {
				Scanner reader = new Scanner(obj);
				while (reader.hasNextLine()) {
					userList.add(reader.nextLine());
				}
				reader.close();
			}

			dpService.getUserRecords().put(dpService.getUserRecords().size() + 1, login);
			FileWriter writer = new FileWriter(DataPoint.filePath);
			userList.add(login.getId() + "," + login.getFirstName() + "," + login.getLastName() + "," + login.getEmail()
					+ "," + login.getPassword());
			String output = "";
			for (String i : userList) {
				output += i + "\n";
			}
			writer.write(output);
			writer.close();
		} catch (Exception e) {
		}
		return true;
	}

}
