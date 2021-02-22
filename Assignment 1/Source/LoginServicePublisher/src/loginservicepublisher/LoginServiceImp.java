package loginservicepublisher;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.crypto.Data;

public class LoginServiceImp implements LoginService {

	public DataPoint dp;

	@Override
	public String status() {
		return "success!";
	}

	@Override
	public HashMap<String, String> login(String email, String password) {
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			if (dp == null)
				dp = new DataPoint();
			User usr = dp.login(email, password);
			if (usr != null) {
				DataPoint.usr = usr;
				result.put("status", "success");
			} else {
				result.put("status", "error");
				result.put("reason", "Credentials are invalid.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public HashMap<String, String> resetPassword(String currentPassword, String newPassword) {
		HashMap<String, String> result = new HashMap<String, String>();
		if (DataPoint.usr != null) {
			if (dp.resetPassword(currentPassword, newPassword, DataPoint.usr.getId()))
				result.put("status", "success");
			else
				result.put("status", "error");
		} else {
			result.put("status", "error");
			result.put("reason", "Login First");
		}
		return result;
	}

	@Override
	public boolean logout() {
		DataPoint.usr = null;
		return true;
	}

	@Override
	public boolean removeAccount() {
		dp.deleteLogin(DataPoint.usr.getId());
		return false;
	}

}
