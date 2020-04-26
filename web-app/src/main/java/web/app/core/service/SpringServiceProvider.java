package web.app.core.service;

import javax.annotation.PostConstruct;
import javax.management.ServiceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class SpringServiceProvider implements ServiceProvider, ApplicationContextAware {
    
    ApplicationContext webApplicationContext = null;
    
    public static Logger LOGGER = LoggerFactory.getLogger(SpringServiceProvider.class);
    
    
	/**
	 * Returns the value of the webApplicationContext field.
	 *
	 * @return the value of the webApplicationContext field.
	 */
	public ApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}
	
	@PostConstruct
	public void initialize() {
		LOGGER.info("SpringServiceProvider started ");
	}
	
	/**
	 * Sets the webApplicationContext to the specified value.
	 * 
	 * @param webApplicationContext The webApplicationContext to set.
	 */
	public void setApplicationContext(ApplicationContext webApplicationContext) {
        
        this.webApplicationContext = webApplicationContext;
        
        try {
			ServiceLocator.addDefaultService(this);
		} catch (ServiceNotFoundException e) {
			LOGGER.error("Error: ", e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getService(Class<T> classname) throws ServiceNotFoundException {
		
		String[] names =  webApplicationContext.getBeanNamesForType(classname);
		
		if (names.length == 1) {
			return (T) webApplicationContext.getBean(names[0], classname);
		}
		
		return (T) webApplicationContext.getAutowireCapableBeanFactory().autowire(classname, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, true);
	}

	public <T> T getRemoteService(Class<T> serviceClass) throws ServiceNotFoundException {
		return getService(serviceClass);
	}

	public <T> T getService(Class<T> serviceClass, String service) throws ServiceNotFoundException {
    	try {
    		
    		return (T) webApplicationContext.getBean(service);	
		} 
    	catch (Exception e) {
    		throw new ServiceNotFoundException();
		}	
    }
}


