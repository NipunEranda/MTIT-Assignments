package registerservicepublisher;

import java.util.HashMap;

public interface RegisterService {

	public String Status();
	
	public HashMap<String, String> register(String firstName, String lastName, String email, String password,String confirmPassword);
	
	
}
