package br.com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan
@EnableTransactionManagement
@SpringBootApplication
public class FundesegApplication extends SpringBootServletInitializer {
	public static void main(String[] args) throws Exception {
		new SpringApplication(FundesegApplication.class).run(args);
	}
}



/*
SpringBoot Notes

@Bean      :tells Spring 'here is an instance of this class, please keep hold of it and give it back to me when I ask'.
@Autowired :says 'please give me an instance of this class, for example, one that I created with an @Bean annotation earlier'.

*/
