package web.app.core.service;

import javax.management.ServiceNotFoundException;

public interface ServiceProvider {

	
	public abstract <T> T getService(Class<T> serviceClass) throws ServiceNotFoundException;
			
	public abstract <T> T getService(Class<T> serviceClass, String service)	throws ServiceNotFoundException;

}