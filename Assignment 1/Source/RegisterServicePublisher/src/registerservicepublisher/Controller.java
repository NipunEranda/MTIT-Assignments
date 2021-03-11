package registerservicepublisher;

import datapointpublisher.DataPointService;
import datapointpublisher.User;

public class Controller {
	
	public DataPointService dpService;
	
	public Controller(DataPointService dpService) {
		this.dpService = dpService;
	}
	
	public boolean register(User login) {
		dpService.getUserRecords().put(dpService.getUserRecords().size() + 1, login);
		return true;
	}

}
