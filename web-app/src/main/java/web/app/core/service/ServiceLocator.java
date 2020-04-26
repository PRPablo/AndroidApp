package web.app.core.service;

import javax.management.ServiceNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServiceLocator implements ServiceProvider {
	
	Log LOGGER = LogFactory.getLog(ServiceLocator.class);

	static ServiceLocator instance = null;
	
    
    private ServiceProvider defaultServiceProvider = null;
	 
	 

    public static void useJndiServiceProvider() throws ServiceNotFoundException {
    	addServiceProvider(new JndiServiceProvider());
    }
    
    public static void addDefaultService(ServiceProvider serviceProvider) throws ServiceNotFoundException {
    	instance().defaultServiceProvider = serviceProvider; 
    }
	
	public static void addServiceProvider(ServiceProvider serviceProvider) throws ServiceNotFoundException {
		
	}
	
	public static ServiceLocator instance()  {
		
		if (instance == null) {
			
			instance = new ServiceLocator(); 

		}
		
		return instance;
	}

	public <T> T getService(Class<T> serviceClass)	throws ServiceNotFoundException {
		
		return defaultServiceProvider.getService(serviceClass);
	}

	public <T> T getService(Class<T> serviceClass, String service) throws ServiceNotFoundException {
		
		return defaultServiceProvider.getService(serviceClass, service);	
	}
	
	public static <T> T get(Class<T> serviceClass) throws ServiceNotFoundException {
		return instance().getService(serviceClass);
	}

	public static <T> T get(Class<T> serviceClass, String name) throws ServiceNotFoundException {
		return instance().getService(serviceClass, name);
	}

	
}
