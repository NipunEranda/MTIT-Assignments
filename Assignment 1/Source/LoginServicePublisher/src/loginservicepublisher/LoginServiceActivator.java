package loginservicepublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class LoginServiceActivator implements BundleActivator {

	private static BundleContext context;
	ServiceRegistration publishServiceRegistration;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		LoginServiceActivator.context = bundleContext;
		System.out.println("Publisher Start!");
		LoginService loginService = new LoginServiceImp();
		publishServiceRegistration = context.registerService(LoginService.class.getName(), loginService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		LoginServiceActivator.context = null;
		System.out.println("Publisher Stop");
		publishServiceRegistration.unregister();
	}

}
