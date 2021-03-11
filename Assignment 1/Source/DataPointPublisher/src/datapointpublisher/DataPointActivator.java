package datapointpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class DataPointActivator implements BundleActivator {

	private static BundleContext context;
	ServiceRegistration publishServiceRegistration;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		DataPointActivator.context = bundleContext;
		System.out.println("Data Point Publisher Start!");
		DataPointService dataPointService = new DataPointServiceImp();
		publishServiceRegistration = context.registerService(DataPointService.class.getName(), dataPointService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		DataPointActivator.context = null;
		System.out.println("Data Point Publisher Stopped!");
		publishServiceRegistration.unregister();
	}

}
