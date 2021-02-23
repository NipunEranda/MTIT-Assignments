package loginservicepublisher;

import java.util.HashMap;

public class DataPoint {
	
	private HashMap<Integer, User> loginsRecords;
	public static User usr;
	
	public DataPoint() {
		loginsRecords = new HashMap<Integer, User>();
		if(loginsRecords.size() == 0)
			loginsRecords.put(1, new User(1, "root", "test", "root@test.com", "test123"));
	}
	
	public boolean addLogin(User login) {
		loginsRecords.put(loginsRecords.size() + 1, login);
		return true;
	}
	
	public User login(String email, String password) {
		for(int i = 1; i <= loginsRecords.size(); i++) {
			if(loginsRecords.get(i).getEmail().equals(email) && loginsRecords.get(i).getPassword().equals(password)) {
				return loginsRecords.get(i);
			}
		}
		return null;
	}
	
	public boolean deleteLogin(int id) {
		loginsRecords.remove(id);
		return true;
	}
	
	public boolean resetPassword(String currentPassword, String newPassword, int id) {
		if(loginsRecords.get(id).getPassword().equals(currentPassword)) {
			User usr = loginsRecords.get(id);
			usr.setPassword(newPassword);
			loginsRecords.put(id, usr);
			return true;
		}
		return false;
	}
}
