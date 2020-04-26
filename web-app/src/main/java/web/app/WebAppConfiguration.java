package web.app;

import java.util.EnumSet;

import javax.inject.Inject;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;
import web.app.core.rest.PublicRestConfig;

@Configuration
public class WebAppConfiguration {

	@Inject
	MeterRegistry meterRegistry;

	@Bean
	public FilterRegistrationBean publicJersey() {
		
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new ServletContainer(new PublicRestConfig(meterRegistry)));
		registration.setUrlPatterns(java.util.Arrays.asList("/public/*"));
		registration.setOrder(-1);
		registration.setName("PublicJersey");
		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		
		return registration;
		
	}
	
}
