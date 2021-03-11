package loginservicesubscriber;

import java.lang.StackWalker.Option;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import loginservicepublisher.DataPoint;
import loginservicepublisher.LoginService;

public class LoginServiceActivator implements BundleActivator {

	private static BundleContext context;
	private ServiceReference serviceReference;
	private String email = "";
	private String password = "";
	private String authString = "";
	private int postOptions = 0;
	private String currentPassword;
	private String newPassword;
	private String cnewPassword;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		try {
			Scanner s = new Scanner(System.in);
			LoginServiceActivator.context = bundleContext;
			System.out.println("Start Subscriber Service");
			serviceReference = context.getServiceReference(LoginService.class.getName());
			@SuppressWarnings("unchecked")
			LoginService loginService = (LoginService) context.getService(serviceReference);
			do {

				System.out.println("Please enter user credentials to login to the system.");
				System.out.print("Email : ");
				email = s.next().toString();
				System.out.print("Password : ");
				password = s.next().toString();
				HashMap<String, String> loginResult = loginService.login(email, password);

				if (loginResult.get("status").equalsIgnoreCase("success")) {
					authString = loginResult.get("authString");
					System.out.println("\nWelcome to Account Management CLI Login Interface!");
					do {
						System.out.print(
								"Options\n(1) Reset Password\n(2) Remove Account\n(3) Logout\n(0) Exit\nSelect an option : ");
						postOptions = s.nextInt();

						if (postOptions == 1) {
							
							// Reset Password
							System.out.print("Enter the current Password : ");
							currentPassword = s.next().toString();
							System.out.print("Enter the new Password : ");
							newPassword = s.next().toString();
							System.out.print("Confirm the new Password : ");
							cnewPassword = s.next().toString();
							if (newPassword.equals(cnewPassword)) {
								if (password.equals(currentPassword)) {
									loginService.resetPassword(currentPassword, newPassword);
									System.out.println("Password Changed!, Logged Out From the System\n");
									loginService.logout();
									postOptions = -1;
									continue;
								} else {
									System.out.println("Current Password doesn't match!\n");
								}
							} else {
								System.out.println("New Password Doesn't match with Confirmation Password!\n");
							}

						} else if (postOptions == 2) {
							
							// Remove Account
							String in;
							do {
								System.out.print("Are you sure? (Y/N): ");
								in = s.next();
								if ("y".equalsIgnoreCase(in)) {
									loginService.removeAccount();
									System.out.println("Account Removed!, Logged Out From the System\n");
									loginService.logout();
									postOptions = -1;
									continue;
								} else if ("n".equalsIgnoreCase(in)) {

								}
							} while (!"y".equalsIgnoreCase(in) && !"n".equalsIgnoreCase(in));
							
						} else if (postOptions == 3) {
							
							// End session
							postOptions = -1;
							loginService.logout();
							continue;
							
						} else if (postOptions == 0) {
							
							// Exit From The System
							System.out.println("Good Bye!\n");
							System.exit(0);
							
						}
					} while (postOptions != -1);
				} else {
					System.out.println("Login Failed\n");
				}
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		//LoginServiceActivator.context = null;
		System.out.println("GoodBye");
		context.ungetService(serviceReference);
	}

}
