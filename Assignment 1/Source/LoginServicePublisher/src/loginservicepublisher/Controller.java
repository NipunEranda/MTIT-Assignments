package loginservicepublisher;

import datapointpublisher.DataPointService;
import datapointpublisher.User;

public class Controller {
	
	public DataPointService dpService;
	
	public Controller(DataPointService dpService) {
		this.dpService = dpService;
	}
	
	public User loginFunction(String email, String password) {
		for(int i = 1; i <= dpService.getUserRecords().size(); i++) {
			if(dpService.getUserRecords().get(i).getEmail().equals(email) && dpService.getUserRecords().get(i).getPassword().equals(password)) {
				dpService.setCurrentUser(dpService.getUserRecords().get(i));
				return dpService.getUserRecords().get(i);
			}
		}
		return null;
	}
	
	public boolean deleteLoginFunction(int id) {
		dpService.getUserRecords().remove(id);
		return true;
	}
	
	public boolean resetPasswordFunction(String currentPassword, String newPassword, int id) {
		if(dpService.getUserRecords().get(id).getPassword().equals(currentPassword)) {
			User usr = dpService.getUserRecords().get(id);
			usr.setPassword(newPassword);
			dpService.getUserRecords().put(id, usr);
			return true;
		}
		return false;
	}
}
