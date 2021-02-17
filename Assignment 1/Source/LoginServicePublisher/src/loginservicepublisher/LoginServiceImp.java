package loginservicepublisher;

import java.util.Base64;
import java.util.HashMap;

public class LoginServiceImp implements LoginService{

	@Override
	public String status() {
		return "success!";
	}

	@Override
	public HashMap<String, String> login(String email, String password) {
		String emailTxt = "test";
		String passwordTxt = "test";
		HashMap<String, String> result = new HashMap<String, String>();
		if(emailTxt.equals(email) && passwordTxt.equals(password)) {
			result.put("status", "success");
			result.put("authString", (Base64.getEncoder().encode(("authKey:" + email + ":" + password).getBytes()).toString()));
		}else {
			result.put("status", "error");
			result.put("reason", "Credentials are invalid.");
		}
		return result;
	}

	@Override
	public HashMap<String, String> resetPassword(String currentPassword, String newPassword, String authString) {
		String[] content = new String(Base64.getDecoder().decode(authString)).split(":");
		String currentPasswordTxt = content[2];
		HashMap<String, String> result = new HashMap<String, String>();
		if(currentPasswordTxt.equals(currentPassword)){
			result.put("status", "success");
		}else {
			result.put("status", "error");
		}
		return result;
	}

}
