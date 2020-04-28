package web.app;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import web.app.core.rest.PrivateRestConfig;
import web.app.core.rest.PublicRestConfig;

@Configuration
public class WebAppConfiguration {

	
	@Bean
	public FilterRegistrationBean publicJersey() {
		
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new ServletContainer(new  PublicRestConfig()));
		registration.setUrlPatterns(java.util.Arrays.asList("/public/*"));
		registration.setOrder(-1);
		registration.setName("PublicJersey");
		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		
		return registration;
		
	}
	
	@Bean
	public FilterRegistrationBean privateJersey() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new ServletContainer(new  PrivateRestConfig()));
		registration.setUrlPatterns(java.util.Arrays.asList("/private/api/*"));
		registration.setOrder(-1);
		registration.setName("PrivateJersey");
		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		
		return registration;
		
	}
	
}
