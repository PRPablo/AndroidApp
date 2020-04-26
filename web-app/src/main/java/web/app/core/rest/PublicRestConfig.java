package  web.app.core.rest;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.jaxrs.config.BeanConfig;

public class PublicRestConfig extends ResourceConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(PublicRestConfig.class);
	

	public PublicRestConfig(MeterRegistry registry) {

		register(AdminResourceImpl.class);

		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		
		LOGGER.info("Restconfig");
	}

	@PostConstruct
	public void init() {
	
	 	BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Web API");
        beanConfig.setVersion("1.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath("/public");
        beanConfig.setHost("localhost:8080");

        beanConfig.setResourcePackage("web.app.client.api");
        beanConfig.setScan(true);
    
        LOGGER.info("Restconfig swagger");
	}
	
}
