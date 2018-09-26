package br.com.app.config;

import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.app.identity.*;
import org.springframework.core.annotation.Order;


@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Filters will not get executed for the resources
        web.ignoring().antMatchers("/", "/resources/**", "/static/**", "/public/**", "/webui/**", "/h2-console/**", "/login/**"
            , "/configuration/**", "/swagger-ui/**", "/swagger-resources/**", "/api-docs", "/api-docs/**", "/v2/api-docs/**"
            , "/*.html", "/**/*.html" ,"/**/*.css","/**/*.js","/**/*.png","/**/*.jpg", "/**/*.gif", "/**/*.svg", "/**/*.ico", "/**/*.ttf","/**/*.woff","/**/*.otf");
    }

 // this configuration allow the client app to access the this api 
 	// all the domain that consume this api must be included in the allowed o'rings 
 	@Bean
 	public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
				super.addCorsMappings(registry);
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200","http://localhost:8080","http://www.pdf995.com/samples/**","https://storage.googleapis.com/fundeseg-attachments/**")
						.allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS");
	          
	        }
	    };
 	}
 	
 	
    //If Security is not working check application.properties if it is set to ignore
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .exceptionHandling().and()
        .anonymous().and()
        // Disable Cross site references
        .csrf().disable()
        // Add CORS Filter
        .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
        // Custom Token based authentication based on the header previously given to the client
        .addFilterBefore(new VerifyTokenFilter(tokenUtil), UsernamePasswordAuthenticationFilter.class)
        // custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
        .addFilterBefore(new GenerateTokenForUserFilter ("/session", authenticationManager(), tokenUtil), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests().antMatchers("/").permitAll()
        .anyRequest().authenticated()
        ;
    }

    /*
    * If You want to store encoded password in your databases and authenticate user
    * based on encoded password then uncomment the below method and provde an encoder

    //@Autowired
    //private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    */
}
