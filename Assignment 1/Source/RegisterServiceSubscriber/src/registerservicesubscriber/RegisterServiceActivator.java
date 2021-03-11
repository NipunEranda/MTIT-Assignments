package registerservicesubscriber;

import java.util.Scanner;
import java.util.HashMap;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import registerservicepublisher.RegisterService;

public class RegisterServiceActivator implements BundleActivator {

	private static BundleContext context;
	private ServiceReference serviceReference;
	private String fname = "";
	private String lname = "";
	private String uname = "";
	private String email = "";
	private String password = "";
	private String conPassword = "";

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		try {
			Scanner s = new Scanner(System.in);
			RegisterServiceActivator.context = bundleContext;
			System.out.println("Start subscriber service");
			serviceReference = context.getServiceReference(RegisterService.class.getName());
			@SuppressWarnings("unchecked")
			RegisterService registerService = (RegisterService) context.getService(serviceReference);
			do {
				System.out.println("\nPlease enter the details to register into the system");
				System.out.println("First name: ");
				fname = s.next().toString();
				System.out.println("Last name: ");
				lname = s.next().toString();
				System.out.println("Email: ");
				email = s.next().toString();
				System.out.println("Password: ");
				password = s.next().toString();
				System.out.println("Confirm Password: ");
				conPassword = s.next().toString();
				HashMap<String, String> registerResult = registerService.register(fname, lname, email, password,conPassword);
				if (registerResult.get("status").equalsIgnoreCase("success")) {
					System.out.println("Registration Success!");
				} else {
					System.out.println("Register Failed\n");
				}
			} while (true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		RegisterServiceActivator.context = null;
		System.out.println("GoodBye");
		context.ungetService(serviceReference);
	}

}
