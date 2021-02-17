package registerservicepublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class RegisterServiceActivator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		RegisterServiceActivator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		RegisterServiceActivator.context = null;
	}

}
