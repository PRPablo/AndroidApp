package web.app;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import web.app.core.service.SpringServiceProvider;

@SpringBootApplication(scanBasePackages={"web.app"})
@ServletComponentScan(basePackages={"web.app"})
//@EntityScan(value={"web.app.model"})
//@EnableJpaRepositories(value={"web.app.dao"})
@EnableCaching
public class WebApplication extends SpringBootServletInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
        return springApplicationBuilder
        		.bannerMode(Banner.Mode.OFF)
        		.sources(WebApplication.class)
                .properties(getProperties());
    }

    public static void main(String[] args) {

    	 System.out.println("Starting...");
    
         new SpringApplicationBuilder(WebApplication.class)
                .sources(WebApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .properties(getProperties())
                .run(args);
    }

   static Properties getProperties() {
      Properties props = new Properties();

      props.put("web.app.config", System.getProperty("web.app.config", "."));
      
      props.put("spring.config.location", System.getProperty("web.app.config", ".") + "/config/");
      props.put("spring.config.name", "web-app");
	  
      System.out.println("web.app.config: " + System.getProperty("web.app.config", "."));
      
      System.out.println("spring.config.location: " + props.get("spring.config.location"));
      
      return props;
   }
   
    @Autowired
	private Environment env;
	
	@Autowired
	SpringServiceProvider springServiceProvider;
	
	@PostConstruct
	public void initApplication() throws IOException {

		if (env.getActiveProfiles().length == 0) {
			LOGGER.warn("No Spring profile configured, running with default configuration");
		} else {
			LOGGER.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
		}
		
		LOGGER.info("springServiceProvider " + springServiceProvider);		
	}

}

