package registerservicepublisher;

import java.util.HashMap;

import datapointpublisher.DataPointService;
import datapointpublisher.User;

public class RegisterServiceImp implements RegisterService{

	Controller c;
	
	public RegisterServiceImp(DataPointService dataPointService){
		c = new Controller(dataPointService);
	}

	public String Status(){
		return "success!";
	}

	public HashMap<String, String> register(String firstName, String lastName, String email, String password,String confirmPassword) {
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			User user;
			if(!firstName.equalsIgnoreCase("") && !lastName.equalsIgnoreCase("") && !email.equalsIgnoreCase("") && !password.equalsIgnoreCase("") && !confirmPassword.equalsIgnoreCase("") && (password.equals(confirmPassword))) {
				user = new User(c.dpService.getUserRecords().size()+1, firstName,lastName,email, password);
			}else {
				user = null;
			}
			
			if (user != null) {
				c.dpService.getUserRecords().put(c.dpService.getUserRecords().size()+1, user);
				result.put("status", "success");
			} else {
				result.put("status", "error");
				result.put("reason", "check inputs again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	}




