package loginservicepublisher;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.crypto.Data;

import datapointpublisher.DataPoint;
import datapointpublisher.DataPointService;
import datapointpublisher.DataPointServiceImp;
import datapointpublisher.User;

public class LoginServiceImp implements LoginService {

	Controller c;
	
	@Override
	public String status() {
		return "success!";
	}

	@Override
	public HashMap<String, String> login(String email, String password) {
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			User usr = c.loginFunction(email, password);
			if (usr != null) {
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
			if (c.resetPasswordFunction(currentPassword, newPassword, DataPoint.usr.getId()))
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
		c.deleteLoginFunction(DataPoint.usr.getId());
		return false;
	}
	
	public LoginServiceImp(DataPointService dataPointService){
		c = new Controller(dataPointService);
	}
}
