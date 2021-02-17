package loginservicesubscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import loginservicepublisher.LoginService;

public class LoginServiceActivator implements BundleActivator {

	private static BundleContext context;
	private ServiceReference serviceReference;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		LoginServiceActivator.context = bundleContext;
		System.out.println("Start Subscriber Service");
		serviceReference = context.getServiceReference(LoginService.class.getName());
		@SuppressWarnings("unchecked")
		LoginService loginService = (LoginService) context.getService(serviceReference);
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		LoginServiceActivator.context = null;
		System.out.println("GoodBye");
		context.ungetService(serviceReference);
	}

}
