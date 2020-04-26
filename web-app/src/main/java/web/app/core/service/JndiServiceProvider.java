package web.app.core.service;

import javax.management.ServiceNotFoundException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JndiServiceProvider implements ServiceProvider {
	
	Log LOGGER = LogFactory.getLog(JndiServiceProvider.class);

	InitialContext initialContext = null;
	
	@SuppressWarnings("unchecked")
	public <T> T getService(Class<T> serviceClass) throws ServiceNotFoundException{
		
		int localpos = serviceClass.getName().lastIndexOf("Local"); 
		
		String service = serviceClass.getName() ;
		
		if (localpos > 0) {
			service = service.substring(0, localpos) + "/local";
		}
		
		int remotepos = serviceClass.getName().lastIndexOf("Remote"); 
		
		if (remotepos > 0) {
			service = service.substring(0, remotepos) + "/remote";
		}
		
		int pos = service.lastIndexOf(".");
		
		if (pos > 0) {
			service = service.substring(pos + 1);	
		}
		
		try {
			LOGGER.debug("getService() " + service);

			T t = (T) getInitialContext().lookup(service);
			
			LOGGER.debug("getService() " + t);
			
			return t;
			
		} 
		catch (Exception e) {
			throw new ServiceNotFoundException();
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.artdecode.base.service.ServiceProvider#getRemoteService(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <T> T getRemoteService(Class<T> serviceClass) throws ServiceNotFoundException{
		
		String service = serviceClass.getName() + "/remote";
		
		int pos = service.lastIndexOf(".");
		
		if (pos > 0) {
			service = service.substring(pos + 1);	
		}
		
		try {
			return (T) getInitialContext().lookup(service);			
		} 
		catch (Exception e) {
			throw new ServiceNotFoundException();
		}
		
	}
	/* (non-Javadoc)
	 * @see ar.com.artdecode.base.service.ServiceProvider#getService(java.lang.Class, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public <T> T getService(Class<T> serviceClass, String service) throws ServiceNotFoundException{
		
		try {
			return (T) getInitialContext().lookup(service);			
		} 
		catch (Exception e) {
			throw new ServiceNotFoundException();
		}
	}
	
	private InitialContext getInitialContext() throws NamingException {
		
		if (initialContext == null) {
			initialContext = new InitialContext();
		}
		
		return initialContext;
	}
	
}
