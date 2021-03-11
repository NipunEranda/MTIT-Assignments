package datapointpublisher;

import java.util.HashMap;

public class DataPointServiceImp implements DataPointService{

	DataPoint dp = new DataPoint();
	
	@Override
	public HashMap<Integer, User> getUserRecords() {
		return DataPoint.userRecords;
	}

	@Override
	public boolean setCurrentUser(User usr) {
		DataPoint.usr = usr;
		return true;
	}

}
