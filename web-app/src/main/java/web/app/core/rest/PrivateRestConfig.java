package web.app.core.rest;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.jaxrs.config.BeanConfig;

public class PrivateRestConfig  extends ResourceConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrivateRestConfig.class);


	public PrivateRestConfig() {

		register(AdminResourceImpl.class);
		
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		
		LOGGER.info("Private Restconfig");
	}

	@PostConstruct
	public void init() {
	
	 	BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Web API");
        beanConfig.setVersion("1.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath("/private");
        beanConfig.setHost("localhost:8080");

        beanConfig.setResourcePackage("web.app.client.api");
        beanConfig.setScan(true);
    
        LOGGER.info("Private Restconfig swagger");
	}


}
