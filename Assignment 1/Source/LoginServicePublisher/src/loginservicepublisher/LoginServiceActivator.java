package loginservicepublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import datapointpublisher.DataPointService;

public class LoginServiceActivator implements BundleActivator {

	private static BundleContext context;
	ServiceRegistration publishServiceRegistration;
	private ServiceReference serviceReference;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		LoginServiceActivator.context = bundleContext;
		System.out.println("Publisher Start!");
		serviceReference = context.getServiceReference(DataPointService.class.getName());
		DataPointService dataPointService = (DataPointService) context.getService(serviceReference);
		LoginService loginService = new LoginServiceImp(dataPointService);
		publishServiceRegistration = context.registerService(LoginService.class.getName(), loginService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		LoginServiceActivator.context = null;
		System.out.println("Publisher Stop");
		publishServiceRegistration.unregister();
	}

}
