package registerservicepublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import datapointpublisher.DataPointService;


public class RegisterServiceActivator implements BundleActivator {

	private static BundleContext context;
	private ServiceRegistration publishServiceRegistration;
	private ServiceReference serviceReference;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		RegisterServiceActivator.context = bundleContext;
		System.out.println("Publisher Start!");
		serviceReference = context.getServiceReference(DataPointService.class.getName());
		DataPointService dataPointService = (DataPointService) context.getService(serviceReference);
		RegisterService registerService = new RegisterServiceImp(dataPointService);
		publishServiceRegistration = context.registerService(RegisterService.class.getName(), registerService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		RegisterServiceActivator.context = null;
		System.out.println("Publisher Stop");
		publishServiceRegistration.unregister();
	}

}