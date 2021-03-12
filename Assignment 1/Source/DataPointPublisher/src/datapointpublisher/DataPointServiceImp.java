package datapointpublisher;

import java.util.HashMap;

public class DataPointServiceImp implements DataPointService{
	
	@Override
	public HashMap<Integer, User> getUserRecords() {
		return DataPoint.userRecords;
	}

	@Override
	public boolean setCurrentUser(User usr) {
		DataPoint.usr = usr;
		return true;
	}

	@Override
	public int loginCount() {
		return DataPoint.userRecords.size();
	}

}
