package loginservicepublisher;

import datapointpublisher.DataPoint;
import datapointpublisher.DataPointService;
import datapointpublisher.User;

public class Controller {
	
	public DataPointService dpService;
	
	public Controller(DataPointService dpService) {
		this.dpService = dpService;
		new DataPoint();
	}
	
	public User loginFunction(String email, String password) {
		new DataPoint();
		for(int i = 1; i <= dpService.getUserRecords().size(); i++) {
			if(dpService.getUserRecords().get(i).getEmail().equals(email) && dpService.getUserRecords().get(i).getPassword().equals(password)) {
				dpService.setCurrentUser(dpService.getUserRecords().get(i));
				return dpService.getUserRecords().get(i);
			}
		}
		return null;
	}
	
	public boolean deleteLoginFunction(int id) {
		new DataPoint();
		dpService.getUserRecords().remove(id);
		DataPoint.deleteRecord(id);
		return true;
	}
	
	public boolean resetPasswordFunction(String currentPassword, String newPassword, int id) {
		new DataPoint();
		if(dpService.getUserRecords().get(id).getPassword().equals(currentPassword)) {
			dpService.getUserRecords().get(id).setPassword(newPassword);
			DataPoint.updatePassword(id, newPassword);
			return true;
		}
		return false;
	}
}
