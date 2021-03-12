package datapointpublisher;

import java.util.HashMap;

public interface DataPointService {

	public HashMap<Integer, User> getUserRecords();
	public boolean setCurrentUser(User usr);
	public int loginCount();
	
}
