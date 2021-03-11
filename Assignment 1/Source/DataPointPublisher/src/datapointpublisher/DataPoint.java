package datapointpublisher;

import java.util.HashMap;

public class DataPoint {
	
	public static HashMap<Integer, User> userRecords;
	public static User usr = null;
	
	public DataPoint() {
		userRecords = new HashMap<Integer, User>();
		if(userRecords.size() == 0)
			userRecords.put(1, new User(1, "root", "test", "root@test.com", "test123"));
	}

}
