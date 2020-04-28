package  web.app.core.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublicRestConfig extends ResourceConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(PublicRestConfig.class);
	

public PublicRestConfig() {

		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		
		LOGGER.info("Public Restconfig");
	}
	
}
