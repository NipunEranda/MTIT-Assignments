package loginservicepublisher;

import java.util.HashMap;

public interface LoginService {

	public String status();
	public HashMap<String, String> login(String email, String password);
	public HashMap<String, String> resetPassword(String currentPassword, String newPassword, String authString);
}
