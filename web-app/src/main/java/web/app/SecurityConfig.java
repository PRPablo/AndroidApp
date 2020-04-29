package web.app;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

@Configuration
@EnableWebSecurity(debug=false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    

	@Override
    public void configure(WebSecurity web) throws Exception {

    	super.configure(web);
    	
    	web.httpFirewall(new DefaultHttpFirewall());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http.headers().frameOptions().disable();
        
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/public/**").permitAll()
            .antMatchers("/private/**").permitAll()
//          .antMatchers("/private/**").authenticated().and().formLogin()
            .anyRequest().permitAll();
    }
	
    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }

    @Bean
    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }
   
}
